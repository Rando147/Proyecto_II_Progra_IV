
package cine.ticket;


public class TicketCRUD {

    protected static final String CMD_LISTAR
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket";
            //+ "WHERE id_Ticket = ?;";

    protected static final String CMD_AGREGAR
            = "INSERT INTO Ticket (id_Ticket, numero_Butaca, id_Cliente, id_Cartelera) "
            + "VALUES (?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Ticket SET id_Ticket = ?, numero_Butaca = ?, id_Cliente = ?, id_Cartelera = ?"
            + "WHERE codigo = ?;";

    protected static final String CMD_ELIMINAR
            = "DELETE FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_LISTAR_TICKET_CLIENTE 
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket "
            + "WHERE id_Cliente = ?; ";
}