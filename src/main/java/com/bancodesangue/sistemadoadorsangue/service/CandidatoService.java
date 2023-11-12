package com.bancodesangue.sistemadoadorsangue.service;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    @Autowired
    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public List<Candidato> findAll() {
        return candidatoRepository.findAll();
    }

    public List<Candidato> findByEstado(String estado) {
        return candidatoRepository.findByEstado(estado);
    }

    public Double findAverageIMCByAgeRange(Date startDate, Date endDate) {
        return candidatoRepository.findAverageIMCByAgeRange(startDate, endDate);
    }

    public Long countObesosBySexo(String sexo) {
        return candidatoRepository.countObesosBySexo(sexo);
    }

    public Long countByTipoSanguineo(String tipoSanguineo) {
        return candidatoRepository.countByTipoSanguineo(tipoSanguineo);
    }

    // Você pode adicionar outros métodos de serviço que façam sentido para a lógica do seu negócio
    // Por exemplo, métodos para calcular a porcentagem de candidatos obesos, a quantidade de possíveis doadores por tipo sanguíneo, etc.
}
