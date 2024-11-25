package com.vinicius.locadora.mapper;

import org.mapstruct.Mapper;

import com.vinicius.locadora.DTO.RequestDTO.TarefaRequestDTO;
import com.vinicius.locadora.DTO.ResponseDTO.TarefaResponseDTO;
import com.vinicius.locadora.model.Tarefa;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    
    TarefaResponseDTO toDTO(Tarefa ator);
    Tarefa toEntity(TarefaRequestDTO tarefaRequestDTO);
}
