package br.ifpe.proext.model;

public class Edital {

    private String titulo;
    private Long numero;
    private String data;
    private String inicioSubmissao;
    private String fimSubmissao;
    private String inicioAvaliacao;
    private String fimAvaliacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInicioSubmissao() {
        return inicioSubmissao;
    }

    public void setInicioSubmissao(String inicioSubmissao) {
        this.inicioSubmissao = inicioSubmissao;
    }

    public String getFimSubmissao() {
        return fimSubmissao;
    }

    public void setFimSubmissao(String fimSubmissao) {
        this.fimSubmissao = fimSubmissao;
    }

    public String getInicioAvaliacao() {
        return inicioAvaliacao;
    }

    public void setInicioAvaliacao(String inicioAvaliacao) {
        this.inicioAvaliacao = inicioAvaliacao;
    }

    public String getFimAvaliacao() {
        return fimAvaliacao;
    }

    public void setFimAvaliacao(String fimAvaliacao) {
        this.fimAvaliacao = fimAvaliacao;
    }

    public Edital() {
    }

}
