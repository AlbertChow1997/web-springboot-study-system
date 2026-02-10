package com.albert.mapper;


import com.albert.pojo.PageResult;
import com.albert.pojo.Student;
import com.albert.pojo.StudentQueryParam;
import com.github.pagehelper.page.PageParams;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(String name, Integer degree, Integer clazzId);

    @Insert("insert into student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void add(Student student);

    void deleteByIds(List<Integer> ids);

    void update(Student student);

    Student getById(Integer id);
}
