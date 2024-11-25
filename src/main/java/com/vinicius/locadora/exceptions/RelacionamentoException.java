package com.vinicius.locadora.exceptions;

public class RelacionamentoException extends RuntimeException{
    public RelacionamentoException(){
        super("Não é possível excluir: registro relacionado a outra tabela.");
    }
}
