
package cine.pelicula;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class PeliculaDAO {
    
    
    PeliculaDAO(){
        db = Database.instance();
    }
    
    public static PeliculaDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new PeliculaDAO();
        return instancia;
    }
    
    public void crear(Pelicula p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_AGREGAR);

//        stm.setInt(1, p.getCedula());
//        stm.setString(2, p.getNombre());
//        stm.setString(3, p.getApellido1());
//        stm.setString(4, p.getApellido2());
//        stm.setString(5, p.getCorreo());
//        stm.setString(6, p.getNumero());
//        stm.setString(7, p.getEspecialidad());
//        stm.setString(8, p.getPassword());

        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
    
    public HashMap listarPeli(){
        Pelicula resultado = null;
        HashMap<String,Pelicula> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                //stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Pelicula(
                                rs.getString("id_Pelicula"),
                                rs.getString("Nombre"),
                                rs.getString("duracion"),
                                rs.getString("descripcion")
                                
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    
    }
    
    
    
    
    public Pelicula recuperar(String id) {
        Pelicula resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Pelicula(
                                rs.getString("id_Pelicula"),
                                rs.getString("Nombre"),
                                rs.getString("duracion"),
                                rs.getString("descripcion") 
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    private Database db;
    private static PeliculaDAO instancia;
}