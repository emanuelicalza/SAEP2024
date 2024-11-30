package senai.br.saep.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.br.saep.model.M_Tarefa;
import senai.br.saep.repository.R_Tarefa;

import java.util.List;

import java.util.Optional;

@Service
public class S_Tarefa {

    private final R_Tarefa r_tarefa;

    @Autowired
    public S_Tarefa(R_Tarefa r_tarefa) {
        this.r_tarefa = r_tarefa;
    }


    public List<M_Tarefa> listarTarefas() {
        return r_tarefa.findAll();
    }


    public M_Tarefa buscarTarefaPorId(Long id) {
        Optional<M_Tarefa> tarefa = r_tarefa.findById(id);
        return tarefa.orElse(null);
    }


    public void salvarTarefa(M_Tarefa tarefa) {
        r_tarefa.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        r_tarefa.deleteById(id);
    }
}



