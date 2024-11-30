package senai.br.saep.service;


import org.springframework.stereotype.Service;
import senai.br.saep.model.M_Tarefa;
import senai.br.saep.repository.R_Tarefa;

import java.util.List;


@Service
public class S_Tarefa {
    private final R_Tarefa r_tarefa;

    public S_Tarefa(R_Tarefa r_tarefa) {
        this.r_tarefa = r_tarefa;
    }

    public List<M_Tarefa> listarTarefas() {
        return r_tarefa.findAll();
    }

    public M_Tarefa salvarTarefa(M_Tarefa m_tarefa) {
        return r_tarefa.save(m_tarefa);
    }
}

