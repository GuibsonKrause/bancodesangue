package com.bancodesangue.sistemadoadorsangue.service;

import com.bancodesangue.sistemadoadorsangue.model.Candidato;
import com.bancodesangue.sistemadoadorsangue.repository.CandidatoRepository;
import org.springframework.stereotype.Service;
import com.bancodesangue.sistemadoadorsangue.dto.ResultadoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

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

        Map<String, Double> imcMedioPorFaixaEtaria = calcularIMCMedioPorFaixaEtaria(candidatos);

        Map<String, Double> mediaIdadePorTipoSanguineo = calcularMediaIdadePorTipoSanguineo(candidatos);

        Map<String, Long> possiveisDoadoresPorTipoSanguineo = calcularPossiveisDoadoresPorTipoSanguineo(candidatos);

        Map<String, Double> percentuaisObesos = calcularPercentualObesosPorSexo(candidatos);

        ResultadoDTO resultado = new ResultadoDTO();
        resultado.setCandidatosPorEstado(candidatosPorEstado);
        resultado.setImcMedioPorFaixaEtaria(imcMedioPorFaixaEtaria);
        resultado.setPercentualObesosHomens(percentuaisObesos.get("Homens"));
        resultado.setPercentualObesosMulheres(percentuaisObesos.get("Mulheres"));
        resultado.setMediaIdadePorTipoSanguineo(mediaIdadePorTipoSanguineo);
        resultado.setPossiveisDoadoresPorTipoSanguineo(possiveisDoadoresPorTipoSanguineo);

        return resultado;
    }

    private Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Candidato> candidatos) {
        final int[] faixasDeIdade = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    
        Map<String, Double> imcMedioPorFaixaEtaria = candidatos.stream()
            .collect(Collectors.groupingBy(
                candidato -> {
                    int idade = calcularIdade(candidato.getDataNasc());
                    return determinarFaixaEtaria(idade, faixasDeIdade);
                },
                Collectors.averagingDouble(
                    candidato -> calcularIMC(candidato.getPeso(), candidato.getAltura())
                )
            ));
    
        imcMedioPorFaixaEtaria.replaceAll((faixaEtaria, imcMedio) -> arredondar(imcMedio));
    
        return imcMedioPorFaixaEtaria;
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

    private Map<String, Double> calcularPercentualObesosPorSexo(List<Candidato> candidatos) {
        long totalHomens = candidatos.stream().filter(c -> "Masculino".equalsIgnoreCase(c.getSexo())).count();
        long totalMulheres = candidatos.stream().filter(c -> "Feminino".equalsIgnoreCase(c.getSexo())).count();
    
        long obesosHomens = candidatos.stream()
            .filter(c -> "Masculino".equalsIgnoreCase(c.getSexo()) && calcularIMC(c.getPeso(), c.getAltura()) > 30)
            .count();
        long obesosMulheres = candidatos.stream()
            .filter(c -> "Feminino".equalsIgnoreCase(c.getSexo()) && calcularIMC(c.getPeso(), c.getAltura()) > 30)
            .count();
    
        Map<String, Double> percentuaisObesos = new HashMap<>();
        percentuaisObesos.put("Homens", arredondar(totalHomens > 0 ? (double) obesosHomens / totalHomens * 100 : 0));
        percentuaisObesos.put("Mulheres", arredondar(totalMulheres > 0 ? (double) obesosMulheres / totalMulheres * 100 : 0));
    
        return percentuaisObesos;
    }
    
    private double arredondar(double valor) {
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
    
    private Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Candidato> candidatos) {
        Map<String, Double> mediaIdadePorTipoSanguineo = candidatos.stream()
            .collect(Collectors.groupingBy(
                Candidato::getTipoSanguineo,
                Collectors.averagingInt(candidato -> calcularIdade(candidato.getDataNasc()))
            ));
    
        mediaIdadePorTipoSanguineo.replaceAll((tipoSanguineo, mediaIdade) -> arredondar(mediaIdade));
    
        return mediaIdadePorTipoSanguineo;
    }

    private int calcularIdade(Date dataNasc) {
        LocalDate dataNascimento = dataNasc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private Map<String, Long> calcularPossiveisDoadoresPorTipoSanguineo(List<Candidato> candidatos) {
        Map<String, List<String>> tipoSanguineoParaDoar = Map.of(
            "A+", List.of("A+", "AB+"),
            "A-", List.of("A+", "A-", "AB+", "AB-"),
            "B+", List.of("B+", "AB+"),
            "B-", List.of("B+", "B-", "AB+", "AB-"),
            "AB+", List.of("AB+"),
            "AB-", List.of("AB+", "AB-"),
            "O+", List.of("A+", "B+", "O+", "AB+"),
            "O-", List.of("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        );
    
        Map<String, Long> possiveisDoadoresPorTipoSanguineo = new HashMap<>();
    
        tipoSanguineoParaDoar.values().stream()
            .flatMap(Collection::stream)
            .distinct()
            .forEach(receptor -> possiveisDoadoresPorTipoSanguineo.put(receptor, 0L));
    
        candidatos.forEach(candidato -> {
            int idade = calcularIdade(candidato.getDataNasc());
            if (idade < 16 || idade > 69 || candidato.getPeso() < 50) {
                return;
            }
            List<String> receptores = tipoSanguineoParaDoar.get(candidato.getTipoSanguineo());
            if (receptores != null) {
                receptores.forEach(receptor -> {
                    long count = possiveisDoadoresPorTipoSanguineo.getOrDefault(receptor, 0L);
                    possiveisDoadoresPorTipoSanguineo.put(receptor, count + 1);
                });
            }
        });
    
        return possiveisDoadoresPorTipoSanguineo;
    }

}
