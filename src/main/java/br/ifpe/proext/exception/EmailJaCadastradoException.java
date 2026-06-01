package br.ifpe.proext.exception;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException( ) {

        super("E-mail já cadastrado");
    }
}
