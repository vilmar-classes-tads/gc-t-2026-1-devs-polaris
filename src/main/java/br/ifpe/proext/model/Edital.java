package br.ifpe.proext.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Edital {

    private static final String FORMATO_DATA = "dd/MM/yyyy";
    private String titulo;
    private int numero;
    private int ano;
    private long data;
    private long inicioSubmissao;
    private long fimSubmissao;
    private long inicioAvaliacao;
    private long fimAvaliacao;

    public Edital() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero() {
        return numero;
    }
    public void definirNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
    public long getInicioSubmissao() {
        return inicioSubmissao;
    }

    public void setInicioSubmissao(long inicioSubmissao) {
        this.inicioSubmissao = inicioSubmissao;
    }

    public void setInicioSubmissao(String data)
            throws ParseException {

        this.inicioSubmissao =
                new SimpleDateFormat(FORMATO_DATA)
                        .parse(data)
                        .getTime();
    }

    public String getInicioSubmissaoString() {

        return new SimpleDateFormat(FORMATO_DATA)
                .format(new Date(this.inicioSubmissao));
    }
    public long getFimSubmissao() {
        return fimSubmissao;
    }

    public void setFimSubmissao(long fimSubmissao) {
        this.fimSubmissao = fimSubmissao;
    }

    public void setFimSubmissao(String data)
            throws ParseException {

        this.fimSubmissao =
                new SimpleDateFormat(FORMATO_DATA)
                        .parse(data)
                        .getTime();
    }

    public String getFimSubmissaoString() {

        return new SimpleDateFormat(FORMATO_DATA)
                .format(new Date(this.fimSubmissao));
    }
    public long getInicioAvaliacao() {
        return inicioAvaliacao;
    }

    public void setInicioAvaliacao(long inicioAvaliacao) {
        this.inicioAvaliacao = inicioAvaliacao;
    }

    public void setInicioAvaliacao(String data)
            throws ParseException {

        this.inicioAvaliacao =
                new SimpleDateFormat(FORMATO_DATA)
                        .parse(data)
                        .getTime();
    }

    public String getInicioAvaliacaoString() {

        return new SimpleDateFormat(FORMATO_DATA)
                .format(new Date(this.inicioAvaliacao));
    }

    public long getFimAvaliacao() {
        return fimAvaliacao;
    }

    public void setFimAvaliacao(long fimAvaliacao) {
        this.fimAvaliacao = fimAvaliacao;
    }

    public void setFimAvaliacao(String data)
            throws ParseException {

        this.fimAvaliacao =
                new SimpleDateFormat(FORMATO_DATA)
                        .parse(data)
                        .getTime();
    }

    public String getFimAvaliacaoString() {

        return new SimpleDateFormat(FORMATO_DATA)
                .format(new Date(this.fimAvaliacao));
    }
    public String getNumeroFormatado() {
        return String.format("%02d/%d", numero, ano);
    }
}
