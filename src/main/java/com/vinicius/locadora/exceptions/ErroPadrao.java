package com.vinicius.locadora.exceptions;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroPadrao {
    private Instant timestamp;
    private Integer status;
    private String message;
    private String path;
}
