package com.medi_connect.Medi_Connect.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String s, Long id){
        super("Doctor Not Exist With Id: "+id);
    }
}
