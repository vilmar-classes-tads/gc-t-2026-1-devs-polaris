package br.ifpe.proext.service;

import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ServidorRepository;

public class ServidorService {

    private ServidorService(){}

    public static void cadastrarServidor(Servidor servidor){
        ServidorRepository.criarServidor(servidor);
    }
}
