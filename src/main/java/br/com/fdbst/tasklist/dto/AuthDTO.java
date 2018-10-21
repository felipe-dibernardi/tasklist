package br.com.fdbst.tasklist.dto;

/**
 * Classe AuthDTO
 *
 * Essa classe representa os dados necessários para realizar o login no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class AuthDTO {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
