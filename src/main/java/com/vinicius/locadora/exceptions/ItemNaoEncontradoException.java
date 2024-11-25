package com.vinicius.locadora.exceptions;

public class ItemNaoEncontradoException extends RuntimeException{
    public ItemNaoEncontradoException(int id){
        super("Não foi possível encontrar o item de id: " + id);
    }
}
