package senai.br.saep.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.br.saep.model.M_Tarefa;

@Repository
public interface R_Tarefa extends JpaRepository<M_Tarefa, Long> {
}

