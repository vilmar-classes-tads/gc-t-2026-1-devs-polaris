package br.ifpe.proext.exception;

public class SemPermissaoException extends RuntimeException {

    public SemPermissaoException() {
        super("Acesso negado: apenas administradores podem realizar esta operação.");
    }
}
