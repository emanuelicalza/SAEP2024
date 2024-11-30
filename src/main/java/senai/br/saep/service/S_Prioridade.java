package senai.br.saep.service;

import org.springframework.stereotype.Service;
import senai.br.saep.model.M_Prioridade;
import senai.br.saep.repository.R_Prioridade;

import java.util.List;

@Service
public class S_Prioridade {
    private final R_Prioridade r_prioridade;

    public S_Prioridade(R_Prioridade r_prioridade) {
        this.r_prioridade = r_prioridade;
    }

    public List<M_Prioridade> listarPrioridades() {
        return r_prioridade.findAll();
    }
}

