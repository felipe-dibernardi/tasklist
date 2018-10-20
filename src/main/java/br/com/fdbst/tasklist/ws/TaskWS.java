package br.com.fdbst.tasklist.ws;

import br.com.fdbst.tasklist.exception.BusinessException;
import br.com.fdbst.tasklist.model.Task;
import br.com.fdbst.tasklist.service.TaskService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Classe TaskWS
 *
 * Essa classe implementa os endpoints para a entidade Tarefa.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
@Path("tasks")
public class TaskWS {

    @EJB
    private TaskService taskService;

    /**
     * Lista todas as Tarefas relacionadas a um usuário.
     * @param userId Id do usuário.
     * @return Lista de Tarefas.
     */
    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public Response listByUser(@PathParam("id") Integer userId) {
        return Response.ok().entity(new GenericEntity<List<Task>>(this.taskService.listByUser(userId)){}).build();
    }

    /**
     * Busca uma Tarefa por meio de seu id.
     * @param id Id da Tarefa.
     * @return Tarefa.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response find(@PathParam("id") Integer id) {
        return Response.ok().entity(this.taskService.find(Task.class, id)).build();
    }

    /**
     * Insere uma Tarefa no sistema.
     * @param task Tarefa a ser inserida no sistema.
     * @return Tarefa inserida.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(Task task) {
        try {
            return Response.ok().entity(this.taskService.insert(task)).build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Atualiza uma Tarefa no sistema.
     * @param task Tarefa a ser atualiza no sistema.
     * @return Tarefa atualizada.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Task task) {
        try {
            return Response.ok().entity(this.taskService.update(task)).build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Conclui uma Tarefa por meio do id.
     * @param id Id da Tarefa.
     * @return Tarefa concluída.
     */
    @PUT
    @Path("conclude/{id}")
    @Produces("application/json")
    public Response conclude(@PathParam("id") Integer id) {
        try {
            return Response.ok().entity(this.taskService.conclude(id)).build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Reinicia uma Tarefa por meio do id.
     * @param id Id da Tarefa.
     * @return Tarefa reiniciada.
     */
    @PUT
    @Path("restart/{id}")
    @Produces("application/json")
    public Response restart(@PathParam("id") Integer id) {
        try {
            return Response.ok().entity(this.taskService.restart(id)).build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Remove uma Tarefa do sistema por meio do id.
     * @param id Id da Tarefa a ser removida.
     * @return Resposta da operação.
     */
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            this.taskService.remove(Task.class, id);
            return Response.ok().build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

}
