package cine.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ModelTicket {

    private static ModelTicket uniqueInstance;
    HashMap<String, Ticket> ticketes;
    HashMap<String, TicketListado> ticketListado;
    TicketDAO dao;

    public static ModelTicket instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelTicket();
        }
        return uniqueInstance;
    }

    public ModelTicket() {
        this.dao = new TicketDAO();
        ticketes = new HashMap<>();
    }

    public ArrayList mejorado(String id){
    cleanHash();
    ticketListado = dao.listadoTicketsMejorado(id);
    return new ArrayList (ticketListado.values());
    
    }
    
    
    private void listaTickets() {
        cleanHash();
        ticketes = dao.listarTicket();
    }

    public HashMap retornaLista() {
        listaTickets();
        return ticketes;
    }

    void cleanHash() {
        if (!ticketes.isEmpty()) {
            ticketes.clear();
        }
    }

    public List<Ticket> retornaArrayList() {
        listaTickets();
        return new ArrayList(ticketes.values());
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
    
    public ArrayList listadoTickets(String id){
    cleanHash();
    ticketes=dao.listadoTickets(id);
    return new ArrayList(ticketes.values());
    
    }

    public void insertarTickets(List<Ticket> LT) throws Exception{
        try {
            Iterator<Ticket> TicketIterator = LT.iterator();
            while (TicketIterator.hasNext()) {
                insertar(TicketIterator.next());
            }
        } catch (Exception x) {

        }
    }
}