package br.com.alura.ScreenMatch.Model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private double avalicao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio){
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numero();

        try{
            this.avalicao = Double.valueOf(dadosEpisodio.avaliacao());
        }catch (NumberFormatException ex){
            this.avalicao = 0.0;
        }

        try{
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLacamento());
        }catch (DateTimeException da){
            this.dataLancamento = null;
        }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public double getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(double avalicao) {
        this.avalicao = avalicao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setDataDeLancamento(LocalDate dataLancamento){
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "temporada = " + temporada +
                ", titulo = " + titulo + '\'' +
                ", numeroEpisodio = " + numeroEpisodio +
                ", avalicao = " + avalicao +
                ", dataLancamento = " + dataLancamento;
    }
}
