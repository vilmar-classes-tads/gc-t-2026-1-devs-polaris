package br.ifpe.proext.service;

import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ServidorRepository;

public class ServidorService {

    private ServidorService(){}

    public static void cadastrarServidor(Servidor servidor){

        //Validação de CPF
        if (ServidorRepository.buscarPorCpf(servidor.getCpf()) != null){
            throw new RuntimeException("CPF já cadastrado.");
        }

        ServidorRepository.criarServidor(servidor);
    }
}
