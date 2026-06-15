package br.ifpe.proext.service;


import br.ifpe.proext.exception.EditalNaoEncontradoException;
import br.ifpe.proext.exception.PeriodoAvaliacaoInvalidoException;
import br.ifpe.proext.exception.PeriodoSubmissaoInvalidoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.repository.EditalRepository;
import java.util.List;
import java.time.LocalDate;


public class EditalService {

    private EditalService() {}

    public static int gerarProximoNumero(int ano) {

        int maiorNumero = 0;

        for (Edital edital : EditalRepository.listarPorAno(ano)) {

            if (edital.getNumero() > maiorNumero) {
                maiorNumero = edital.getNumero();
            }
        }

        return maiorNumero + 1;
    }

    private static void validarPeriodoSubmissao(Edital edital) {

        if (edital.getInicioSubmissao() == 0) {
            throw new IllegalArgumentException("Início da submissão é obrigatório.");
        }

        if (edital.getFimSubmissao() == 0) {
            throw new IllegalArgumentException("Fim da submissão é obrigatório.");
        }

        if (edital.getInicioSubmissao() > edital.getFimSubmissao()) {
            throw new PeriodoSubmissaoInvalidoException();
        }
    }

    private static void validarPeriodoAvaliacao(Edital edital) {

        if (edital.getInicioAvaliacao() == 0) {
            throw new IllegalArgumentException("Início da avaliação é obrigatório.");
        }

        if (edital.getFimAvaliacao() == 0) {
            throw new IllegalArgumentException("Fim da avaliação é obrigatório.");
        }

        if (edital.getInicioAvaliacao() > edital.getFimAvaliacao()) {
            throw new PeriodoAvaliacaoInvalidoException(
                    "O início da avaliação deve ser anterior ao fim da avaliação.");
        }
    }

    private static void validarConsistenciaTemporalSubmissaoAvaliacao(Edital edital) {

        if (edital.getInicioAvaliacao() < edital.getFimSubmissao()) {
            throw new PeriodoAvaliacaoInvalidoException(
                    "A avaliação não pode iniciar antes do término da submissão.");
        }
    }

    private static void definirNovoEdital(Edital edital) {

        int anoAtual = LocalDate.now().getYear();

        edital.setAno(anoAtual);
        edital.setDataCriacao(System.currentTimeMillis());

        int proximoNumero = gerarProximoNumero(anoAtual);

        edital.setNumero(proximoNumero);

    }

    public static void cadastrarEdital(Edital edital){

        validarPeriodoSubmissao(edital);
        validarPeriodoAvaliacao(edital);
        validarConsistenciaTemporalSubmissaoAvaliacao(edital);
        definirNovoEdital(edital);

        EditalRepository.criarEdital(edital);
    }

    public static void editarEdital(Edital editalAtualizado) {

        validarPeriodoSubmissao(editalAtualizado);

        Edital editalExistente =
                EditalRepository.buscarPorNumeroEAno(
                        editalAtualizado.getNumero(),
                        editalAtualizado.getAno());

        if (editalExistente == null) {
            throw new EditalNaoEncontradoException();
        }

        EditalRepository.atualizarEdital(editalAtualizado);
    }

    public static List<Edital> listarEditais() {

        return EditalRepository.listarTodos();

    }

    }
