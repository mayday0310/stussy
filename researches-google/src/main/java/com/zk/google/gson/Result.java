package com.zk.google.gson;

/**
 * Created by qcon on 2017/3/21.
 */
public class Result<T> {

    public static final Result<String> DEFAULT_RESULT= new Result(666, "zkchs1991", "178cm");

    public Result (){}

    public Result(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
