package br.ifpe.proext.enums;

public enum ODS {
    ODS_1("Fome Zero e Agricultura Sustentável"),
    ODS_2("Saúde e Bem-estar"),
    ODS_3("Educação de Qualidade"),
    ODS_4("Igualdade de Gênero"),
    ODS_5("Água Potável e Saneamento"),
    ODS_6("Energia Limpa e Acessível"),
    ODS_7("Trabalho Decente e Crescimento Econômico"),
    ODS_8("Indústria, Inovação e Infraestrutura"),
    ODS_9("Redução das Desigualdades"),
    ODS_10("Cidades e Comunidades Sustentáveis"),
    ODS_11("Consumo e Produção Responsáveis"),
    ODS_12("Ação Contra a Mudança Global do Clima"),
    ODS_13("Vida Submarina"),
    ODS_14("Vida Terrestre"),
    ODS_15("Paz, Justiça e Instituições Eficazes"),
    ODS_16("Parcerias e Meios de Implementação");

    private final String descricao;

    ODS(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
