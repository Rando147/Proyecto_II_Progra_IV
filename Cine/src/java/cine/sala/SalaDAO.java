package cine.sala;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;


public class SalaDAO {
    
    
    private SalaDAO(){
        db = Database.instance();
    }
    
    public static SalaDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new SalaDAO();
        return instancia;
    }
    
    public Sala recuperar(int id) {
        Sala resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(SalaCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Sala(
                                rs.getString("id_Sala"),
                                rs.getString("total_Butacas") 
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    private Database db;
    private static SalaDAO instancia;
}