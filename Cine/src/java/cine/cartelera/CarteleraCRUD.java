
package cine.cartelera;

public class CarteleraCRUD {

    protected static final String CMD_LISTAR
            = "SELECT * FROM Cartelera"
            + "WHERE id = ?;";

    protected static final String CMD_Listar_PELICULA = "SELECT * from Cartelera WHERE pelicula = ?;";

    protected static final String CMD_AGREGAR
            = "INSERT INTO Cartelera (id, fecha_funcion, hora_inicio, hora_fin, pelicula, sala) "
            + "VALUES (?, ?, ?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id, fecha_funcion, hora_inicio, hora_fin, pelicula, sala FROM Cartelera "
            + "WHERE id = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Cartelera SET id = ?, fecha_funcion = ?, hora_inicio = ?, hora_fin = ?, pelicula = ?, sala = ?"
            + "WHERE codigo = ?;";

    protected static final String CMD_ELIMINAR
            = "DELETE FROM Cartelera "
            + "WHERE id = ?; ";

    protected static final String CMD_LISTAR_CARTELERA_PELICULA = ""
            + "SELECT * "
            + "FROM Cartelera "
            + "WHERE pelicula = ?;";


}