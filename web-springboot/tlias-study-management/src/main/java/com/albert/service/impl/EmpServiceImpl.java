package com.albert.service.impl;

import com.albert.mapper.EmpExprMapper;
import com.albert.mapper.EmpMapper;
import com.albert.pojo.Emp;
import com.albert.pojo.EmpExpr;
import com.albert.pojo.EmpQueryParam;
import com.albert.pojo.PageResult;
import com.albert.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;
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

    @Override
    public void save(Emp emp) {
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
    }
}
