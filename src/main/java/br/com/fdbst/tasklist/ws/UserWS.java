package br.com.fdbst.tasklist.ws;

import br.com.fdbst.tasklist.dto.AuthDTO;
import br.com.fdbst.tasklist.exception.BusinessException;
import br.com.fdbst.tasklist.model.User;
import br.com.fdbst.tasklist.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Classe UserWS
 *
 * Essa classe implementa os endpoints para a entidade Usuário.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
@Path("users")
public class UserWS {

    @EJB
    private UserService userService;

    /**
     * Busca um Usuário através do username.
     * @param username Username do Usuário.
     * @return Usuário.
     */
    @GET
    @Path("{username}")
    @Produces("application/json")
    public Response findByUsername(@PathParam("username") String username) {
        return Response.ok().entity(this.userService.findByUsername(username)).build();
    }

    /**
     * Insere um Usuário no sistema.
     * @param user Usuário a ser inserido.
     * @return Usuário inserido.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(User user) {
        try {
            return Response.ok().entity(this.userService.insert(user)).build();
        } catch (BusinessException e) {
            return Response.serverError().entity(e).build();
        }
    }

    /**
     * Realiza a autenticação de um Usuário no sistema.
     * @param auth DTO de Autenticação.
     * @return Usuário autenticado.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response authenticate(AuthDTO auth) {
        User user = this.userService.authenticate(auth.getUsername(), auth.getPassword());
        return (user == null) ? Response.status(Response.Status.UNAUTHORIZED).build() : Response.ok().entity(user).build();
    }

}
