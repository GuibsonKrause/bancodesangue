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

        ResultadoDTO resultado = new ResultadoDTO();
        resultado.setCandidatosPorEstado(candidatosPorEstado);
        resultado.setImcMedioPorFaixaEtaria(imcMedioPorFaixaEtaria);
        resultado.setPercentualObesos(percentualObesos);
        resultado.setMediaIdadePorTipoSanguineo(mediaIdadePorTipoSanguineo);
        resultado.setPossiveisDoadoresPorTipoSanguineo(possiveisDoadoresPorTipoSanguineo);

        return resultado;
    }

    private Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Candidato> candidatos) {
        final int[] faixasDeIdade = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    
        return candidatos.stream()
                .collect(Collectors.groupingBy(
                        candidato -> {
                            int idade = calcularIdade(candidato.getDataNasc());
                            return determinarFaixaEtaria(idade, faixasDeIdade);
                        },
                        Collectors.averagingDouble(
                                candidato -> calcularIMC(candidato.getPeso(), candidato.getAltura())
                        )
                ));
    }
    
    private String determinarFaixaEtaria(int idade, int[] faixasDeIdade) {
        for (int i = 0; i < faixasDeIdade.length - 1; i++) {
            if (idade >= faixasDeIdade[i] && idade < faixasDeIdade[i + 1]) {
                return faixasDeIdade[i] + "-" + (faixasDeIdade[i + 1] - 1);
            }
        }
        return faixasDeIdade[faixasDeIdade.length - 1] + "+";
    }
    
    private double calcularIMC(double peso, double altura) {
        if (altura <= 0) {
            return 0;
        }
        return peso / (altura * altura);
    }
    
    private int calcularIdade(Date dataNasc) {
        Calendar hoje = Calendar.getInstance();
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNasc);
        
        int diff = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        if (nascimento.get(Calendar.MONTH) > hoje.get(Calendar.MONTH) ||
                (nascimento.get(Calendar.MONTH) == hoje.get(Calendar.MONTH) && nascimento.get(Calendar.DATE) > hoje.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
    

    private double calcularPercentualObesos(List<Candidato> candidatos) {
        long totalObesos = candidatos.stream()
                                      .filter(candidato -> calcularIMC(candidato.getPeso(), candidato.getAltura()) > 30)
                                      .count();
        return totalObesos * 100.0 / candidatos.size();
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

}


   

}
