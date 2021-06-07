/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.Cliente;

public class ClienteCRUD {
    protected static final String CMD_LISTAR
            = "SELECT id_Cliente, password, nombre, apellido, numero_Cuenta FROM Cliente "
            + "ORDER BY apellido, nombre;";
    
    protected static final String CMD_AGREGAR
            = "INSERT INTO Cliente (id_Cliente, password, nombre, apellido, numero_Cuenta) "
            + "VALUES (?, ?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Cliente, password FROM Cliente "
            + "WHERE id_Cliente = ?; ";
    
    protected static final String CMD_ACTUALIZAR
            = "UPDATE Cliente SET id_Cliente = ?, password = ?, nombre = ?, apellido = ?, numero_Cuenta = ?"
            + "WHERE id_Cliente = ?;";
    
    protected static final String CMD_ELIMINAR
            = "DELETE FROM Cliente "
            + "WHERE id_Cliente = ?; ";
    
}