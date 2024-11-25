package com.vinicius.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.locadora.DTO.RequestDTO.TarefaRequestDTO;
import com.vinicius.locadora.DTO.ResponseDTO.TarefaResponseDTO;
import com.vinicius.locadora.service.TarefaService;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService atorService;

    @PostMapping("/novo")
    public ResponseEntity<TarefaResponseDTO> salvar(@RequestBody TarefaRequestDTO request){
        return atorService.salvar(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable int id){
        return atorService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TarefaResponseDTO>> buscarTodos(){
        return atorService.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@RequestBody TarefaRequestDTO request){
        return atorService.atualizar(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id){
        return atorService.deletar(id);
    }

    @GetMapping("/{itemToDownID}/{itemToUpID}")
    public void orderItems(@PathVariable int itemToDownID, @PathVariable int itemToUpID){
        atorService.orderItems(itemToDownID, itemToUpID);
    }
}
