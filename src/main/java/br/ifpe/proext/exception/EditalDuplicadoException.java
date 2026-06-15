package br.ifpe.proext.exception;

public class EditalDuplicadoException extends RuntimeException {

    public EditalDuplicadoException() {
        super("Já existe um edital com este número e ano.");
    }
}