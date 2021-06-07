
package cine.cartelera;

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

public class CarteleraDAO {

    private CarteleraDAO() {
        db = Database.instance();
    }

    public static CarteleraDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new CarteleraDAO();
        }
        return instancia;
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
                                rs.getInt("fecha"),
                                rs.getInt("fecha")
                                
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

    
//    public Service listarCarteleras(String codigoCurso) {
//        Service listaCarteleras;
//        try {
//            listaCarteleras = new Service();
//            int codigoCursoInt = Integer.parseInt(codigoCurso);
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(CarteleraCRUD.CMD_Listar_CODIGO);
//            stm.setInt(1, codigoCursoInt);
//            ResultSet result = stm.executeQuery();
//            while (result.next()) {
//                Cartelera aux = new Cartelera(result.getInt("codigo"), result.getInt("Curso_codigo"), result.getInt("profesor_idProfesor"), result.getString("fecha"));
//                listaCarteleras.gruposAdd(aux);
//            }
//            return listaCarteleras;
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaCarteleras;
//    }
    
//    public Service listarCarteleras(int idProfesor) {
//        Service listaCarteleras;
//        try {
//            listaCarteleras = new Service();
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(CarteleraCRUD.CMD_LISTAR_GRUPOS_PROFESOR);
//            stm.setInt(1, idProfesor);
//            ResultSet result = stm.executeQuery();
//            while (result.next()) {
//                Cartelera aux = new Cartelera();
//                
//                int cedula = result.getInt(1);
//                String horario = result.getString(2);
//                
//                aux.setCodigo(cedula);
//                aux.setFecha(horario);
//                
//                listaCarteleras.gruposAdd(aux);
//            }
//            return listaCarteleras;
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaCarteleras;
//    }
//    
//    
//
//    public Service listarCarteleras() {
//
//        Service listaCarteleras = new Service();
//        Cartelera auxCartelera;
//        try (Connection cnx = db.getConnection(); PreparedStatement stm = cnx.prepareStatement(CarteleraCRUD.CMD_LISTAR)) {
//
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    auxCartelera = new Cartelera(
//                            rs.getInt("codigo"),
//                            rs.getInt("profeid"),
//                            rs.getInt("cursoid"),
//                            rs.getString("fecha")
//                    );
//
//                    listaCarteleras.gruposAdd(auxCartelera);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listaCarteleras;
//
//    }
//    
//    
//    public void matricular(int idCartelera, int idEstudiante) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(CarteleraCRUD.CMD_MATRICULAR);
//        stm.setInt(1, idCartelera);
//        stm.setInt(2, idEstudiante);
//
//        int count = Database.instance().executeUpdate(stm);
//        if (count == 0) {
//            throw new Exception("Error agregando matricula a la base de datos");
//        }
//    }
//
//    public Service listaCartelerasProfe(HttpServletRequest request) {
//        Service listaCarteleras = new Service();
//        Cartelera auxCartelera;
//        try (Connection cnx = db.getConnection(); PreparedStatement stm = cnx.prepareStatement(CarteleraCRUD.CMD_RECUPERARPG)) {
//
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    auxCartelera = new Cartelera(
//                            rs.getInt("grupo_codigo"),
//                            rs.getInt("grupo_Curso_codigo")
//                    );
//
//                    listaCarteleras.gruposAdd(auxCartelera);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (URISyntaxException | IOException | SQLException ex) {
//            Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listaCarteleras;
//    }
//
//    public void updateCartelera(Cartelera g) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(CarteleraCRUD.CMD_ACTUALIZARG);
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
//    public logic.usuario.estudiante.Service listarEstudiatesCartelera(int idCartelera) {
//        logic.usuario.estudiante.Service listaEstudiantes;
//        try {
//            listaEstudiantes = new logic.usuario.estudiante.Service();
//            Connection connection = db.getConnection();
//            PreparedStatement stm = connection.prepareStatement(CarteleraCRUD.CMD_LISTAR_ESTUDIANTES_GRUPO);
//            stm.setInt(1, idCartelera);
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
//            Logger.getLogger(CarteleraDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        //return listaCarteleras;
//    }
//    
//    public void crearCartelera(Cartelera g) throws Exception {
//
//        PreparedStatement stm = Database.instance().prepareStatement(CarteleraCRUD.CMD_AGREGAR);
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
    private static CarteleraDAO instancia;
}
