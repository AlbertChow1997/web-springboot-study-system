package com.albert;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){
        //System.out.println(LocalDateTime.now() + " : 开始计算...");
        log.debug("Start Calculate...");

        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }


        log.info("The sum is: " + sum);
        log.debug("End Calculate...");

        log.trace("This is a trace log");
        log.warn("This is a warn log");
        log.error("This is a error log");


        //System.out.println("计算结果为: "+sum);
        //System.out.println(LocalDateTime.now() + "结束计算...");
    }

}
