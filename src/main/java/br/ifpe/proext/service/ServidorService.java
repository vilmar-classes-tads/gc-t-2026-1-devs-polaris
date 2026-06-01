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

        //validação de Email
        if (ServidorRepository.buscarPorEmail(servidor.getEmail()) != null){
            throw new RuntimeException("email já cadastrado.");
        }

        ServidorRepository.criarServidor(servidor);
    }
}
