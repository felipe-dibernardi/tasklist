package br.com.fdbst.tasklist.service;

import br.com.fdbst.tasklist.exception.BusinessException;
import br.com.fdbst.tasklist.model.User;
import br.com.fdbst.tasklist.util.MD5Hash;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Classe UserService
 *
 * Essa classe implementa as regras de negócio para a entidade Usuário.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class UserService extends BasePersistence<User> {

    @Override
    public User insert(User user) throws BusinessException {
        user.setPassword(MD5Hash.encript(user.getPassword()));
        return super.insert(user);
    }

    /**
     * Busca um Usuário pelo username,
     * @param username Username do Usuário.
     * @return Usuário ou nulo se não for encontrado.
     */
    public User findByUsername(String username) {
        StringBuilder strQuery = new StringBuilder();

        strQuery.append("SELECT u FROM User u ");
        strQuery.append("WHERE u.username = '").append(username).append("'");

        Query query = super.createQuery(strQuery.toString());

        return query.getResultList().isEmpty() ? null : (User) query.getSingleResult();
    }

    /**
     * Busca por um Usuário com o seguinte par username/senha
     * @param username Username do usuário.
     * @param password Senha do usuário.
     * @return Usuário ou nulo se não for encontrado.
     */
    public User authenticate(String username, String password) {
        StringBuilder strQuery = new StringBuilder();

        strQuery.append("SELECT u FROM User u ");
        strQuery.append("WHERE u.username = '").append(username).append("' ");
        strQuery.append("AND u.password = '").append(MD5Hash.encript(password)).append("'");

        Query query = super.createQuery(strQuery.toString());

        return query.getResultList().isEmpty() ? null : (User) query.getSingleResult();
    }
}
