package com.vinicius.locadora.DTO.RequestDTO;

import java.time.LocalDate;

public record TarefaRequestDTO(
    Integer id,
    String nomeTarefa,
    Double custo,
    LocalDate dataLimite,
    Integer ordemApresentacao
) {
    
}
