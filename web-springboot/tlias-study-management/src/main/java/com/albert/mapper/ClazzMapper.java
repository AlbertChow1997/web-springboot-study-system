package com.albert.mapper;

import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQuery);
}
