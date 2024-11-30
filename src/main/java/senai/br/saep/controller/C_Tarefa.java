package senai.br.saep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import senai.br.saep.model.M_Tarefa;
import senai.br.saep.service.S_Prioridade;
import senai.br.saep.service.S_Tarefa;
import senai.br.saep.service.S_Usuario;


@Controller
public class C_Tarefa {
    private final S_Tarefa s_tarefa;
    private final S_Usuario s_usuario;
    private final S_Prioridade s_prioridade;

    public C_Tarefa(S_Tarefa s_tarefa, S_Usuario s_usuario, S_Prioridade s_prioridade) {
        this.s_tarefa = s_tarefa;
        this.s_usuario = s_usuario;
        this.s_prioridade = s_prioridade;
    }

    @GetMapping("/tarefas/cadastrar")
    public String formularioCadastro(Model model) {
        model.addAttribute("m_tarefa", new M_Tarefa());
        model.addAttribute("usuarios", s_usuario.listarUsuarios());
        model.addAttribute("prioridades", s_prioridade.listarPrioridades());
        return "cadastroTarefas";
    }

    @PostMapping("/tarefas/cadastrar")
    public String salvarTarefa(M_Tarefa m_tarefa) {
        s_tarefa.salvarTarefa(m_tarefa);
        return "redirect:/tarefas/cadastrar";
    }
}
