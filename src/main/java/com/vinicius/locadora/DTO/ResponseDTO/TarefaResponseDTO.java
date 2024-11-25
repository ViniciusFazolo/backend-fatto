package com.vinicius.locadora.DTO.ResponseDTO;

import java.time.LocalDate;

public record TarefaResponseDTO(
    Integer id,
    String nomeTarefa,
    Double custo,
    LocalDate dataLimite,
    Integer ordemApresentacao
) {
    
}
