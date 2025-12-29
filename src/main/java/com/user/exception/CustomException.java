package com.user.exception;

public class CustomException extends RuntimeException{

    public CustomException(){
        super("Resource not found");
    }

    public CustomException(String msg){
        super(msg);
    }


}
