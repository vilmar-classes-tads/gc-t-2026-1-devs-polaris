package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ServidorRepository;

public class ServidorService {

    private ServidorService(){}

    private static void validarCpf(String cpf) {
        if (ServidorRepository.buscarPorCpf(cpf) != null) {
            throw new RuntimeException("CPF já cadastrado.");
        }
    }

    private static void validarEmail(String email) {
        if (ServidorRepository.buscarPorEmail(email) != null) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
    }

    private static void validarSenha(String senha) {

        if (senha == null || senha.length() < 6) {
            throw new RuntimeException(
                    "A senha deve possuir no mínimo 6 caracteres."
            );
        }

    }

    private static void atribuirPerfisPadrao(Servidor servidor) {

        servidor.getPerfis().add(Perfil.COORDENADOR);
        servidor.getPerfis().add(Perfil.AVALIADOR);

    }

    private static void definirNovoServidor(Servidor servidor) {

        atribuirPerfisPadrao(servidor);
        //criptografarSenha(servidor);
        //definirStatusInicial(servidor);

    }



    public static void cadastrarServidor(Servidor servidor){

        validarCpf(servidor.getCpf());
        validarEmail(servidor.getEmail());
        validarSenha(servidor.getSenhaHash());

        definirNovoServidor(servidor);

        ServidorRepository.criarServidor(servidor);
    }
}
