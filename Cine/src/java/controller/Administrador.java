
package controller;

import cine.cartelera.Cartelera;
import cine.pelicula.Pelicula;
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
    public void crear(Pelicula p){
    try {
            Service.instance().crearPelicula(p);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
    @Path("{id_pelicula}/image")
    public void addImage(@PathParam("id_pelicula") String id_pelicula, @FormDataParam("image") InputStream imageStream) {  
        try{
            
            int x = 0;
            Service.instance().insertarIMagen(id_pelicula, imageStream);
//                int read = 0;
//                byte[] bytes = new byte[1024];
//
//                OutputStream out = new FileOutputStream(new File(location + id_pelicula));
//                while ((read = imageStream.read(bytes)) != -1){out.write(bytes, 0, read);}
//                out.flush();
//                out.close();
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





