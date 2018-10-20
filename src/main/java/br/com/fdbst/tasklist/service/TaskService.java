package br.com.fdbst.tasklist.service;

import br.com.fdbst.tasklist.model.Task;

import javax.ejb.Stateless;

/**
 * Classe TaskService
 *
 * Essa classe implementa as regras de negócio para a entidade Tarefa.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class TaskService extends BasePersistence<Task> {
}
