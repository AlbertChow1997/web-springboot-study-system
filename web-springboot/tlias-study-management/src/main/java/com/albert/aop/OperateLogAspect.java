package com.albert.aop;


import com.albert.utils.CurrentHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.albert.mapper.OperateLogMapper;
import com.albert.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    private final OperateLogMapper operateLogMapper;
    private final ObjectMapper objectMapper;

    public OperateLogAspect(OperateLogMapper operateLogMapper, ObjectMapper objectMapper) {
        this.operateLogMapper = operateLogMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * 切点：controller 包下所有方法
     */
    @Pointcut("execution(* com.albert.controller..*(..))")
    public void controllerPointcut() {}

    /**
     * 环绕通知：记录增删改操作日志
     */
    @Around("controllerPointcut()")
    public Object recordOperateLog(ProceedingJoinPoint pjp) throws Throwable {

        // 1) 判断是否为增删改（你可以按项目习惯调整规则）
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        if (!isCudMethod(methodName)) {
            return pjp.proceed();
        }

        // 2) 采集基础信息
        String className = pjp.getTarget().getClass().getName();
        Object[] args = pjp.getArgs();
        String methodParams = safeToJson(args);

        Integer operateEmpId = currentUserId(); // 从登录上下文获取（下面给示例实现）
        LocalDateTime operateTime = LocalDateTime.now();

        long start = System.currentTimeMillis();
        Object result = null;
        Throwable ex = null;

        try {
            // 3) 执行目标方法
            result = pjp.proceed();
            return result;
        } catch (Throwable t) {
            ex = t;
            throw t;
        } finally {
            long costTime = System.currentTimeMillis() - start;

            // 4) 返回值（或异常信息）序列化
            String returnValue;
            if (ex == null) {
                returnValue = safeToJson(result);
            } else {
                // 如果你不想记录异常，把这里改为 null 即可
                returnValue = truncate("EXCEPTION: " + ex.getClass().getName() + ": " + ex.getMessage());
            }

            // 5) 组装并落库
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(operateTime);
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(truncate(methodParams));
            operateLog.setReturnValue(truncate(returnValue));
            operateLog.setCostTime(costTime);

            try {
                operateLogMapper.insert(operateLog);
            } catch (Exception dbEx) {
                // 日志落库失败不要影响业务
                log.error("保存操作日志失败: {}", dbEx.getMessage(), dbEx);
            }
        }
    }

    /**
     * 规则：按方法名判断是否为增删改
     * 你可以按项目规范扩展：save/insert/add/create, update/edit/modify, delete/remove/del...
     */
    private boolean isCudMethod(String methodName) {
        String n = methodName.toLowerCase();
        return n.startsWith("add")
                || n.startsWith("save")
                || n.startsWith("insert")
                || n.startsWith("create")
                || n.startsWith("update")
                || n.startsWith("edit")
                || n.startsWith("modify")
                || n.startsWith("delete")
                || n.startsWith("remove")
                || n.startsWith("del");
    }

    /**
     * 当前登录人ID：按你项目实际情况替换
     */
    private Integer currentUserId() {
        // 方式1：ThreadLocal（例如你在登录拦截器里把用户ID塞进去）
        // return LoginContext.getCurrentUserId();

        // 方式2：Spring Security
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // if (auth == null || !(auth.getPrincipal() instanceof MyUserDetails u)) return null;
        // return u.getId();

        // 这里先给一个兜底，避免 NPE
        return CurrentHolder.getCurrentId();
    }

    private String safeToJson(Object obj) {
        if (obj == null) return null;
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // 序列化失败时，退化为 toString
            return String.valueOf(obj);
        }
    }

    /**
     * 表字段 varchar(2000)，做截断保护（避免超长插入失败）
     */
    private String truncate(String s) {
        if (s == null) return null;
        return s.length() <= 2000 ? s : s.substring(0, 2000);
    }
}
