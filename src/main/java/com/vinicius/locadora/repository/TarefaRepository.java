package com.vinicius.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.vinicius.locadora.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    @Query("SELECT COALESCE(MAX(t.ordemApresentacao), 0) FROM Tarefa t")
    Integer findMaxOrdemApresentacao();

    @Query("SELECT t FROM Tarefa t ORDER BY t.ordemApresentacao")
    List<Tarefa> findAllByOrdemApresentacao();
}
