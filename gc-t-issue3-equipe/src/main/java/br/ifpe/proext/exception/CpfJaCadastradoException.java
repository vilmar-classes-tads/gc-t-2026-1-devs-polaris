package br.ifpe.proext.exception;

public class CpfJaCadastradoException extends RuntimeException {
    public CpfJaCadastradoException() {

        super("CPF já cadastrado");
    }
}
