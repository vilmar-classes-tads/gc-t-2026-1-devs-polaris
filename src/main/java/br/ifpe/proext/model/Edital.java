package br.ifpe.proext.model;

public class Edital {

    private String titulo;
    private String numero;
    private String data;
    private String inicioSumbissao;
    private String fimSumbissao;
    private String inicioAvaliacao;
    private String fimAvaliacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInicioSumbissao() {
        return inicioSumbissao;
    }

    public void setInicioSumbissao(String inicioSumbissao) {
        this.inicioSumbissao = inicioSumbissao;
    }

    public String getFimSumbissao() {
        return fimSumbissao;
    }

    public void setFimSumbissao(String fimSumbissao) {
        this.fimSumbissao = fimSumbissao;
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
