package com.albert.service;

import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import com.albert.pojo.PageResult;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQuery);
}
