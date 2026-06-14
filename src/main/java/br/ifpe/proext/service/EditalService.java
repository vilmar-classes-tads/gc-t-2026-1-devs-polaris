package br.ifpe.proext.service;


import br.ifpe.proext.model.Edital;
import br.ifpe.proext.repository.EditalRepository;


public class EditalService {

    private static void definirNovoEdital(Edital edital) {

    }

    public static void cadastrarEdital(Edital edital){

//        validarTitulo(edital.getTitulo());
//        validarNumero(edital.getNumero());

        definirNovoEdital(edital);

        EditalRepository.criarEdital(edital);
    }
}
