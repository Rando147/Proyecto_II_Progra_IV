/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cine.pelicula.Pelicula;
import logic.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author boyro
 */
@Path("cartelera")
@PermitAll
public class Carteleras {

    String location = "C:\\Users\\boyro\\Documents\\GitHub\\Proyecto_II_Progra_IV\\Cine\\web\\Images/";

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pelicula> listAll() {
        return Service.instance().peliculasListAll();
    }

    @GET
    @Path("{id}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("id") String id_pelicula) throws IOException {
        try {
            File file = new File(location + id_pelicula + ".jpg");
            //Image image = Service.instance().getImagen("1");
            Response.ResponseBuilder response = Response.ok((Object) file);
            return response.build();

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

}
