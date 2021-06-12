
package cine.cartelera;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class CarteleraDAO {
    
    private Database db;
    private static CarteleraDAO instancia;

    CarteleraDAO() {
        db = Database.instance();
    }

    public static CarteleraDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new CarteleraDAO();
        }
        return instancia;
    }

     public void crear(Cartelera p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(CarteleraCRUD.CMD_AGREGAR);

        stm.setString(1, p.getId());
        stm.setDate(2, (Date) p.getFecha_funcion());
        stm.setTime(3, p.getHora_inicio());
        stm.setTime(4, p.getHora_fin()); 
        stm.setString(5, p.getPelicula());
        stm.setString(6, p.getSala());

        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
     
     public HashMap listarCart(){
        Cartelera resultado = null;
        HashMap<String,Cartelera> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(CarteleraCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Cartelera(
                                rs.getString("id"),
                                rs.getDate("fecha_funcion"),
                                rs.getTime("hora_inicio"),
                                rs.getTime("hora_fin"),
                                rs.getString("pelicula"),
                                rs.getString("sala")      
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    
    }

    
    public Cartelera recuperar(int id) {
        Cartelera resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(CarteleraCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Cartelera(
                                rs.getString("id"),
                                rs.getDate("fecha_funcion"),
                                rs.getTime("hora_inicio"),
                                rs.getTime("hora_fin"),
                                rs.getString("pelicula"),
                                rs.getString("sala") 
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }

    
public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(CarteleraCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
    
    
    
}
