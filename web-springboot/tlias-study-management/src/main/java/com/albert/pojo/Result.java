package com.albert.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * The Backend Return Result
 */
@Data
public class Result {

    private Integer code; //1: success, 0: fail
    private String msg; //error message
    private Object data; //data

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
