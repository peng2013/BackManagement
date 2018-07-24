package com.peng.BackManagement.util.resutil;

/**
 * 错误信息封装
 *
 * @author: peng
 * @time: 2018/7/23
 **/
public class CodeMessage {
    private int retCode;
    private String message;

    public CodeMessage(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public CodeMessage(int retCode, CodeMessage codeMessage) {
        this.retCode = retCode;
    }

    public CodeMessage(String message) {
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
