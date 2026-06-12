package br.ifpe.proext;

import br.ifpe.proext.controller.EditalController;
import br.ifpe.proext.model.Edital;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("🚀 INICIANDO TESTES DO SISTEMA DE EDITAIS");
        System.out.println("=============================================\n");

        // ---------------------------------------------------------
        // TESTE 1: Cadastrar um Edital Válido
        // ---------------------------------------------------------
        System.out.println("--- TESTE 1: Cadastro de Edital Válido ---");
        Edital novoEdital = new Edital();
        novoEdital.setTitulo("Edital de Pesquisa PROEXT 2026");
        novoEdital.setData("2026-06-12"); // Data de início do edital
        novoEdital.setFimSubmissao("2026-07-12"); // Fim da submissão (Posterior ao início)
        novoEdital.setInicioAvaliacao("2026-07-15");
        novoEdital.setFimAvaliacao("2026-08-15");

        EditalController.cadastrarEdital(novoEdital);
        System.out.println("✅ Edital cadastrado com sucesso!");

        // ---------------------------------------------------------
        // TESTE 2: Listar e verificar a regra da mesma data inicial
        // ---------------------------------------------------------
        System.out.println("\n--- TESTE 2: Listagem e Validação de Datas ---");
        List<Edital> lista = EditalController.listarEditais();

        if (!lista.isEmpty()) {
            Edital cadastrado = lista.get(0);
            System.out.println("📋 Edital Gerado -> Número: " + cadastrado.getNumero() + " | Título: " + cadastrado.getTitulo());
            System.out.println("📅 Data de Início do Edital: " + cadastrado.getData());
            System.out.println("📥 Início da Submissão (Deve ser igual): " + cadastrado.getInicioSubmissao());
            System.out.println("📤 Fim da Submissão: " + cadastrado.getFimSubmissao());
        }

        // ---------------------------------------------------------
        // TESTE 3: Editar o Edital com sucesso
        // ---------------------------------------------------------
        System.out.println("\n--- TESTE 3: Edição de Edital Valida ---");
        if (!lista.isEmpty()) {
            Edital editalParaEditar = lista.get(0);
            editalParaEditar.setTitulo("Edital de Pesquisa PROEXT 2026 - ATUALIZADO");
            editalParaEditar.setFimSubmissao("2026-07-20"); // Alterando a data de fim

            EditalController.editarEdital(editalParaEditar);

            // Buscar novamente para conferir se alterou
            Edital editalAlterado = EditalController.listarEditais().get(0);
            System.out.println("✅ Edital Editado -> Novo Título: " + editalAlterado.getTitulo());
            System.out.println("✅ Novo Fim de Submissão: " + editalAlterado.getFimSubmissao());
        }

        // ---------------------------------------------------------
        // TESTE 4: Tentar Editar com uma Data Inválida (Lançar Exceção)
        // ---------------------------------------------------------
        System.out.println("\n--- TESTE 4: Tentativa de Edição com Data Inválida ---");
        if (!lista.isEmpty()) {
            Edital editalInvalido = lista.get(0);

            // Forçando o erro: Fim da submissão ANTES do início (Início é 2026-06-12)
            editalInvalido.setFimSubmissao("2026-05-01");

            System.out.println("⚠️ Tentando salvar data de fim (2026-05-01) anterior ao início...");

            try {
                EditalController.editarEdital(editalInvalido);
                System.out.println("❌ ERRO: O sistema aceitou uma data inválida!");
            } catch (Exception e) {
                System.out.println("✅ SUCESSO NO BLOQUEIO: O sistema impediu a alteração!");
                System.out.println("💡 Mensagem do Erro: " + e.getClass().getSimpleName());
            }
        }

        System.out.println("\n=============================================");
        System.out.println("🏁 FIM DOS TESTES");
        System.out.println("=============================================");
    }
}