package com.albert.service;

import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import com.albert.pojo.PageResult;

import java.awt.*;
import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQuery);

    void add(Clazz clazz);

    List<Clazz> findAll();

    void update(Clazz clazz);
}
