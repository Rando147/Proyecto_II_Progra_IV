
package controller;

import cine.cliente.Cliente;
import cine.pelicula.Pelicula;
import cine.usuario.Usuario;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.NotAcceptableException;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Service;

@Path("admin")
@PermitAll
public class Administrador {
    
    
    @POST
    @Path("pelicula")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void crear(Pelicula p){
    try {
            Service.instance().crearPelicula(p);
            
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    // se lo dije pedazo de playo xD   
    
}
