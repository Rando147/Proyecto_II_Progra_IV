
package controller;

import cine.cartelera.Cartelera;
import cine.pelicula.Pelicula;
import cine.sala.Sala;
import javax.ws.rs.NotAcceptableException;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
    public void crear(Pelicula p){
    try {
            Service.instance().crearPelicula(p);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    // se lo dije pedazo de playo xD   
    //@QueryParam("sala") String sala, @QueryParam("asientos") String asientos
    @POST
    @Path("sala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(String aux){
    try {
        int x=0; 
            //Service.instance().crearSala(aux);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    @POST
    @Path("cartelera")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(Cartelera aux){
    try {
            System.out.print(aux);
            //Service.instance().crearCartelera(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
}
