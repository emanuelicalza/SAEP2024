package senai.br.saep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.br.saep.model.M_Prioridade;

@Repository
public interface R_Prioridade extends JpaRepository<M_Prioridade, Long> {
}
