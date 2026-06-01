package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.exception.CpfJaCadastradoException;
import br.ifpe.proext.exception.EmailJaCadastradoException;
import br.ifpe.proext.exception.SenhaInvalidaException;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ServidorRepository;

public class ServidorService {

    private ServidorService(){}

    private static void validarCpf(String cpf) {
        if (ServidorRepository.buscarPorCpf(cpf) != null) {
            throw new CpfJaCadastradoException();
        }
    }

    private static void validarEmail(String email) {
        if (ServidorRepository.buscarPorEmail(email) != null) {
            throw new EmailJaCadastradoException();
        }
    }

    private static void validarSenha(String senha) {

        if (senha == null || senha.length() < 6) {
            throw new SenhaInvalidaException();
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
