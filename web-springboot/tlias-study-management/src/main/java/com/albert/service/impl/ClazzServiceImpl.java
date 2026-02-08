package com.albert.service.impl;

import com.albert.mapper.ClazzMapper;
import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import com.albert.pojo.PageResult;
import com.albert.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQuery) {
        /**
         * @param page
         * @param pageSize
         * @return the page result
         *
         */
        PageHelper.startPage(clazzQuery.getPage(), clazzQuery.getPageSize());
        List<Clazz> rows = clazzMapper.list(clazzQuery);
        Page<Clazz> p = (Page<Clazz>) rows;

        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void add(Clazz clazz) {
        clazzMapper.add(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public Clazz getClazzById(Long id) {
        return clazzMapper.getById(id);
    }
}
