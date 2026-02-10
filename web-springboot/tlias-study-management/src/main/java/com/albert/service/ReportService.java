package com.albert.service;

import com.albert.pojo.ClazzOption;
import com.albert.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    ClazzOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
