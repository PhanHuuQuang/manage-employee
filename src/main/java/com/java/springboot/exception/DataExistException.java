package com.java.springboot.exception;

public class DataExistException extends RuntimeException{

    public DataExistException(String message){
        super(message);
    }
}