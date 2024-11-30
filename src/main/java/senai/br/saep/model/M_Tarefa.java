package senai.br.saep.model;


import jakarta.persistence.*;


@Entity
@Table(name = "tarefa")
public class M_Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String setor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private M_Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "prioridade_id", nullable = false)
    private M_Prioridade prioridade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public M_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(M_Usuario usuario) {
        this.usuario = usuario;
    }

    public M_Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(M_Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}

