package br.ifpe.proext.model;

import br.ifpe.proext.enums.TipoParticipacao;

public class Membro {
    private String nome;
    private String cpf;
    private String funcao;
    private int cargaHoraria;
    private TipoParticipacao tipoParticipacao;

    public Membro() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public TipoParticipacao getTipoParticipacao() {
        return tipoParticipacao;
    }

    public void setTipoParticipacao(TipoParticipacao tipoParticipacao) {
        this.tipoParticipacao = tipoParticipacao;
    }

    public boolean isPlanoDeTrabalho() {
        return tipoParticipacao != null;
    }
}
