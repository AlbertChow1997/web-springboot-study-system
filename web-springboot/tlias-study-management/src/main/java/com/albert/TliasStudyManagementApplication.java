package com.albert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ServletComponentScan
public class TliasStudyManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasStudyManagementApplication.class, args);
    }

}
