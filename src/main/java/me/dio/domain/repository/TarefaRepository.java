package me.dio.domain.repository;

import me.dio.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    boolean existsTarefaById(Long id);

    @Query("SELECT t FROM tb_tarefas t WHERE t.finalizada = true")
    List<Tarefa> findTarefaByFinalizada();

    @Query("SELECT t FROM tb_tarefas t WHERE t.finalizada = false")
    List<Tarefa> findTarefaByPendentes();
}
