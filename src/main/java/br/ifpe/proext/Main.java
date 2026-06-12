package br.ifpe.proext;

import br.ifpe.proext.controller.EditalController;
import br.ifpe.proext.model.Edital;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("🚀 TESTE: TENTATIVA DE AJUSTAR FIM < INÍCIO NA AVALIAÇÃO");
        System.out.println("=============================================\n");

        // 1. Criar e cadastrar o edital base (Avaliação começa nula)
        Edital edital = new Edital();
        edital.setTitulo("Edital de Extensão IFPE 2026");
        edital.setData("2026-06-15");
        edital.setFimSubmissao("2026-07-15");
        EditalController.cadastrarEdital(edital);

        // 2. Buscar o edital do banco simulado para a primeira edição
        List<Edital> lista = EditalController.listarEditais();
        Edital editalDoBanco = lista.get(0);

        // 3. Definir um período inicial correto para a avaliação
        System.out.println("📅 Definindo período inicial correto: Início em 01/08 e Fim em 15/08...");
        editalDoBanco.setInicioAvaliacao("2026-08-01");
        editalDoBanco.setFimAvaliacao("2026-08-15");
        EditalController.editarEdital(editalDoBanco);
        System.out.println("✅ Período inicial salvo com sucesso.");

        // -----------------------------------------------------------------
        // 🚨 O CENÁRIO DO TEU TESTE: Mudar o Fim para antes do Início
        // -----------------------------------------------------------------
        System.out.println("\n🔥 Executando o Teu Teste: Alterando data de Fim para ANTERIOR ao Início...");

        // Mantemos o início fixo (2026-08-01) e tentamos mudar o fim para Julho (2026-07-25)
        editalDoBanco.setFimAvaliacao("2026-07-25");

        try {
            EditalController.editarEdital(editalDoBanco);
            System.out.println("❌ ERRO: O sistema aceitou salvar o Fim da avaliação anterior ao Início!");
        } catch (Exception e) {
            System.out.println("✅ SUCESSO NO BLOQUEIO: O sistema impediu a gravação de datas inválidas!");
            System.out.println("💡 Exceção capturada: " + e.getClass().getSimpleName());
        }

        System.out.println("\n=============================================");
        System.out.println("🏁 FIM DO TESTE ESPECÍFICO");
        System.out.println("=============================================");
    }
}