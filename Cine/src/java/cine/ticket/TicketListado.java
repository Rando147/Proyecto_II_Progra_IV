
package cine.ticket;


public class TicketListado {
    String id;
    String nombre;
    String apellido;
    String sala;
    String pelicula;
    String fecha;
    String hora;
    String asiento;

    public TicketListado(String id, String nombre, String apellido, String sala, String pelicula, String fecha, String hora,String asiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sala = sala;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.asiento = asiento;
    }

    public TicketListado() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSala() {
        return sala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getAsiento() {
        return asiento;
    }
    
    
}
