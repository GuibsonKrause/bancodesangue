import java.util.Map;

public class ResultadoDTO {
    private Map<String, Long> candidatosPorEstado;
    private Map<String, Double> imcMedioPorFaixaEtaria;
    private double percentualObesos;
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

    public double getPercentualObesos() {
        return percentualObesos;
    }

    public void setPercentualObesos(double percentualObesos) {
        this.percentualObesos = percentualObesos;
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
