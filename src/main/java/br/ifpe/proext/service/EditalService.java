package br.ifpe.proext.service;


import br.ifpe.proext.exception.PeriodoAvaliacaoInvalidoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.repository.EditalRepository;
import br.ifpe.proext.exception.PeriodoSubmissaoInvalidoException;
import java.util.List;
import java.time.LocalDate;


public class EditalService {


    private static void definirNovoEdital(Edital edital) {

        edital.setNumero(
                EditalRepository.gerarNumeroEdital());

        edital.setInicioSubmissao(LocalDate.now().toString());
        edital.setData(LocalDate.now().toString());
    }

    private static void validarPeriodoSubmissao(Edital editalExistente, Edital editalAtualizado) {

        if (editalAtualizado.getFimSubmissao() == null) {
            return;
        }

        LocalDate inicio =
                LocalDate.parse(
                        editalExistente.getInicioSubmissao());

        LocalDate fim =
                LocalDate.parse(
                        editalAtualizado.getFimSubmissao());

        if (inicio.isAfter(fim)) {
            throw new PeriodoSubmissaoInvalidoException();
        }
    }
    private static void validarPeriodoAvaliacao(Edital editalExistente, Edital editalAtualizado) {


        if (editalAtualizado.getFimAvaliacao() == null || editalExistente.getInicioAvaliacao() == null) {
            return;
        }

        LocalDate inicio =
                LocalDate.parse(
                        editalExistente.getInicioAvaliacao());

        LocalDate fim =
                LocalDate.parse(
                        editalAtualizado.getFimAvaliacao());

        if (inicio.isAfter(fim)) {
            throw new PeriodoAvaliacaoInvalidoException();
        }
    }

    public static void cadastrarEdital(Edital edital){

//        validarTitulo(edital.getTitulo());
//        validarNumero(edital.getNumero());

        definirNovoEdital(edital);

        validarPeriodoSubmissao(edital, edital);
        validarPeriodoAvaliacao(edital, edital);

        EditalRepository.criarEdital(edital);
    }

        public static void editarEdital(Edital editalAtualizado) {

            Edital editalExistente =
                    EditalRepository.buscarPorNumero(
                            editalAtualizado.getNumero());

            if (editalExistente == null) {
                throw new RuntimeException("Edital não encontrado.");
            }

            if (editalExistente.getInicioAvaliacao() != null) {

                boolean tentouMudarInicio = !editalExistente.getInicioAvaliacao().equals(editalAtualizado.getInicioAvaliacao());
                if (tentouMudarInicio) {
                    throw new RuntimeException("O início do período de avaliação já foi definido e não pode ser alterado.");
                }

            }

            validarPeriodoSubmissao(
                    editalExistente,
                    editalAtualizado);

            validarPeriodoAvaliacao(
                    editalExistente,
                    editalAtualizado);

            EditalRepository.atualizarEdital(editalAtualizado);
        }
    public static List<Edital> listarEditais() {

        return EditalRepository.listarTodos();

    }

    }

