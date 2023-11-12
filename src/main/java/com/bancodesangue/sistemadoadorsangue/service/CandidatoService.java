package com.bancodesangue.sistemadoadorsangue.service;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Optional<Candidato> findById(Long id) {
        return candidatoRepository.findById(id);
    }

    public Candidato save(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public Candidato update(Long id, Candidato candidatoDetails) {
        Candidato candidato = candidatoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Candidato com ID " + id + " não existe."));
        
        candidato.setNome(candidatoDetails.getNome());
        candidato.setCpf(candidatoDetails.getCpf());
        candidato.setRg(candidatoDetails.getRg());
        candidato.setDataNasc(candidatoDetails.getDataNasc());
        candidato.setSexo(candidatoDetails.getSexo());
        candidato.setMae(candidatoDetails.getMae());
        candidato.setPai(candidatoDetails.getPai());
        candidato.setEmail(candidatoDetails.getEmail());
        candidato.setCep(candidatoDetails.getCep());
        candidato.setEndereco(candidatoDetails.getEndereco());
        candidato.setNumero(candidatoDetails.getNumero());
        candidato.setBairro(candidatoDetails.getBairro());
        candidato.setCidade(candidatoDetails.getCidade());
        candidato.setEstado(candidatoDetails.getEstado());
        candidato.setTelefoneFixo(candidatoDetails.getTelefoneFixo());
        candidato.setCelular(candidatoDetails.getCelular());
        candidato.setAltura(candidatoDetails.getAltura());
        candidato.setPeso(candidatoDetails.getPeso());
        candidato.setTipoSanguineo(candidatoDetails.getTipoSanguineo());

        return candidatoRepository.save(candidato);
    }


    public void delete(Long id) {
        Candidato candidato = candidatoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Candidato com ID " + id + " não existe."));
        candidatoRepository.delete(candidato);
    }

    // Você pode adicionar outros métodos de serviço que façam sentido para a lógica do seu negócio
    // Por exemplo, métodos para calcular a porcentagem de candidatos obesos, a quantidade de possíveis doadores por tipo sanguíneo, etc.
}
