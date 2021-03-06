package controller;

import cine.cliente.Cliente;
import cine.cliente.JSON_TO_CLIENTE_PARSER;
import cine.ticket.JSON_TO_TICKET_PARSER;
import cine.ticket.Ticket;
import cine.ticket.TicketListado;
import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PUT;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import logic.Service;

@Path("usuario")
@PermitAll
public class User {

    @Context
    HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    //public List<Usuario> search(@DefaultValue("") @QueryParam("nombre") String nombre){
    public Usuario search() {
        Usuario s = new Usuario("sfdfsd", "sdfafsdsa");
        return s;
        //return Service.instance().usuarioListAll();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(Usuario usuario) {
        HttpSession sesion = request.getSession(true);
        Usuario logged = null;
        try {
            logged = (Cliente) Service.instance().getCliente(usuario.getId());
        } catch (Exception ex) {
            try {
                logged = Service.instance().getAdmin(usuario.getId());
            } catch (Exception ex2) {

            }
        }
        if (logged == null) {
            throw new NotFoundException("Usuario no existe");
        }

        if (!logged.getPassword().equals(usuario.getPassword())) {
            throw new NotAcceptableException("Clave incorrecta");
        }
        //Service.instance().login(logged);
        //logged.setPassword(null);//Borra el password del usuario
        request.getSession(true).setAttribute("Usuario", logged);
        return logged;
    }
    
    @GET 
    @Path("check-user")
    @Produces({MediaType.APPLICATION_JSON})
    @PermitAll
    public Usuario checkUser() {
        Usuario usr = (Usuario) request.getSession(true).getAttribute("Usuario"); 
        return usr;
    }

    @POST
    @Path("register")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario register(String json) {
        JSON_TO_CLIENTE_PARSER parser = new JSON_TO_CLIENTE_PARSER();//crea una clase para parsear el objeto
        Cliente cliente = parser.parseCJSON(json);//llamado a un metodo que crea un objeto cliete a mano a partir del string
        Usuario user = null;
        try {
            Service.instance().crearCliente(cliente);
            user = new Usuario(cliente.getId(), cliente.getPassword(), "CLIENTE");

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }

        HttpSession sesion = request.getSession(true);
        Usuario logged = null;
        try {
            logged = (Cliente) Service.instance().getCliente(user.getId());
        } catch (Exception ex) {
            try {
                logged = Service.instance().getAdmin(user.getId());
            } catch (Exception ex2) {

            }
        }
        if (logged == null) {
            throw new NotFoundException("Usuario no existe");
        }

        if (!logged.getPassword().equals(user.getPassword())) {
            throw new NotAcceptableException("Clave incorrecta");
        }
        //Service.instance().login(logged);
        //logged.setPassword(null);//Borra el password del usuario
        request.getSession(true).setAttribute("Usuario", logged);
        return logged;
    }

    @POST
    @Path("{idCartelera}/comprar")
    @RolesAllowed("CLIENTE")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TicketListado> comprar(String json, @PathParam("idCartelera") String idCartelera) {//idCartelera
        
        try {
            HttpSession session = request.getSession(true);
            Usuario user= (Usuario) request.getSession().getAttribute("Usuario");
            JSON_TO_TICKET_PARSER parser = new JSON_TO_TICKET_PARSER();//crea una clase para parsear el objeto
            try {
                user = (Cliente) Service.instance().getCliente(user.getId());
            } catch (Exception ex) {
                try {
                    user = Service.instance().getAdmin(user.getId());
                } catch (Exception ex2) {

                }
            }
            //hay que val??idar el usuario aqu??
            
            List<Ticket> ticket = parser.parseCJSON(idCartelera, json, user);
            
            int ticketID = Service.instance().crearTickets(ticket);
            List<TicketListado> tic =  Service.instance().obtenerTicket(Integer.toString(ticketID));
            return tic;
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
        @RolesAllowed("CLIENTE")
    public void update(Cliente cliente) {
        try {
            //Service.instance().clienteUpdate(p);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("logout")
    @RolesAllowed({"CLIENTE", "ADMINISTRATOR"})
    public void logout() {
        HttpSession session = request.getSession(true);
        session.removeAttribute("Usuario");
        session.invalidate();
        //Service.instance().logout();
    }
    
    @GET 
    @Path("{id}/ticketsListado")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"CLIENTE"})
    public List<TicketListado> listadoTickets(@PathParam("id") String json) {
        try {
            List<TicketListado> tl = new ArrayList<>();
            tl =Service.instance().VersionMejorada(json);
            System.out.print(tl);
            return tl;
            
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    
    

//    @GET
//    @Path("login")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Usuario login(@DefaultValue("") seatsArray String id, @QueryParam("password") String password) { 
//        Usuario usuario = new Usuario("01","hoysecome");
//        System.out.print("hoy no se come perros");
//        return usuario;
//    }
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
