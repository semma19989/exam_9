package com.attractor.exam_9.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Getter
@Setter
public class PaswordNotFoundException extends RuntimeException {

    private String resource;
    private int id;

    public PaswordNotFoundException(String resource, int id) {
        super();
        this.resource = resource;
        this.id = id;
    }
}
