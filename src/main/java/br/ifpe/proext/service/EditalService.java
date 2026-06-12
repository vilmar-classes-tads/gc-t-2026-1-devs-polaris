package br.ifpe.proext.service;


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

    public static void cadastrarEdital(Edital edital){

//        validarTitulo(edital.getTitulo());
//        validarNumero(edital.getNumero());

        definirNovoEdital(edital);

        EditalRepository.criarEdital(edital);
    }

        public static void editarEdital(Edital editalAtualizado) {

            Edital editalExistente =
                    EditalRepository.buscarPorNumero(
                            editalAtualizado.getNumero());

            if (editalExistente == null) {
                throw new RuntimeException("Edital não encontrado.");
            }

            validarPeriodoSubmissao(
                    editalExistente,
                    editalAtualizado);

            EditalRepository.atualizarEdital(editalAtualizado);
        }
    public static List<Edital> listarEditais() {

        return EditalRepository.listarTodos();

    }

    }

