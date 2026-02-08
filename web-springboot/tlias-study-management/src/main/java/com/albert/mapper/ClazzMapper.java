package com.albert.mapper;

import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQuery);

    @Insert("insert into clazz VALUES (null,#{name},#{room},#{beginDate},#{endDate},#{masterId}, #{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> findAll();

    void update(Clazz clazz);

    Clazz getById(Long id);

    void deleteById(Long id);
}
