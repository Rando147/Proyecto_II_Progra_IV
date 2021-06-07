
package cine.cliente;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class ClienteDAO {
    
    
    private ClienteDAO(){
        db = Database.instance();
    }
    
    public static ClienteDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new ClienteDAO();
        return instancia;
    }
    
    public Cliente recuperar(int id) {
        Cliente resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(ClienteCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Cliente(
                                rs.getString("id_Cliente"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("numero_Cuenta")  
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    private Database db;
    private static ClienteDAO instancia;
}