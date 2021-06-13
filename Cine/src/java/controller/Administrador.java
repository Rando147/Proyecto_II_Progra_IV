
package controller;

import cine.cartelera.Cartelera;
import cine.cartelera.JSON_TO_CARTELERA_PARSER;
import cine.cliente.Cliente;
import cine.cliente.JSON_TO_CLIENTE_PARSER;
import cine.pelicula.JSON_TO_PELICULA_PARSER;
import cine.pelicula.Pelicula;
import cine.sala.JSON_TO_SALA_PARSER;
import cine.sala.Sala;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.NotAcceptableException;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.Service;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("admin")
@PermitAll
public class Administrador {
      String location="C:/AAA/images/";
      
      
    @POST
    @Path("pelicula")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearPeli(String json){
        JSON_TO_PELICULA_PARSER parser = new JSON_TO_PELICULA_PARSER();
        Pelicula p = parser.parseCJSON(json);
    try {
            Service.instance().crearPelicula(p);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
    @Path("{id}/image")
    public void addImage(@PathParam("id") String id_pelicula, @FormDataParam("image") InputStream imageStream) {  
        try{
            
            
            Service.instance().insertarIMagen(id_pelicula, imageStream);

            } catch (Exception ex) {
                throw new NotAcceptableException(); 
            }
    }
    
    
    @GET
    @Path("{id_pelicula}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("id_pelicula") String id_pelicula) throws IOException {
        
        File file = new File(location+ id_pelicula);
        ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }    
  
    
    @POST
    @Path("sala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearSala(String json){
        JSON_TO_SALA_PARSER parser = new JSON_TO_SALA_PARSER();
        Sala sala = parser.parseCJSON(json);
    try {
            Service.instance().crearSala(sala);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    @POST
    @Path("cartelera")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(String json){
        JSON_TO_CARTELERA_PARSER parser = new JSON_TO_CARTELERA_PARSER();
        Cartelera c = parser.parseCJSON(json);
    try {
            Service.instance().crearCartelera(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
}





