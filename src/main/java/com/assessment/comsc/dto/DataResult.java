package com.assessment.comsc.dto;

import lombok.Data;

/**
 * Unified front-end and back-end interaction objects
 * @param <T>
 */
@Data
public class DataResult<T> {

    /**
     * 1 == interface success
     * 0 == interface failed
     */
    private Integer success;

    private T data;

    private String msg;


    public void succeed(T response) {
        success = 1;
        data = response;
        msg = "SUCCESS";
    }

    public void failed(T response, String errorMsg) {
        success = 0;
        data = response;
        msg = errorMsg;
    }


}
