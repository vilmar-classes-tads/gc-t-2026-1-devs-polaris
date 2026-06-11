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

    public static Edital buscarPorNumero(String numero){
        for (Edital edital: editais) {
            if(edital.getNumero().equals(numero)){
                return edital;
            }
        }
        return null;
    }

    public static List<Edital> buscarPorTitulo(String titulo) {

        List<Edital> resultado = new ArrayList<>();

        for (Edital edital : editais) {
            if (edital.getTitulo().equals(titulo)) {
                resultado.add(edital);
            }
        }

        return resultado;
    }

    public static void atualizarEdital(Edital edital) {

        for (Edital editalAux : editais) {

            if (editalAux.getNumero().equals(edital.getNumero())) {

                editalAux.setTitulo(edital.getTitulo());
                editalAux.setData(edital.getData());
                editalAux.setInicioSumbissao(edital.getInicioSumbissao());
                editalAux.setFimSumbissao(edital.getFimSumbissao());
                editalAux.setInicioAvaliacao(edital.getInicioAvaliacao());
                editalAux.setFimAvaliacao(edital.getFimAvaliacao());

                return;
            }
        }
    }
    public static boolean removerEdital(String numero) {
        return editais.removeIf(
                edital -> edital.getNumero().equals(numero)
        );
    }

    public static List<Edital> listarTodos(){
        return new ArrayList<>(editais); //retornando uma cópia da lista atual de editais
    }


}
