
package cine.ticket;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;

import javax.servlet.http.HttpServletRequest;

public class TicketDAO {

    private TicketDAO() {
        db = Database.instance();
    }

    public static TicketDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new TicketDAO();
        }
        return instancia;
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
                                rs.getInt("id_Cliente"),
                                rs.getInt("id_Cartelera")
                                
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

    
//    public Service listarTickets(String codigoCurso) {
//        Service listaTickets;
//        try {
//            listaTickets = new Service();
//            int codigoCursoInt = Integer.parseInt(codigoCurso);
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(TicketCRUD.CMD_Listar_CODIGO);
//            stm.setInt(1, codigoCursoInt);
//            ResultSet result = stm.executeQuery();
//            while (result.next()) {
//                Ticket aux = new Ticket(result.getInt("codigo"), result.getInt("Curso_codigo"), result.getInt("profesor_idProfesor"), result.getString("fecha"));
//                listaTickets.gruposAdd(aux);
//            }
//            return listaTickets;
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaTickets;
//    }
    
//    public Service listarTickets(int idProfesor) {
//        Service listaTickets;
//        try {
//            listaTickets = new Service();
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(TicketCRUD.CMD_LISTAR_GRUPOS_PROFESOR);
//            stm.setInt(1, idProfesor);
//            ResultSet result = stm.executeQuery();
//            while (result.next()) {
//                Ticket aux = new Ticket();
//                
//                int cedula = result.getInt(1);
//                String horario = result.getString(2);
//                
//                aux.setCodigo(cedula);
//                aux.setFecha(horario);
//                
//                listaTickets.gruposAdd(aux);
//            }
//            return listaTickets;
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaTickets;
//    }
//    
//    
//
//    public Service listarTickets() {
//
//        Service listaTickets = new Service();
//        Ticket auxTicket;
//        try (Connection cnx = db.getConnection(); PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR)) {
//
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    auxTicket = new Ticket(
//                            rs.getInt("codigo"),
//                            rs.getInt("profeid"),
//                            rs.getInt("cursoid"),
//                            rs.getString("fecha")
//                    );
//
//                    listaTickets.gruposAdd(auxTicket);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listaTickets;
//
//    }
//    
//    
//    public void matricular(int idTicket, int idEstudiante) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_MATRICULAR);
//        stm.setInt(1, idTicket);
//        stm.setInt(2, idEstudiante);
//
//        int count = Database.instance().executeUpdate(stm);
//        if (count == 0) {
//            throw new Exception("Error agregando matricula a la base de datos");
//        }
//    }
//
//    public Service listaTicketsProfe(HttpServletRequest request) {
//        Service listaTickets = new Service();
//        Ticket auxTicket;
//        try (Connection cnx = db.getConnection(); PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_RECUPERARPG)) {
//
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    auxTicket = new Ticket(
//                            rs.getInt("grupo_codigo"),
//                            rs.getInt("grupo_Curso_codigo")
//                    );
//
//                    listaTickets.gruposAdd(auxTicket);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listaTickets;
//    }
//
//    public void updateTicket(Ticket g) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_ACTUALIZARG);
//        stm.setInt(1, g.getCurso_codigo());
//        stm.setInt(2, g.getProfesor_idPreofesor());
//        stm.setString(3, g.getFecha());
//        stm.setInt(4, g.getCodigo());
//
//        int count = Database.instance().executeUpdate(stm);
//        if (count == 0) {
//            throw new Exception("xxxxxxxxxxxxxxxxxxxx");
//        }
//    }
//    
//    public logic.usuario.estudiante.Service listarEstudiatesTicket(int idTicket) {
//        logic.usuario.estudiante.Service listaEstudiantes;
//        try {
//            listaEstudiantes = new logic.usuario.estudiante.Service();
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(TicketCRUD.CMD_LISTAR_ESTUDIANTES_GRUPO);
//            stm.setInt(1, idTicket);
//            ResultSet result = stm.executeQuery();
//            while (result.next()) {
//                Estudiante aux = new Estudiante();
//                
//                int cedula = result.getInt(1);
//                String nombre = result.getString(2);
//                String apellido1 = result.getString(3);
//                String apellido2 = result.getString(4);
//                int nota = result.getInt(5);
//                
//                aux.setCedula(cedula);
//                aux.setNombre(nombre);
//                aux.setApellido1(apellido1);
//                aux.setApellido2(apellido2);
//                aux.setNota(nota);
//                
//                listaEstudiantes.estudiantesAdd(aux);
//            }
//            return listaEstudiantes;
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaTickets;
//    }
//    
//    public void crearTicket(Ticket g) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_AGREGAR);
////        stm.setInt(1, g.getCodigo());
////        stm.setInt(2, g.getCurso_codigo());
////        stm.setInt(3, g.getProfesor_idPreofesor());
////        stm.setString(4, g.getFecha());
//        stm.setInt(1, g.getCurso_codigo());
//        stm.setInt(2, g.getProfesor_idPreofesor());
//        stm.setString(3, g.getFecha());
//        
//
//        int count = Database.instance().executeUpdate(stm);
//        if (count == 0) {
//            throw new Exception("xxxxxxxxxxxxxxxxxxxx");
//        }
//    }
    
    
    
    
    
    private Database db;
    private static TicketDAO instancia;
}
