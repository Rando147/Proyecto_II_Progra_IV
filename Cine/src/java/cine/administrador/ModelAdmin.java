
package cine.administrador;

import java.util.HashMap;


public class ModelAdmin {
    
    private static ModelAdmin uniqueInstance;
    HashMap<String, Administrador> admin;
    AdministradorDAO dao;
    
    public static ModelAdmin instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelAdmin();
        }
        return uniqueInstance;
    }
    
    public ModelAdmin() {
        this.dao = new AdministradorDAO();
    }
    
    private void listaAdministradors() {
        admin = dao.listarAdmin();
    }

    //crear, obtener por XXXX, listar, eliminar*** 
    public void insertar(Administrador p) throws Exception {
        dao.crear(p);
    }
    
    public Administrador getAdmin(String nombre) throws Exception {
        listaAdministradors();
        Administrador result = null;
        for (Administrador p : admin.values()) {
            if (p.getId().contains(nombre)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Administrador no existe");
        }
        return result;
    }
    
    public void eliminar(String id) throws Exception{
        dao.eliminar(id);
    }
    
}
