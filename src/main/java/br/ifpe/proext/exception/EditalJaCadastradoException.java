package br.ifpe.proext.exception;

public class EditalJaCadastradoException extends RuntimeException {
    public EditalJaCadastradoException() {
        super("Já existe um edital cadastrado com este número.");
    }
}
