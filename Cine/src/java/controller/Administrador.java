package controller;

import cine.cartelera.Cartelera;
import cine.cartelera.JSON_TO_CARTELERA_PARSER;
import cine.cliente.Cliente;
import cine.cliente.JSON_TO_CLIENTE_PARSER;
import cine.pelicula.Image;
import cine.pelicula.JSON_TO_PELICULA_PARSER;
import cine.pelicula.Pelicula;
import cine.sala.JSON_TO_SALA_PARSER;
import cine.sala.Sala;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotAcceptableException;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

    //String location = "C:\\Users\\boyro\\Documents\\GitHub\\Proyecto_II_Progra_IV\\Cine\\web\\Images/";
    String location = "/home/josedf/Documentos/Programacion IV/Proyecto II/Proyecto_II_Progra_IV/Cine/web/Images/";
    //String location = "C:\\Users\\Diego\\Documents\\Z I semestre\\1 Programacion\\proyecto 2\\Proyecto_II_Progra_IV\\Cine\\web\\Images/";
    
    @GET
    @Path("{name}/peli")
    @Produces(MediaType.APPLICATION_JSON)
    public Pelicula getImge(@PathParam("name") String name) throws IOException {
        try {       
        return Service.instance().getPelicula(name);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    @POST
    @Path("pelicula")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pelicula crearPeli(String json) {
        JSON_TO_PELICULA_PARSER parser = new JSON_TO_PELICULA_PARSER();
        Pelicula p = parser.parseCJSON(json);
        try {
            Service.instance().crearPelicula(p);
            return Service.instance().getPelicula(p.getNombre());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    
    //Carga las peliculas en la pagina principal en caso que el admin este loggeado
    @GET
    @Path("peliculas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pelicula> listDisp() {
        return Service.instance().peliculasListAll();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{id}/image")
    public void addImage(@PathParam("id") String id_pelicula, @FormDataParam("image") InputStream imageStream) {
        try {
            int read = 0;
                byte[] bytes = new byte[10000];
                OutputStream out = new FileOutputStream(new File(location + id_pelicula));
                while ((read = imageStream.read(bytes)) != -1){out.write(bytes, 0, read);}
                out.flush();
                out.close();
            //Service.instance().insertarImagen("2", imageStream);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

//    @GET
//    @Path("{id}/imagen")
//    @Produces("image/png")
//    public Response getImge(@PathParam("id") String id_pelicula) throws IOException {
////        try {
////            File file = new File(location + id_pelicula+".jpg");
////            FileOutputStream image = Service.instance().getImagen("1");
////            ResponseBuilder response = Response.ok((Object) image);
////            return response.build();
////
////        } catch (Exception ex) {
////            throw new NotAcceptableException();
////        }
//    }
//    @GET
//    @Path("{id}/imagen")
//    //@Produces("image/png")
//    //@Produces(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    //public Response getImge(@PathParam("id") String id_pelicula) throws IOException {
//    public Image getImge(@PathParam("id") String id_pelicula) throws IOException {
//        try {
//            //File file = new File(location + id_pelicula);
//            Image image = Service.instance().getImagen(id_pelicula);
//        //    ResponseBuilder response = Response.ok((Object) image);
//           // return response.build();
//            return image;
//        } catch (Exception ex) {
//            throw new NotAcceptableException();
//        }
//    }
    @POST
    @Path("sala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearSala(String json) {
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
    public void crear(String json) {
        JSON_TO_CARTELERA_PARSER parser = new JSON_TO_CARTELERA_PARSER();
        Cartelera c = parser.parseCJSON(json);
        try {
            Service.instance().crearCartelera(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    @DELETE
    @Path("borrar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void borrarPelicula(@PathParam("id") String idPelicula){
        try {
            Service.instance().eliminarPelicula(idPelicula);
        } catch (Exception ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @POST
    @Path("activar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void activarPelicula(String json) {
        JSON_TO_PELICULA_PARSER parser = new JSON_TO_PELICULA_PARSER();
        Pelicula pelicula = parser.parseCJSON(json);
        try {
            Service.instance().activarPelicula(pelicula.getId());
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

}
