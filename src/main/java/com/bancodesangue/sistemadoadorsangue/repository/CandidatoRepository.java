package com.bancodesangue.sistemadoadorsangue.repository;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findByEstado(String estado);

    @Query("SELECT c FROM Candidato c WHERE c.dataNasc BETWEEN :startDate AND :endDate")
    List<Candidato> findByFaixaEtaria(Date startDate, Date endDate);

    @Query("SELECT AVG(c.peso / (c.altura * c.altura)) FROM Candidato c WHERE c.dataNasc BETWEEN :startDate AND :endDate")
    Double findAverageIMCByAgeRange(Date startDate, Date endDate);

    @Query("SELECT COUNT(c) FROM Candidato c WHERE c.sexo = :sexo AND c.peso / (c.altura * c.altura) > 30")
    Long countObesosBySexo(String sexo);

    Long countByTipoSanguineo(String tipoSanguineo);
    
}
