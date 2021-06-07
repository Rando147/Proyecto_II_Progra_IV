
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

//    protected static final String CMD_LISTAR_ESTUDIANTES_GRUPO = "SELECT codigo, nombre, apellido1, apellido2, nota "
//            + "FROM Cartelera_has_estudiante "
//            + "INNER JOIN estudiante "
//            + "ON Cartelera_has_estudiante.codigo = estudiante.idEstudiante "
//            + "WHERE Cartelera_has_estudiante.Cartelera_codigo = ?;";
//
//    // de aqui para abajo son los del Cartelera incluyendo a los estudiantes
//    protected static final String CMD_MATRICULAR = "INSERT INTO Cartelera_has_estudiante (Cartelera_codigo, codigo) VALUES (?, ?);";
//
//    protected static final String CMD_RECUPERAREG
//            = "SELECT Cartelera_codigo, Cartelera_Curso_codigo, Cartelera_profesor_idProfesor, estudiante_idEstudiante FROM Cartelera_has_estudiante "
//            + "WHERE Cartelera_codigo = ?; ";
//
//    protected static final String CMD_ELIMINAREG
//            = "DELETE FROM Cartelera_has_estudiante "
//            + "WHERE Cartelera_codigo = ?; ";
//
//    protected static final String CMD_RECUPERARPG
//            = "SELECT Cartelera_codigo, Cartelera_Curso_codigo, Cartelera_profesor_idProfesor, estudiante_idEstudiante FROM Cartelera_has_estudiante "
//            + "WHERE Cartelera_profesor_idProfesor = ?; ";
//
//    protected static final String CMD_ACTUALIZARG
//            = "UPDATE Cartelera SET Curso_codigo = ?, profesor_idProfesor = ?,fecha = ?"
//            + "WHERE codigo = ?;";

}