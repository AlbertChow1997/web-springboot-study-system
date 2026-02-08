package com.albert.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /**
     * 联系地址
     */
    private String address;
    /**
     * 班级ID
     */
    private Long clazzId;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 最高学历，1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士
     */
    private Long degree;
    /**
     * 性别，1: 男 , 2: 女
     */
    private Long gender;
    /**
     * 毕业时间
     */
    private OffsetDateTime graduationDate;
    /**
     * ID
     */
    private Long id;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 是否是院校学生，1: 是 , 0: 否
     */
    private Long isCollege;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String no;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 修改时间
     */
    private OffsetDateTime updateTime;
    /**
     * 违纪次数
     */
    private Long violationCount;
    /**
     * 违纪扣分
     */
    private Long violationScore;

    private String clazzName;
}
