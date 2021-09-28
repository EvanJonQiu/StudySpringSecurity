package com.evanjon.studySpring.common;

import java.io.Serializable;

public class ResultMsg<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8323452363020954887L;
    
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;

    public ResultMsg() {
    }

    public ResultMsg(Boolean success) {
        this.success = success;
        this.errorCode = success ? ResultMsgCode.SUCCESS.getCode() : ResultMsgCode.FAILURE.getCode();
        this.errorMsg = success ? ResultMsgCode.SUCCESS.getMessage() : ResultMsgCode.FAILURE.getMessage();
    }

    public ResultMsg(Boolean success, ResultMsgCode code) {
        this.success = success;
        this.errorCode = success ? ResultMsgCode.SUCCESS.getCode()
                : (code == null ? ResultMsgCode.FAILURE.getCode() : code.getCode());
        this.errorMsg = success ? ResultMsgCode.SUCCESS.getMessage()
                : (code == null ? ResultMsgCode.FAILURE.getMessage() : code.getMessage());
    }

    public ResultMsg(Boolean success, T data) {
        this.success = success;
        this.errorCode = success ? ResultMsgCode.SUCCESS.getCode() : ResultMsgCode.FAILURE.getCode();
        this.errorMsg = success ? ResultMsgCode.SUCCESS.getMessage() : ResultMsgCode.FAILURE.getMessage();
        this.data = data;
    }

    public ResultMsg(Boolean success, T data, ResultMsgCode code) {
        this.success = success;
        this.errorCode = success ? ResultMsgCode.SUCCESS.getCode()
                : (code == null ? ResultMsgCode.FAILURE.getCode() : code.getCode());
        this.errorMsg = success ? ResultMsgCode.SUCCESS.getMessage()
                : (code == null ? ResultMsgCode.FAILURE.getMessage() : code.getMessage());
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
