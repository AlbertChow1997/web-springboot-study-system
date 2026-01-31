package com.albert.controller;


import com.albert.pojo.JobOption;
import com.albert.pojo.Result;
import com.albert.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("Get employees' position statistics");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
}
