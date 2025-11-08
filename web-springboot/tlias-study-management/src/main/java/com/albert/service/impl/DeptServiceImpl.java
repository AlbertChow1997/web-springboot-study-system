package com.albert.service.impl;

import com.albert.mapper.DeptMapper;
import com.albert.pojo.Dept;
import com.albert.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        deptMapper.add(dept);
    }

    @Override
    public Dept getInfo(Integer deptId) {
        return deptMapper.getInfo(deptId);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
