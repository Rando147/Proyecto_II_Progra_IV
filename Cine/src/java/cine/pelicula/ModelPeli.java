package cine.pelicula;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelPeli {

    private static ModelPeli uniqueInstance;
    HashMap<String, Pelicula> peliculas;
    PeliculaDAO dao;

    public static ModelPeli instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelPeli();
        }
        return uniqueInstance;
    }

    public ModelPeli() {
        this.dao = new PeliculaDAO();
    }

    private void listaPeliculas() {
        peliculas = dao.listarPeli();
    }

    public HashMap retornaLista() {
        peliculas = dao.listarPeli();
        return peliculas;
    }

    //crear, obtener por XXXX, listar, eliminar*** 
    public void insertar(Pelicula p) throws Exception {
        dao.crear(p);
    }

    public Pelicula getPelicula(String nombre) throws Exception {
        listaPeliculas();
        Pelicula result = null;
        for (Pelicula p : peliculas.values()) {
            if (p.getNombre().contains(nombre)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Pelicula no existe");
        }
        return result;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }

    public void insertarIMagen(String id_Pelicula, InputStream image) throws Exception {
        dao.insertImage(id_Pelicula, image);
    }

}
