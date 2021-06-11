package controller;

import cine.usuario.Usuario;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("usuario")
@PermitAll
public class User {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    //public List<Usuario> search(@DefaultValue("") @QueryParam("nombre") String nombre){
    public Usuario search() {
        Usuario s = new Usuario("sfdfsd","sdfafsdsa");
        return s;
        //return Service.instance().usuarioListAll();
    }
    

    @GET
    @Path("login")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario search(@DefaultValue("") @QueryParam("nombre") String nombre) { 
        Usuario usuario = new Usuario("01","hoysecome");
        System.out.print("hoy no se come perros");
        return usuario;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Usuario login(Usuario usuario) {  
        
        System.out.print("hoy no se come perros");
         Usuario logged=null;
         //request.getSession(true).setAttribute("Usuario", logged);
        usuario = new Usuario("01","hoysecome");
        System.out.print("hoy no se come perros");
        return usuario;
        //return logged;    
//            try {
//                logged= Model.instance().get(usuario);
//                if(!logged.getPass().equals(usuario.getPass())) throw new Exception("Clave incorrecta");
//                request.getSession(true).setAttribute("user", logged);
//                return logged;
//            } catch (Exception ex) {
//                throw new NotFoundException();
//            }  
    }
    
    @DELETE 
    public void logout() {  
//        //HttpSession session = request.getSession(true);
//        session.removeAttribute("user");           
//        session.invalidate();
    }
    

    
    
    
//    @GET
//    @Path("{cedula}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Usuario get(@PathParam("cedula") String cedula) {
//        try {
//            return Service.instance().personaEdit(cedula);
//        } catch (Exception ex) {
//            throw new NotFoundException(); 
//        }
//    }
    
//    @GET
//    @Path("{cedula}/imagen")
//    @Produces("image/png")
//    public Response getImge(@PathParam("cedula") String cedula) throws IOException {
//        File file = new File(location+cedula);
//        ResponseBuilder response = Response.ok((Object) file);
//        return response.build();
//    }    

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void add(Usuario u) {  
        try {
            //Service.instance().usuarioAdd(u);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA) 
//    @Path("{cedula}/imagen")
//    public void addImage(@PathParam("cedula") String cedula, @FormDataParam("imagen") InputStream imagenStream) {  
//        try{
//                int read = 0;
//                byte[] bytes = new byte[1024];
//
//                OutputStream out = new FileOutputStream(new File(location + cedula));
//                while ((read = imagenStream.read(bytes)) != -1){out.write(bytes, 0, read);}
//                out.flush();
//                out.close();
//            } catch (Exception ex) {
//                throw new NotAcceptableException(); 
//            }
//    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Usuario p) {  
        try {
            //Service.instance().usuarioUpdate(p);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    

    @DELETE
    @Path("{id}")
    public void del(@PathParam("cedula") String cedula) {
        try {
            //Service.instance().personaDelete(cedula);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }

}

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("mujeres")
//    public List<Usuario> searchMujeres() { 
//        List<Usuario> todos = Service.instance().usuarioListAll("");
//        List<Usuario> mujeres = new ArrayList<>();
//        for(Usuario p: todos){ if(p.getSexo().equals("F")) mujeres.add(p);};
//        return mujeres;
//    }  
    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON})  
//    @Path("filtrar")    
//    public List<Usuario> filtrar(Usuario filtro) {  
//        List<Usuario> todos=Service.instance().personaSearch("");
//        List<Usuario> filtrados = new ArrayList<>();
//        for(Usuario p: todos){ 
//            if (    p.getCedula().contains(filtro.getCedula())
//                  && p.getNombre().contains(filtro.getNombre())
//                  && p.getSexo().contains(filtro.getSexo()))  filtrados.add(p);
//        };
//        return filtrados;
//    }    
}
