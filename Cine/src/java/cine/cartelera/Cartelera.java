
package cine.cartelera;

import cine.pelicula.Pelicula;
import cine.sala.Sala;
import java.sql.Time;
import java.util.Date;


public class Cartelera {
    String id;
    Date fecha_funcion;
    Time hora_inicio;
    Time hora_fin;
    int pelicula;
    int sala;

    public Cartelera() {
    }

    public Cartelera(String id, Date fecha_funcion, Time hora_inicio, Time hora_fin, int pelicula, int sala) {
        this.id = id;
        this.fecha_funcion = fecha_funcion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.pelicula = pelicula;
        this.sala = sala;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_funcion() {
        return fecha_funcion;
    }

    public void setFecha_funcion(Date fecha_funcion) {
        this.fecha_funcion = fecha_funcion;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
    
}
