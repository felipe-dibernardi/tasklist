package br.com.fdbst.tasklist.service;

import br.com.fdbst.tasklist.exception.BusinessException;
import br.com.fdbst.tasklist.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Classe BasePersistence
 *
 * Essa classe abstrata implementa os métodos padrão para um CRUD.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BasePersistence<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager em;

    /**
     * Recupera todos os registros de T.
     *
     * @param classType Classe de T.
     * @return Lista de T.
     */
    public List<T> listAll(final Class<T> classType) {
        Query query = this.createQuery("SELECT object(o) FROM " + classType.getSimpleName() + " AS o");
        return query.getResultList();
    }

    /**
     * Busca um registro específico através do id.
     *
     * @param classType Classe de T.
     * @param id Id do registro a ser recuperado.
     * @return Registro recuperado
     */
    public T find(final Class<T> classType, final Integer id) {
        return em.find(classType, id);
    }

    /**
     * Insere um registro no sistema.
     *
     * @param t Registro a ser inserido.
     * @return Registro inserido com seu id.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public T insert(final T t) throws BusinessException {
        try {
            em.persist(t);
            em.flush();
            return t;
        } catch (Exception e) {
            throw new BusinessException("action.insert.error");
        }

    }

    /**
     * Atualiza um registro no sistema.
     *
     * @param t Registro a ser atualizado.
     * @return Registro atualizado.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public T update(final T t) throws BusinessException {
        try {
            em.merge(t);
            em.flush();
            return t;
        } catch (Exception e) {
            throw new BusinessException("action.update.error");
        }

    }

    /**
     * Remove um registro do sistema através do id.
     *
     * @param classType Classe de T.
     * @param id Id do registro.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public void remove(Class<T> classType, Integer id) throws BusinessException {
        T bean = this.find(classType, id);
        if (bean != null) {
            try {
                em.remove(bean);
                em.flush();
            } catch (Exception e) {
                throw new BusinessException("action.remove.error");
            }
        }
    }

    /**
     * Cria uma Query SQL a partir de uma String.
     *
     * @param query String representando a Query SQL.
     * @return Objeto Query.
     */
    public Query createQuery(final String query) {
        return em.createQuery(query);
    }

    /**
     * Cria uma Query SQL a partir de uma NamedQuery.
     *
     * @param query Named Query.
     * @return Objeto Query.
     */
    public Query createNamedQuery(final String query) {
        return em.createNamedQuery(query);
    }

}
