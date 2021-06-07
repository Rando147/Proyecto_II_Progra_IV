
package cine.pelicula;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class PeliculaDAO {
    
    
    private PeliculaDAO(){
        db = Database.instance();
    }
    
    public static PeliculaDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new PeliculaDAO();
        return instancia;
    }
    
    public Pelicula recuperar(int id) {
        Pelicula resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
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