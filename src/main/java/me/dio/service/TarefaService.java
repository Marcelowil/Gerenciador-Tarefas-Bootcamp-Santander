package me.dio.service;

import me.dio.domain.model.Tarefa;
import me.dio.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa create(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public Tarefa getById(Long id){
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa update (Long id, Tarefa tarefa){
        Tarefa tarefaAtualizada = tarefaRepository.findById(id).orElse(null);
        tarefaAtualizada.setTitulo(tarefa.getTitulo());
        tarefaAtualizada.setDescricao(tarefa.getDescricao());
        tarefaAtualizada.setFinalizada(tarefa.isFinalizada());
        tarefaRepository.save(tarefaAtualizada);

        return tarefaAtualizada;
    }
    public void delete(Long id){
        tarefaRepository.deleteById(id);
    }

    public boolean existById(Long id){
        return tarefaRepository.existsTarefaById(id);
    }

    public List<Tarefa> findAllTarefas(){
        return tarefaRepository.findAll();
    }

    public List<Tarefa> findByTarefaFinalizada(String tarefa){
        if(tarefa.equalsIgnoreCase("true")){
            return tarefaRepository.findTarefaByFinalizada();
        }
        else {
           return tarefaRepository.findTarefaByPendentes();
        }
    }
}
