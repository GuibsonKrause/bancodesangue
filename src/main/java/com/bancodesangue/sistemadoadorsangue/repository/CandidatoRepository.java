package com.bancodesangue.sistemadoadorsangue.repository;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    // Métodos de pesquisa específicos ...
}
