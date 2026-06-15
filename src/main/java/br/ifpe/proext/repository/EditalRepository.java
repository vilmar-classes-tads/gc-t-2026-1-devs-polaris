package br.ifpe.proext.repository;

import br.ifpe.proext.model.Edital;



import java.util.ArrayList;
import java.util.List;


public class EditalRepository {

    private static final List<Edital> editais = new ArrayList<>();

    private EditalRepository(){}

    public static void criarEdital(Edital edital){
        editais.add(edital);
    }

    public static List<Edital> buscarPorTitulo(String titulo) {

        List<Edital> resultado = new ArrayList<>();

        for (Edital edital : editais) {
            if (edital.getTitulo().trim().toLowerCase().equals((titulo).trim().toLowerCase())) {
                resultado.add(edital);
            }
        }

        return resultado;
    }

    public static Edital buscarPorNumeroEAno(
            int numero,
            int ano) {

        for (Edital edital : editais) {

            if (edital.getNumero() == numero
                    && edital.getAno() == ano) {

                return edital;
            }
        }

        return null;
    }

    public static void atualizarEdital(Edital edital) {

        for (Edital editalAux : editais) {

            if (editalAux.getNumero() == edital.getNumero()
                    && editalAux.getAno() == edital.getAno()) {

                editalAux.setTitulo(edital.getTitulo());
                editalAux.setInicioSubmissao(edital.getInicioSubmissao());
                editalAux.setFimSubmissao(edital.getFimSubmissao());
                editalAux.setInicioAvaliacao(edital.getInicioAvaliacao());
                editalAux.setFimAvaliacao(edital.getFimAvaliacao());

                return;
            }
        }
    }
    public static boolean removerEdital(int numero, int ano) {
        return editais.removeIf(
                edital -> edital.getNumero() ==(numero) && edital.getAno() == (ano));
    }

    public static List<Edital> listarTodos(){
        return new ArrayList<>(editais); //retornando uma cópia da lista atual de editais
    }

    public static List<Edital> listarPorAno(int ano) {

        List<Edital> resultado = new ArrayList<>();

        for (Edital edital : editais) {

            if (edital.getAno() == ano) {
                resultado.add(edital);
            }
        }

        return resultado;
    }

}
