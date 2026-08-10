package br.ifpe.proext.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {

    public ProjetoNaoEncontradoException() {
        super("Projeto não encontrado.");
    }
}

