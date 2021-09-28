package com.evanjon.studySpring.common;

public class ResultMsgUtil {
    
    public static <T> ResultMsg<T> success() {
        return new ResultMsg<T>(true);
    }
    
    public static <T> ResultMsg<T> success(T data) {
        return new ResultMsg<T>(true, data);
    }
    
    public static <T> ResultMsg<T> failure() {
        return new ResultMsg<T>(false);
    }
    
    public static <T> ResultMsg<T> failure(ResultMsgCode code) {
        return new ResultMsg<T>(false, code);
    }
}
