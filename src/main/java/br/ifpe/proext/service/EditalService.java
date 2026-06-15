package br.ifpe.proext.service;


import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.exception.PeriodoSubmissaoInvalidoException;
import br.ifpe.proext.exception.PeriodoAvaliacaoInvalidoException;
import br.ifpe.proext.exception.EditalDuplicadoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.EditalRepository;
import java.util.List;
import java.time.LocalDate;


public class EditalService {

    private static void validarUnicidadeEdital(Edital edital) {

        Edital editalExistente =
                EditalRepository.buscarPorNumeroEAno(
                        edital.getNumero(),
                        edital.getAno());

        if (editalExistente != null) {
            throw new EditalDuplicadoException();
        }
    }
    public static int gerarProximoNumero(int ano) {

        int maiorNumero = 0;

        for (Edital edital : EditalRepository.listarPorAno(ano)) {

            if (edital.getNumero() > maiorNumero) {
                maiorNumero = edital.getNumero();
            }
        }

        return maiorNumero + 1;
    }

    private static void validarAdministrador(Servidor servidor) {

        if (!servidor.getPerfis().contains(Perfil.ADMINISTRADOR)) {
            throw new RuntimeException(
                    "Apenas administradores podem gerenciar editais.");
        }
    }

    private static void validarPeriodoSubmissao(Edital edital) {

        if (edital.getInicioSubmissao() > edital.getFimSubmissao()) {
            throw new PeriodoSubmissaoInvalidoException();
        }
    }

    private static void validarPeriodoAvaliacao(Edital edital) {

        if (edital.getInicioAvaliacao() > edital.getFimAvaliacao()) {
            throw new PeriodoAvaliacaoInvalidoException();
        }
    }

    private static void definirNovoEdital(Edital edital) {

        int anoAtual = LocalDate.now().getYear();

        edital.setAno(anoAtual);
        edital.setData(System.currentTimeMillis());

        int proximoNumero = gerarProximoNumero(anoAtual);

        edital.definirNumero(proximoNumero);

    }

    public static void cadastrarEdital(Edital edital, Servidor servidor){

//        validarTitulo(edital.getTitulo());
//        validarNumero(edital.getNumero());


        validarAdministrador(servidor);

        validarPeriodoSubmissao(edital);
        validarPeriodoAvaliacao(edital);

        definirNovoEdital(edital);
        validarUnicidadeEdital(edital);

        EditalRepository.criarEdital(edital);
    }

    public static void editarEdital(Edital editalAtualizado, Servidor servidor) {

        validarAdministrador(servidor);

        validarPeriodoSubmissao(editalAtualizado);
        validarPeriodoAvaliacao(editalAtualizado);

        Edital editalExistente =
                EditalRepository.buscarPorNumeroEAno(
                        editalAtualizado.getNumero(),
                        editalAtualizado.getAno());

        if (editalExistente == null) {
            throw new RuntimeException("Edital não encontrado.");
        }

        EditalRepository.atualizarEdital(editalAtualizado);
    }

    public static List<Edital> listarEditais(Servidor servidor) {

        validarAdministrador(servidor);

        return EditalRepository.listarTodos();

    }

    }
