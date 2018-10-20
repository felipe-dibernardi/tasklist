package br.com.fdbst.tasklist.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Classe CORSRequestFilter
 *
 * Essa classe  implementa o filtro que intercepta requisições feitas ao servidor.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Provider
@PreMatching
public class CORSRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if ( requestContext.getRequest().getMethod().equals( "OPTIONS" ) ) {

            requestContext.abortWith( Response.status( Response.Status.OK ).build() );
        }
    }

}