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
    private final R_Prioridade r_prioridade; // Injeção do repositório de Prioridade

    @Autowired
    public C_Tarefa(S_Tarefa s_tarefa, R_Usuario r_usuario, R_Tarefa r_tarefa, R_Prioridade r_prioridade) {
        this.s_tarefa = s_tarefa;
        this.r_usuario = r_usuario;
        this.r_tarefa = r_tarefa;
        this.r_prioridade = r_prioridade; // Inicializando o repositório de Prioridade
    }

    // Exibir todas as tarefas
    @GetMapping("/tarefas/gerenciar")
    public String gerirTarefas(Model model) {
        List<M_Tarefa> tarefas = s_tarefa.listarTarefas(); // Lista todas as tarefas
        List<M_Usuario> usuarios = r_usuario.findAll(); // Lista todos os usuários para o seletor

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("usuarios", usuarios);
        return "gerirTarefas"; // Nome da página que exibirá as tarefas
    }

    // Exibir a página de cadastro de tarefas
    @GetMapping("/gerirTarefas/cadastrar")
    public String cadastroTarefa(Model model) {
        List<M_Usuario> usuarios = r_usuario.findAll(); // Busca todos os usuários
        List<M_Prioridade> prioridades = r_prioridade.findAll(); // Busca todas as prioridades

        model.addAttribute("usuarios", usuarios); // Passa a lista de usuários para o template
        model.addAttribute("prioridades", prioridades); // Passa a lista de prioridades para o template
        return "cadastroTarefa"; // Nome do arquivo de template
    }

    // Método para criar a tarefa (POST)
    @PostMapping("/gerirTarefas/cadastrar")
    public String criarTarefa(@RequestParam("descricao") String descricao,
                              @RequestParam("setor") String setor,
                              @RequestParam("usuario.id") Long usuarioId,
                              @RequestParam("prioridade.id") Long prioridadeId) {
        // Buscar objetos de usuário e prioridade
        M_Usuario usuario = r_usuario.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        M_Prioridade prioridade = r_prioridade.findById(prioridadeId).orElseThrow(() -> new RuntimeException("Prioridade não encontrada"));

        // Criar e salvar a tarefa
        M_Tarefa tarefa = new M_Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setSetor(setor);
        tarefa.setUsuario(usuario);
        tarefa.setPrioridade(prioridade);

        s_tarefa.salvarTarefa(tarefa);

        return "redirect:/gerirTarefas"; // Redireciona para a página de gerenciamento de tarefas
    }


    // Alterar o status da tarefa
    @PostMapping("/gerirTarefas/alterarStatus")
    public String alterarStatus(@RequestParam("tarefaId") Long tarefaId,
                                @RequestParam("status") String status) {
        M_Tarefa tarefa = s_tarefa.buscarTarefaPorId(tarefaId);
        if (tarefa != null) {
            tarefa.setStatus(status); // Altera o status da tarefa
            s_tarefa.salvarTarefa(tarefa);
        }
        return "redirect:/gerirTarefas"; // Redireciona para a página de gerenciamento de tarefas
    }

    // Editar tarefa
    @GetMapping("/gerirTarefas/editarTarefa")
    public String editarTarefa(@RequestParam("id") Long id, Model model) {
        M_Tarefa tarefa = s_tarefa.buscarTarefaPorId(id);
        if (tarefa != null) {
            List<M_Usuario> usuarios = r_usuario.findAll();
            model.addAttribute("tarefa", tarefa);
            model.addAttribute("usuarios", usuarios);
            return "editarTarefa"; // Página para editar tarefa
        }
        return "redirect:/gerirTarefas"; // Se tarefa não encontrada, redireciona
    }

    // Atualizar tarefa
    @PostMapping("/gerirTarefas/atualizarTarefa")
    public String atualizarTarefa(@ModelAttribute M_Tarefa tarefa) {
        s_tarefa.salvarTarefa(tarefa); // Atualiza a tarefa
        return "redirect:/gerirTarefas"; // Redireciona para a página de gerenciamento de tarefas
    }

    // Excluir tarefa
    @PostMapping("/gerirTarefas/excluirTarefa")
    @ResponseBody
    public String excluirTarefa(@RequestParam("id") Long id) {
        s_tarefa.excluirTarefa(id); // Exclui a tarefa
        return "sucesso"; // Retorna sucesso
    }





}
