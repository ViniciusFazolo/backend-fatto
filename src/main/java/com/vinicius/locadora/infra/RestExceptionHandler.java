package com.vinicius.locadora.infra;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vinicius.locadora.exceptions.ErroPadrao;
import com.vinicius.locadora.exceptions.ObjetoNaoEncontradoException;
import com.vinicius.locadora.exceptions.PreencherTodosCamposException;
import com.vinicius.locadora.exceptions.RelacionamentoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(PreencherTodosCamposException.class)
    public ResponseEntity<ErroPadrao> PreencherTodosOsCamposHandler(PreencherTodosCamposException e, HttpServletRequest req) {
        ErroPadrao err = new ErroPadrao();
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    //item
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> ObjetoNaoEncontradoHandler(ObjetoNaoEncontradoException e, HttpServletRequest req) {
        ErroPadrao err = new ErroPadrao();
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(RelacionamentoException.class)
    public ResponseEntity<ErroPadrao> RelacionamentoTituloHandler(RelacionamentoException e, HttpServletRequest req) {
        ErroPadrao err = new ErroPadrao();
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
