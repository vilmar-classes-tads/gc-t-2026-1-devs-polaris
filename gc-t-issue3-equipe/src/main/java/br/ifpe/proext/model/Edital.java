package br.ifpe.proext.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Edital {

    private static final String FORMATO_DATA = "dd/MM/yyyy";

    private String titulo;
    private int numero;
    private int ano;
    private long dataCriacao;
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
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(long dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public long getInicioSubmissao() {
        return inicioSubmissao;
    }

    public void setInicioSubmissao(long inicioSubmissao) {
        this.inicioSubmissao = inicioSubmissao;
    }

    public String getInicioSubmissaoString() {
        return new SimpleDateFormat(FORMATO_DATA).format(new Date(this.inicioSubmissao));
    }

    public long getFimSubmissao() {
        return fimSubmissao;
    }

    public void setFimSubmissao(long fimSubmissao) {
        this.fimSubmissao = fimSubmissao;
    }

    public String getFimSubmissaoString() {
        return new SimpleDateFormat(FORMATO_DATA).format(new Date(this.fimSubmissao));
    }

    public long getInicioAvaliacao() {
        return inicioAvaliacao;
    }

    public void setInicioAvaliacao(long inicioAvaliacao) {
        this.inicioAvaliacao = inicioAvaliacao;
    }

    public String getInicioAvaliacaoString() {
        return new SimpleDateFormat(FORMATO_DATA).format(new Date(this.inicioAvaliacao));
    }

    public long getFimAvaliacao() {
        return fimAvaliacao;
    }

    public void setFimAvaliacao(long fimAvaliacao) {
        this.fimAvaliacao = fimAvaliacao;
    }

    public String getFimAvaliacaoString() {
        return new SimpleDateFormat(FORMATO_DATA).format(new Date(this.fimAvaliacao));
    }
}
