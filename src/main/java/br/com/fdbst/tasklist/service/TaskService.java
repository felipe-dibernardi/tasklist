package br.com.fdbst.tasklist.service;

import br.com.fdbst.tasklist.model.Task;
import br.com.fdbst.tasklist.model.User;
import br.com.fdbst.tasklist.type.StatusType;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.EnumSet;
import java.util.List;

/**
 * Classe TaskService
 *
 * Essa classe implementa as regras de negócio para a entidade Tarefa.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class TaskService extends BasePersistence<Task> {

    /**
     * Lista todas as Tarefas de um Usuário.
     * @param userId Id do Usuário do qual se quer recuperar as Tarefas.
     * @return Lista de Tarefas.
     */
    public List<Task> listByUser(Integer userId) {
        StringBuilder strQuery = new StringBuilder();

        strQuery.append("SELECT t FROM Task t ");
        strQuery.append("WHERE t.user.id = ").append(userId).append(" ");
        strQuery.append("AND t.status NOT IN :status");

        Query query = super.createQuery(strQuery.toString());

        query.setParameter("status", EnumSet.of(StatusType.REMOVED));

        return query.getResultList();
    }

}
