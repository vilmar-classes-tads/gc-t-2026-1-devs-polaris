package br.ifpe.proext.exception;

public class MembroJaCadastradoException extends RuntimeException {

    public MembroJaCadastradoException() {
        super("Já existe um membro cadastrado com este CPF neste projeto.");
    }
}
