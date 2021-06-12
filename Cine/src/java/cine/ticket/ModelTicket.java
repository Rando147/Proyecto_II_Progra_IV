package cine.ticket;

import java.util.HashMap;

public class ModelTicket {

    private static ModelTicket uniqueInstance;
    HashMap<String, Ticket> ticketes;
    TicketDAO dao;

    public static ModelTicket instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelTicket();
        }
        return uniqueInstance;
    }

    public ModelTicket() {
        this.dao = new TicketDAO();
    }

    private void listaTickets() {
        ticketes = dao.listarTicket();
    }

    public HashMap retornaLista() {
        ticketes = dao.listarTicket();
        return ticketes;
    }

    public void insertar(Ticket p) throws Exception {
        dao.crear(p);
    }

    public Ticket getTicket(String nombre) throws Exception {
        listaTickets();
        Ticket result = null;
        for (Ticket p : ticketes.values()) {
            if (p.getId().contains(nombre)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Ticket no existe");
        }
        return result;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }
}
