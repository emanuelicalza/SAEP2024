package senai.br.saep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import senai.br.saep.model.M_Prioridade;
import senai.br.saep.model.M_Tarefa;
import senai.br.saep.model.M_Usuario;
import senai.br.saep.repository.R_Prioridade;
import senai.br.saep.repository.R_Tarefa;
import senai.br.saep.repository.R_Usuario;
import senai.br.saep.service.S_Tarefa;

import java.util.List;

@Controller
public class C_Tarefa {

    private final S_Tarefa s_tarefa;
    private final R_Usuario r_usuario;
    private final R_Tarefa r_tarefa;
    private final R_Prioridade r_prioridade;

    @Autowired
    public C_Tarefa(S_Tarefa s_tarefa, R_Usuario r_usuario, R_Tarefa r_tarefa, R_Prioridade r_prioridade) {
        this.s_tarefa = s_tarefa;
        this.r_usuario = r_usuario;
        this.r_tarefa = r_tarefa;
        this.r_prioridade = r_prioridade;
    }

    // Exibir todas as tarefas
    @GetMapping("/tarefas/gerenciar")
    public String gerirTarefas(Model model) {
        List<M_Tarefa> tarefas = s_tarefa.listarTarefas();
        List<M_Usuario> usuarios = r_usuario.findAll();

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("usuarios", usuarios);
        return "gerirTarefas";
    }


    @GetMapping("/gerirTarefas/cadastrar")
    public String cadastroTarefa(Model model) {
        List<M_Usuario> usuarios = r_usuario.findAll();
        List<M_Prioridade> prioridades = r_prioridade.findAll();

        model.addAttribute("usuarios", usuarios); // Passa a lista de usuários para o template
        model.addAttribute("prioridades", prioridades); // Passa a lista de prioridades para o template
        return "cadastroTarefa"; // Nome do arquivo de template
    }


    @PostMapping("/gerirTarefas/cadastrar")
    public String criarTarefa(@RequestParam("descricao") String descricao,
                              @RequestParam("setor") String setor,
                              @RequestParam("usuario.id") Long usuarioId,
                              @RequestParam("prioridade.id") Long prioridadeId) {

        M_Usuario usuario = r_usuario.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        M_Prioridade prioridade = r_prioridade.findById(prioridadeId).orElseThrow(() -> new RuntimeException("Prioridade não encontrada"));


        M_Tarefa tarefa = new M_Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setSetor(setor);
        tarefa.setUsuario(usuario);
        tarefa.setPrioridade(prioridade);

        tarefa.setStatus("Pendente");

        s_tarefa.salvarTarefa(tarefa);

        return "redirect:/gerirTarefas/cadastrar";
    }




    @PostMapping("/gerirTarefas/alterarStatus")
    public String alterarStatus(@RequestParam("tarefaId") Long tarefaId,
                                @RequestParam("status") String status) {
        M_Tarefa tarefa = s_tarefa.buscarTarefaPorId(tarefaId);
        if (tarefa != null) {
            tarefa.setStatus(status);
            s_tarefa.salvarTarefa(tarefa);
        }
        return "redirect:/gerirTarefas";
    }






}
