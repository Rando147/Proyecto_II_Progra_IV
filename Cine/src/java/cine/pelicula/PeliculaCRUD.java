
package cine.pelicula;

public class PeliculaCRUD {
    protected static final String CMD_LISTAR
            = "SELECT id_Pelicula, Nombre, duracion, descripcion FROM Pelicula "
            + "ORDER BY id_Pelicula;";
    
    protected static final String CMD_AGREGAR
            = "INSERT INTO Pelicula (Nombre, duracion, descripcion) "
            + "VALUES (?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Pelicula, Nombre, duracion, descripcion FROM Pelicula "
            + "WHERE id_Pelicula = ?; ";
    
    protected static final String CMD_ACTUALIZAR
            = "UPDATE Pelicula SET id_Pelicula = ?, Nombre = ?, duracion = ?, descripcion = ?"
            + "WHERE id_Pelicula = ?;";
    
    protected static final String CMD_ELIMINAR
            = "DELETE FROM Pelicula "
            + "WHERE id_Pelicula = ?; ";
    
}