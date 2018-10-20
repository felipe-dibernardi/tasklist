package br.com.fdbst.tasklist.service;

import br.com.fdbst.tasklist.exception.BusinessException;
import br.com.fdbst.tasklist.model.Task;
import br.com.fdbst.tasklist.model.User;
import br.com.fdbst.tasklist.type.StatusType;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Date;
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

    @Override
    public Task insert(Task task) throws BusinessException {
        task.setStatus(StatusType.OPEN);
        task.setCreation(new Date());
        return super.insert(task);
    }

    @Override
    public Task update(Task task) throws BusinessException {
        task.setLastUpdate(new Date());
        return super.update(task);
    }

    @Override
    public void remove(Class<Task> classType, Integer id) throws BusinessException {
        try {
            Task task = this.find(classType, id);
            task.setStatus(StatusType.REMOVED);
            task.setRemoval(new Date());
            this.update(task);
        } catch (BusinessException e) {
            throw new BusinessException("action.remove.error");
        }

    }

    /**
     * Conclui uma Tarefa por meio do seu id.
     * @param id Id da Terafa.
     * @return Tarefa concluída.
     * @throws BusinessException Exceção a ser lançada caso haja erro ao atualizar.
     */
    public Task conclude(Integer id) throws BusinessException {
        Task task = this.find(Task.class, id);
        task.setStatus(StatusType.CONCLUDED);
        task.setConclusion(new Date());
        return this.update(task);
    }

    /**
     * Reinicia uma Tarefa concluída por meio do seu id.
     * @param id Id da Tarefa.
     * @return Tarefa concluída.
     * @throws BusinessException Exceção a ser lançada caso haja erro ao atualizar.
     */
    public Task restart(Integer id) throws BusinessException {
        Task task = this.find(Task.class, id);
        task.setStatus(StatusType.OPEN);
        task.setConclusion(null);
        return this.update(task);
    }
}
