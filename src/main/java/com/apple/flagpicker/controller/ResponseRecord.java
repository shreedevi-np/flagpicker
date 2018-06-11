/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.controller;

import java.util.List;

/**
 *
 * @author Shree
 * @param <T>
 */
public class ResponseRecord<T> {

    private String message;
    private String status;
    private T data;

    public ResponseRecord() {}

    public ResponseRecord(String message, String status, T data) {        
        this.message = message;
        this.status = status;
        this.data = data;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {        
        this.data = data;
    }
}
