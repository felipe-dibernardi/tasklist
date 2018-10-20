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
     * @return Usuário.
     */
    public User findByUsername(String username) {
        StringBuilder strQuery = new StringBuilder();

        strQuery.append("SELECT u FROM User u ");
        strQuery.append("WHERE u.username = ").append(username);

        Query query = super.createQuery(strQuery.toString());

        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (User) query.getSingleResult();
        }
    }
}
