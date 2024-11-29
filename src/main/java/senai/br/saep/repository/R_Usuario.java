package senai.br.saep.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.br.saep.model.M_Usuario;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
}
