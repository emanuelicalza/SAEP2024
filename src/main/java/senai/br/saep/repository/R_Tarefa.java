package senai.br.saep.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.br.saep.model.M_Tarefa;

import java.util.List;

@Repository
public interface R_Tarefa extends JpaRepository<M_Tarefa, Long> {
    List<M_Tarefa> findByStatus(String status);
}


