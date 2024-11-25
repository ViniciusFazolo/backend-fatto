package com.vinicius.locadora.exceptions;

public class PreencherTodosCamposException extends RuntimeException{
    public PreencherTodosCamposException(){
        super("Preencha todos os campos");
    }
}
