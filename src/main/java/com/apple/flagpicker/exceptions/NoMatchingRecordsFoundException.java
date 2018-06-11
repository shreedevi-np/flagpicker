/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.exceptions;

/**
 *
 * @author Shree
 */
public class NoMatchingRecordsFoundException extends RuntimeException {
    
    public NoMatchingRecordsFoundException() {
        super();
    }

    public NoMatchingRecordsFoundException(String message) {
        super(message);
    }

    public NoMatchingRecordsFoundException(Throwable cause) {
        super(cause);
    }
}
