package br.com.fdbst.tasklist.exception;

/**
 * Classe BusinessException
 *
 * Essa classe representa as exceções de regra de negócio do sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
