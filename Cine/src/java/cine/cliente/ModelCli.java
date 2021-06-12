package cine.cliente;

import java.util.HashMap;
import java.util.Map;

public class ModelCli {

    private static ModelCli uniqueInstance;
    HashMap<String, Cliente> clientes;
    ClienteDAO dao;

    public static ModelCli instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelCli();
        }
        return uniqueInstance;
    }

    public ModelCli() {
        this.dao = new ClienteDAO();
    }

    private void listaClientes() {
        clientes = dao.listarCli();
    }

    public HashMap retornaLista() {
        clientes = dao.listarCli();
        return clientes;
    }

    public void insertar(Cliente p) throws Exception {
        dao.crear(p);
    }

    public Cliente getCliente(String cedula) throws Exception {
        listaClientes();
        Cliente result = null;
        for (Cliente p : clientes.values()) {
            if (p.getNombre().contains(cedula)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Cliente no existe");
        }
        return result;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }

}
