package senai.br.saep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import senai.br.saep.model.M_Usuario;
import senai.br.saep.service.S_Usuario;

@Controller
public class C_Usuario {
    private final S_Usuario s_usuario;

    public C_Usuario(S_Usuario s_usuario) {
        this.s_usuario = s_usuario;
    }

    @GetMapping("/usuarios")
    public String formularioCadastro(Model model) {
        model.addAttribute("m_usuario", new M_Usuario());
        return "cadastroUsuarios";
    }

    @PostMapping("/usuarios")
    public String salvarUsuario(M_Usuario m_usuario) {
        s_usuario.salvarUsuario(m_usuario);
        return "redirect:/usuarios";
    }
}

