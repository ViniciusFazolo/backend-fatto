package com.vinicius.locadora.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException{
    public ObjetoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
