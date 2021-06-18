package cine.ticket;

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

public class TicketDAO {

    private Database db;
    private static TicketDAO instancia;

    TicketDAO() {
        db = Database.instance();
    }

    public static TicketDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new TicketDAO();
        }
        return instancia;
    }

    public void crear(Ticket p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_AGREGAR);

        stm.setString(1, p.getId());
        stm.setString(2, p.getButaca());
        stm.setString(3, p.getCliente());
        stm.setString(4, p.getCartelera());

        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

    
    public HashMap listarTicket(){
        Ticket resultado = null;
        HashMap<String,Ticket> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                rs.getString("numero_Butaca"),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                                
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    
    }
    
    
    public Ticket recuperar(int id) {
        Ticket resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                rs.getString("numero_Butaca"),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    
    
    public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
    
    
    public HashMap listadoTickets(String id){
    Ticket resultado = null;
        HashMap<String,Ticket> peliculas = new HashMap<>();
        int x = Integer.parseInt(id);
        
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR_TICKET_CLIENTE)) {
                stm.clearParameters();
                stm.setInt(1, x);
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                rs.getString("numero_Butaca"),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                                
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    }
}
