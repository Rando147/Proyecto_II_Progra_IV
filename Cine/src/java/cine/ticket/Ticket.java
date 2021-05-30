
package cine.ticket;

import cine.cartelera.Cartelera;
import cine.cliente.Cliente;


public class Ticket {
    String id;
    String butaca;
    Cliente cliente;
    Cartelera cartelera;

    public Ticket() {
    }

    public Ticket(String id, String butaca, Cliente cliente, Cartelera cartelera) {
        this.id = id;
        this.butaca = butaca;
        this.cliente = cliente;
        this.cartelera = cartelera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButaca() {
        return butaca;
    }

    public void setButaca(String butaca) {
        this.butaca = butaca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cartelera getCartelera() {
        return cartelera;
    }

    public void setCartelera(Cartelera cartelera) {
        this.cartelera = cartelera;
    }
    
}
