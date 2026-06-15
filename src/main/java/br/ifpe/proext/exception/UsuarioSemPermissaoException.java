package br.ifpe.proext.exception;

public class UsuarioSemPermissaoException extends RuntimeException {

    public UsuarioSemPermissaoException() {
        super("Acesso negado: apenas coordenadores podem realizar esta operação.");
    }
}

