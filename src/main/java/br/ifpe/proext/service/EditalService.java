package br.ifpe.proext.service;


import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.exception.AcessoNegadoException;
import br.ifpe.proext.exception.EditalNaoEncontradoException;
import br.ifpe.proext.exception.PeriodoAvaliacaoInvalidoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.EditalRepository;
import br.ifpe.proext.exception.PeriodoSubmissaoInvalidoException;
import java.util.List;
import java.time.LocalDate;


public class EditalService {


    private static void validarUnicidadeEdital(Edital editalAtualizado, boolean ehCadastro) {
        if (editalAtualizado.getNumero() == null) {
            return;
        }

        // Listamos todos os editais direto do repositório
        List<Edital> todosEditais = EditalRepository.listarTodos();

        for (Edital editalDoBanco : todosEditais) {

            if (editalDoBanco.getNumero().equals(editalAtualizado.getNumero())) {


                if (ehCadastro) {
                    throw new br.ifpe.proext.exception.EditalJaCadastradoException();
                }


               return;
            }
        }
    }
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

    private static void validarPermissaoAdministrador(Servidor servidor) {
        if (servidor == null || servidor.getPerfis() == null || !servidor.getPerfis().contains(Perfil.ADMINISTRADOR)) {
            throw new AcessoNegadoException();
        }
    }

    public static void cadastrarEdital(Edital edital, Servidor servidor){

//        validarTitulo(edital.getTitulo());
        validarPermissaoAdministrador(servidor);

        definirNovoEdital(edital);

        validarUnicidadeEdital(edital, true);

        validarPeriodoSubmissao(edital, edital);
        validarPeriodoAvaliacao(edital, edital);

        EditalRepository.criarEdital(edital);
    }

        public static void editarEdital(Edital editalAtualizado, Servidor servidor) {

            validarPermissaoAdministrador(servidor);



            Edital editalExistente =
                    EditalRepository.buscarPorNumero(
                            editalAtualizado.getNumero());

            if (editalExistente == null) {
                throw new EditalNaoEncontradoException();
            }

            validarUnicidadeEdital(editalAtualizado, true);

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
    public static List<Edital> listarEditais( Servidor servidor) {

        validarPermissaoAdministrador(servidor);

        return EditalRepository.listarTodos();

    }

    }

