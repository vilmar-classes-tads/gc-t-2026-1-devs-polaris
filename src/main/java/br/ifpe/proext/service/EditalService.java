package br.ifpe.proext.service;


import br.ifpe.proext.exception.PeriodoSubmissaoInvalidoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.repository.EditalRepository;

import java.util.List;
import java.time.LocalDate;


public class EditalService {

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

        if (edital.getInicioSubmissao() > edital.getFimSubmissao()) {
            throw new PeriodoSubmissaoInvalidoException();
        }
    }

    private static void definirNovoEdital(Edital edital) {

        int anoAtual = LocalDate.now().getYear();

        edital.setAno(anoAtual);
        edital.setData(System.currentTimeMillis());

        int proximoNumero = gerarProximoNumero(anoAtual);

        edital.definirNumero(proximoNumero);

    }

    public static void cadastrarEdital(Edital edital){

//        validarTitulo(edital.getTitulo());
//        validarNumero(edital.getNumero());

        validarPeriodoSubmissao(edital);
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
            throw new RuntimeException("Edital não encontrado.");
        }

        EditalRepository.atualizarEdital(editalAtualizado);
    }

    public static List<Edital> listarEditais() {

        return EditalRepository.listarTodos();

    }

    }
