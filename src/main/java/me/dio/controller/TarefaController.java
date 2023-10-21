package me.dio.controller;

import me.dio.domain.model.Tarefa;
import me.dio.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable("id") Long id){
        var tarefa = tarefaService.getById(id);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/status={status}")
    public List<Tarefa> findByStatus(@PathVariable("status") String status){
        return tarefaService.findByTarefaFinalizada(status);
    }

    @GetMapping("/all")
    public List<Tarefa> findAll(){
        return tarefaService.findAllTarefas();
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefaCriada){
        var tarefa = tarefaService.create(tarefaCriada);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Tarefa> delete(@PathVariable("id") Long id){
        if(tarefaService.existById(id)) {
            tarefaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa){
        tarefaService.update(id, tarefa);
        return ResponseEntity.ok(tarefa);
    }


}
