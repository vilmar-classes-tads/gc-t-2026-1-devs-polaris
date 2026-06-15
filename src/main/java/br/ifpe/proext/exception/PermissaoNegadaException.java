package br.ifpe.proext.exception;

public class PermissaoNegadaException extends RuntimeException {

    public PermissaoNegadaException() {
        super("Apenas administradores podem gerenciar editais.");
    }
}