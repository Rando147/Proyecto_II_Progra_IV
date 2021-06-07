
package cine.ticket;


public class TicketCRUD {

    protected static final String CMD_LISTAR
            = "SELECT * FROM Ticket"
            + "WHERE id_Ticket = ?;";


    protected static final String CMD_AGREGAR
            = "INSERT INTO Ticket (id_Ticket, numero_Butaca, id_Cliente, id_Cartelera) "
            + "VALUES (?, ?, ?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Ticket SET id_Ticket = ?, numero_Butaca = ?, id_Cliente = ?, id_Cartelera = ?"
            + "WHERE codigo = ?;";

    protected static final String CMD_ELIMINAR
            = "DELETE FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_LISTAR_TICKET_CLIENTE = ""
            + "SELECT * "
            + "FROM Ticket "
            + "WHERE id_Cliente = ?;";

//    protected static final String CMD_LISTAR_ESTUDIANTES_GRUPO = "SELECT codigo, nombre, apellid_Ticketo1, apellid_Ticketo2, nota "
//            + "FROM Ticket_has_estudiante "
//            + "INNER JOIN estudiante "
//            + "ON Ticket_has_estudiante.codigo = estudiante.id_TicketEstudiante "
//            + "WHERE Ticket_has_estudiante.Ticket_codigo = ?;";
//
//    // de aqui para abajo son los del Ticket incluyendo a los estudiantes
//    protected static final String CMD_MATRICULAR = "INSERT INTO Ticket_has_estudiante (Ticket_codigo, codigo) VALUES (?, ?);";
//
//    protected static final String CMD_RECUPERAREG
//            = "SELECT Ticket_codigo, Ticket_Curso_codigo, Ticket_profesor_id_TicketProfesor, estudiante_id_TicketEstudiante FROM Ticket_has_estudiante "
//            + "WHERE Ticket_codigo = ?; ";
//
//    protected static final String CMD_ELIMINAREG
//            = "DELETE FROM Ticket_has_estudiante "
//            + "WHERE Ticket_codigo = ?; ";
//
//    protected static final String CMD_RECUPERARPG
//            = "SELECT Ticket_codigo, Ticket_Curso_codigo, Ticket_profesor_id_TicketProfesor, estudiante_id_TicketEstudiante FROM Ticket_has_estudiante "
//            + "WHERE Ticket_profesor_id_TicketProfesor = ?; ";
//
//    protected static final String CMD_ACTUALIZARG
//            = "UPDATE Ticket SET Curso_codigo = ?, profesor_id_TicketProfesor = ?,fecha = ?"
//            + "WHERE codigo = ?;";

}