package com.foursys.fourstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidValueException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

	public InvalidValueException(String msg) {
        super(msg);
    }
}
