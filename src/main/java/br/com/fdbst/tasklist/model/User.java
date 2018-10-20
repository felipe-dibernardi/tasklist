package br.com.fdbst.tasklist.model;

import br.com.fdbst.tasklist.type.ProfileType;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Classe User
 *
 * Essa classe representa o Usuário no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ProfileType profile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ProfileType getProfile() {
        return profile;
    }

    public void setProfile(ProfileType profile) {
        this.profile = profile;
    }
}
