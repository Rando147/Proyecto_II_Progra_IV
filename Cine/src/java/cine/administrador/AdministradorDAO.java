
package cine.administrador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class AdministradorDAO {
    
    
    private AdministradorDAO(){
        db = Database.instance();
    }
    
    public static AdministradorDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new AdministradorDAO();
        return instancia;
    }
    
    public Administrador recuperar(int id) {
        Administrador resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(AdministradorCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Administrador(
                                rs.getString("id_Administrador"),
                                rs.getString("password") 
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    private Database db;
    private static AdministradorDAO instancia;
}