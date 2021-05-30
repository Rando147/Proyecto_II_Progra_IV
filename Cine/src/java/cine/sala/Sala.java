
package cine.sala;

public class Sala {
    String sala;
    String butacas;

    public Sala() {
    }

    public Sala(String sala, String butacas) {
        this.sala = sala;
        this.butacas = butacas;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getButacas() {
        return butacas;
    }

    public void setButacas(String butacas) {
        this.butacas = butacas;
    }
    
}
