package com.javior.taskmanager.api;

public class ApiResponse {
    private boolean success;
    private Object data;
    private String error;

    private ApiResponse() {
    }

    private ApiResponse(boolean success, Object data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse success(Object data){
        return new ApiResponse(true, data, null);
    }

    public static ApiResponse fail(String error){
        return new ApiResponse(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
