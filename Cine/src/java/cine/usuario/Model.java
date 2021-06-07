
package cine.usuario;


public class Model {

    public Model() {
        tmpUser = new Usuario();
    }

    public Model(Usuario usr) {
        this.tmpUser = usr;
    }

    public Usuario getUsr() {
        return tmpUser;
    }

    public void setUsr(Usuario usr) {
        this.tmpUser = usr;
    }
    
    private Usuario tmpUser;
}