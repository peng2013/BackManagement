package com.peng.BackManagement.util.resutil;

/**
 * 封装返回数据
 *
 * @author: peng
 * @time: 2018/7/23
 **/
public class ResponseData<T> {
    private final static int RET_SUCCESS = 1;
    private final static int RET_FAIL = 0;
    private String message;
    private int retCode;
    private T data;

    private ResponseData(T data, CodeMessage codeMessage) {
        this.retCode = codeMessage.getRetCode();
        this.message = codeMessage.getMessage();
        this.data = data;
    }

    private ResponseData(T data, String codeMessage) {
        this.retCode = RET_SUCCESS;
        this.message = codeMessage;
        this.data = data;
    }

    private ResponseData(String codeMessage) {
        this.retCode = RET_SUCCESS;
        this.message = codeMessage;
        this.data = data;
    }

    public static <T> ResponseData<T> succcess(T data, CodeMessage codeMessage) {
        return new ResponseData<T>(data, codeMessage);
    }

    public static <T> ResponseData<T> succcess(T data, String codeMessage) {
        CodeMessage codeM= new CodeMessage(RET_SUCCESS,codeMessage);
        return new ResponseData<T>(data, codeM);
    }

    public static <T> ResponseData<T> error(T data, CodeMessage codeMessage) {
        CodeMessage codeM= new CodeMessage(RET_FAIL,codeMessage);
        return new ResponseData<T>(data, codeM);
    }

    public static <T> ResponseData<T> error(T data, String codeMessage) {
        return new ResponseData<T>(data, codeMessage);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
