package br.com.fdbst.tasklist.exception;

/**
 * Classe BusinessException
 *
 * Essa classe representa as exceções de regra de negócio do sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BusinessException extends Exception {

    private String message;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
