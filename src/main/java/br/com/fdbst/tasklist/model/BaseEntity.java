package br.com.fdbst.tasklist.model;

/**
 * Classe BaseEntity
 *
 * Essa classe abstrata representa a menor entidade necessária para o sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public abstract class BaseEntity {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
