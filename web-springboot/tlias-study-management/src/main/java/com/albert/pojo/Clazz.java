package com.albert.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    /**
     * 开课时间，开课时间
     */
    private LocalDate beginDate;
    /**
     * 创建时间，创建时间
     */
    private LocalDateTime createTime;
    /**
     * 结课时间，结课时间
     */
    private LocalDate endDate;
    /**
     * ID，ID
     */
    private Long id;
    /**
     * 班主任id，班主任id, 关联员工表id
     */
    private Long masterId;
    /**
     * 班级名称，班级
     */
    private String name;
    /**
     * 班级教室，班级教室
     */
    private String room;
    /**
     * 学科，1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式
     */
    private Long subject;
    /**
     * 修改时间，修改时间
     */
    private LocalDateTime updateTime;

    private String masterName; //班主任姓名
    private String status; //班级状态 - 未开班 , 在读 , 已结课
}
