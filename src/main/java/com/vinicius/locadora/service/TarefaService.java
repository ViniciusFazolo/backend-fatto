package com.vinicius.locadora.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vinicius.locadora.DTO.RequestDTO.TarefaRequestDTO;
import com.vinicius.locadora.DTO.ResponseDTO.TarefaResponseDTO;
import com.vinicius.locadora.exceptions.ObjetoNaoEncontradoException;
import com.vinicius.locadora.exceptions.PreencherTodosCamposException;
import com.vinicius.locadora.mapper.TarefaMapper;
import com.vinicius.locadora.model.Tarefa;
import com.vinicius.locadora.repository.TarefaRepository;

@Service
public class TarefaService{
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public ResponseEntity<TarefaResponseDTO> salvar(TarefaRequestDTO request) {
        if(request.nomeTarefa() == null || request.nomeTarefa().isBlank() || request.custo() == null || request.dataLimite() == null){
            throw new PreencherTodosCamposException();
        }

        Integer lastValueOrdemApresentacao = tarefaRepository.findMaxOrdemApresentacao() + 1;

        Tarefa obj = new Tarefa(null, request.nomeTarefa(), request.custo(), request.dataLimite(), lastValueOrdemApresentacao);
        obj = tarefaRepository.save(obj);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaMapper.toDTO(obj));
    }

    public ResponseEntity<TarefaResponseDTO> buscarPorId(int id){
        Tarefa obj = tarefaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possível encontrar o tarefa de id: " + id));
        return ResponseEntity.ok().body(tarefaMapper.toDTO(obj));
    }

    public ResponseEntity<List<TarefaResponseDTO>> buscarTodos(){
        return ResponseEntity.ok().body(tarefaRepository.findAllByOrdemApresentacao().stream().map(tarefaMapper::toDTO).collect(Collectors.toList()));
    }       

    public ResponseEntity<TarefaResponseDTO> atualizar(TarefaRequestDTO request){
        if(request.nomeTarefa() == null || request.nomeTarefa().isBlank() || request.custo() == null || request.dataLimite() == null){
            throw new PreencherTodosCamposException();
        }

        Tarefa obj = tarefaRepository.findById(request.id()).orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possível encontrar o tarefa de id: " + request.id()));
        
        obj.setCusto(request.custo());
        obj.setDataLimite(request.dataLimite());
        obj.setNomeTarefa(request.nomeTarefa());

        tarefaRepository.save(obj);

        return ResponseEntity.ok().body(tarefaMapper.toDTO(obj));
    }

    public ResponseEntity<String> deletar(int id){
        Tarefa obj = tarefaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possível encontrar o tarefa de id:" + id));
        tarefaRepository.delete(obj);

        List<Tarefa> remainingItems = tarefaRepository.findAllByOrdemApresentacao();

        int newOrder = 1;
        for (Tarefa tarefa : remainingItems) {
            tarefa.setOrdemApresentacao(newOrder++);
        }
        tarefaRepository.saveAll(remainingItems);
     
        return ResponseEntity.ok().body("Registro excluído com sucesso");
    }

    public void orderItems(int itemToDownID, int itemToUpID){
        Tarefa itemToDown = tarefaRepository.findById(itemToDownID).orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possível encontrar a tarefa de id: " + itemToDownID));
        Tarefa itemToUp = tarefaRepository.findById(itemToUpID).orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possível encontrar a tarefa de id: " + itemToUpID));
   
        int aux = itemToDown.getOrdemApresentacao();

        itemToDown.setOrdemApresentacao(itemToUp.getOrdemApresentacao());
        itemToUp.setOrdemApresentacao(aux);

        tarefaRepository.save(itemToDown);
        tarefaRepository.save(itemToUp);
    }
}
