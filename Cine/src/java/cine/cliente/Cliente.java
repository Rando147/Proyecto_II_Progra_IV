
package cine.cliente;

import cine.usuario.Usuario;


public class Cliente extends Usuario{

    private String nombre;
    private String apellido;
    private String numero_cuenta;

    public Cliente() {
    }

    public Cliente(String id, String pass, String nombre, String apellido, String numero_cuenta) {
        super(id, pass);
        this.nombre=nombre;
        this.apellido=apellido;
        this.numero_cuenta=numero_cuenta;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }
    
    
}
