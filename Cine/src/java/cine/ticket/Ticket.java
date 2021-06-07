
package cine.ticket;

import cine.cartelera.Cartelera;
import cine.cliente.Cliente;


public class Ticket {
    String id;
    String butaca;
    int cliente;
    int cartelera;

    public Ticket() {
    }

    public Ticket(String id, String butaca, int cliente, int cartelera) {
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

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getCartelera() {
        return cartelera;
    }

    public void setCartelera(int cartelera) {
        this.cartelera = cartelera;
    }
    
}
