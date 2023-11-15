import java.util.Map;

public class ResultadoDTO {
    private Map<String, Long> candidatosPorEstado;
    private Map<String, Double> imcMedioPorFaixaEtaria;
    private double percentualObesosHomens;
    private double percentualObesosMulheres;
    private Map<String, Double> mediaIdadePorTipoSanguineo;
    private Map<String, Long> possiveisDoadoresPorTipoSanguineo;

    public Map<String, Long> getCandidatosPorEstado() {
        return candidatosPorEstado;
    }

    public void setCandidatosPorEstado(Map<String, Long> candidatosPorEstado) {
        this.candidatosPorEstado = candidatosPorEstado;
    }

    public Map<String, Double> getImcMedioPorFaixaEtaria() {
        return imcMedioPorFaixaEtaria;
    }

    public void setImcMedioPorFaixaEtaria(Map<String, Double> imcMedioPorFaixaEtaria) {
        this.imcMedioPorFaixaEtaria = imcMedioPorFaixaEtaria;
    }

    public double getPercentualObesosHomens() {
        return percentualObesosHomens;
    }

    public void setPercentualObesosHomens(double percentualObesosHomens) {
        this.percentualObesosHomens = percentualObesosHomens;
    }

    public double getPercentualObesosMulheres() {
        return percentualObesosMulheres;
    }

    public void setPercentualObesosMulheres(double percentualObesosMulheres) {
        this.percentualObesosMulheres = percentualObesosMulheres;
    }

    public Map<String, Double> getMediaIdadePorTipoSanguineo() {
        return mediaIdadePorTipoSanguineo;
    }

    public void setMediaIdadePorTipoSanguineo(Map<String, Double> mediaIdadePorTipoSanguineo) {
        this.mediaIdadePorTipoSanguineo = mediaIdadePorTipoSanguineo;
    }

    public Map<String, Long> getPossiveisDoadoresPorTipoSanguineo() {
        return possiveisDoadoresPorTipoSanguineo;
    }

    public void setPossiveisDoadoresPorTipoSanguineo(Map<String, Long> possiveisDoadoresPorTipoSanguineo) {
        this.possiveisDoadoresPorTipoSanguineo = possiveisDoadoresPorTipoSanguineo;
    }
}
