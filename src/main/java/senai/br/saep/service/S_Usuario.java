package senai.br.saep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.br.saep.model.M_Usuario;
import senai.br.saep.repository.R_Usuario;

import java.util.List;

@Service
public class S_Usuario {
    private final R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario) {
        this.r_usuario = r_usuario;
    }

    public List<M_Usuario> listarUsuarios() {
        return r_usuario.findAll();
    }

    public M_Usuario salvarUsuario(M_Usuario m_usuario) {
        return r_usuario.save(m_usuario);
    }
}


