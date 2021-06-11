
package cine.cliente;

import java.util.Map;


public class ModelCli {
    private static ModelCli uniqueInstance;
     static Map<String,Cliente> clientes;
    
    public static ModelCli instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelCli();
        }
        return uniqueInstance;
    }

    public ModelCli() {
        tmpUser = new Cliente();
    }

    public ModelCli(Cliente usr) {
        this.tmpUser = usr;
    }

    public Cliente getUsr() {
        return tmpUser;
    }

    public void setUsr(Cliente usr) {
        this.tmpUser = usr;
    }
    
    private Cliente tmpUser;
    
    
//     public static Cliente get(Cliente id)throws Exception{
//        Cliente result = clientes.get(id.getCliente());
//        if (result==null) throw new Exception("Cliente no existe");
//        return result;
//    }
    
}
