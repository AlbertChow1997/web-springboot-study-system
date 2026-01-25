package com.albert.service.impl;

import com.albert.mapper.EmpExprMapper;
import com.albert.mapper.EmpMapper;
import com.albert.pojo.*;
import com.albert.service.EmpLogService;
import com.albert.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
        //Use pageHelper to implement
        //If using the pageHelper, you don't need to calculate the start index and add limit in the SQL statement
        //It will be automatically implemented by the pageHelper
        /**
         * @param page
         * @param pageSize
         * @return the page result
         *
         */
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Emp> rows = empMapper.list(param);

        Page<Emp> p = (Page<Emp>) rows;

        return new PageResult<>(p.getTotal(), p.getResult());

//        //1.call the count method
//        Long total = empMapper.count();
//
//        //2.calculate the start index
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        //3.call the list method
//
//
//        //4.return the page result
//        return new PageResult<Emp>(total, rows);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.set the createTime and updateTime
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            System.out.println("Employee ID after insert: " + emp.getId());

            int empId = emp.getId();

            //2.save the exprList
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(empId);
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"Add employee"+emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.delete the basic info of employees
        empMapper.deleteByIds(ids);
        //2.delete the emp_expr of related employees
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {

        return empMapper.getById(id);
    }
}
