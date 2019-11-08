package com.javior.taskmanager.api;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class BaseController {


    protected ApiResponse success(Object data) {
        return ApiResponse.success(data);
    }

    protected ApiResponse fail(String error){
        return ApiResponse.fail(error);
    }
}
