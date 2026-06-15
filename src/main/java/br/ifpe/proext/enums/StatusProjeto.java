package br.ifpe.proext.enums;

public enum StatusProjeto {
    RASCUNHO("Em Elaboração"),
    SUBMETIDO("Submetido"),
    EM_ANALISE("Em Análise"),
    CORRECAO("Em Correção"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado");

    private final String descricao;

    StatusProjeto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
