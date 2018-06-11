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
public class UnauthorizedTokenException extends Exception {
    private final static String _default = "Unauthorized token";
    public UnauthorizedTokenException() {
        super(_default);
    }
    
    public UnauthorizedTokenException(String message) {
        super(message);
    }
    
    public UnauthorizedTokenException(Throwable cause) {
        super(cause);
    }
}
