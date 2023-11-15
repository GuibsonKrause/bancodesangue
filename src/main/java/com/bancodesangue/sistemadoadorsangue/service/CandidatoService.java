package com.bancodesangue.sistemadoadorsangue.service;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bancodesangue.sistemadoadorsangue.dto.ResultadoDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<Candidato> findById(Long id) {
        return candidatoRepository.findById(id);
    }

    public Candidato save(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public void saveAll(List<Candidato> candidatos) {
        candidatoRepository.saveAll(candidatos);
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

    public ResultadoDTO processarCandidatos(List<Candidato> candidatos) {
        // Calcula o número de candidatos por estado
        Map<String, Long> candidatosPorEstado = candidatos.stream()
            .collect(Collectors.groupingBy(Candidato::getEstado, Collectors.counting()));

                // Calcula o IMC médio por faixa etária
        Map<String, Double> imcMedioPorFaixaEtaria = calcularIMCMedioPorFaixaEtaria(candidatos);

        // Calcula o percentual de obesos
        double percentualObesos = calcularPercentualObesos(candidatos);

        // Calcula a média de idade por tipo sanguíneo
        Map<String, Double> mediaIdadePorTipoSanguineo = calcularMediaIdadePorTipoSanguineo(candidatos);

        // Calcula a quantidade de possíveis doadores para cada tipo sanguíneo receptor
        Map<String, Long> possiveisDoadoresPorTipoSanguineo = calcularPossiveisDoadoresPorTipoSanguineo(candidatos);

        // Cria o DTO de resultado e preenche com os dados calculados
        ResultadoDTO resultado = new ResultadoDTO();
        resultado.setCandidatosPorEstado(candidatosPorEstado);
        resultado.setImcMedioPorFaixaEtaria(imcMedioPorFaixaEtaria);
        resultado.setPercentualObesos(percentualObesos);
        resultado.setMediaIdadePorTipoSanguineo(mediaIdadePorTipoSanguineo);
        resultado.setPossiveisDoadoresPorTipoSanguineo(possiveisDoadoresPorTipoSanguineo);

        return resultado;
    }

    private Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Candidato> candidatos) {
        // Implementar lógica para calcular o IMC médio por faixa etária
        return new HashMap<>();
    }

    private double calcularPercentualObesos(List<Candidato> candidatos) {
        // Implementar lógica para calcular o percentual de obesos
        return 0;
    }

        private Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Candidato> candidatos) {
        // Implementar lógica para calcular a média de idade por tipo sanguíneo
        return candidatos.stream()
            .collect(Collectors.groupingBy(Candidato::getTipoSanguineo,
                    Collectors.averagingInt(candidato -> calcularIdade(candidato.getDataNasc()))));
    }

    private Map<String, Long> calcularPossiveisDoadoresPorTipoSanguineo(List<Candidato> candidatos) {
        // Implementar lógica para calcular a quantidade de possíveis doadores por tipo sanguíneo
        // Isso pode envolver uma lógica específica dependendo das regras de compatibilidade sanguínea
        return new HashMap<>();
    }

    private int calcularIdade(Date dataNasc) {
        // Implementar lógica para calcular a idade do candidato a partir de sua data de nascimento
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);
        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        if (hoje.get(Calendar.DAY_OF_YEAR) < dataNascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--; // Ainda não fez aniversário este ano
        }
        return idade;
    }

}


   

}
