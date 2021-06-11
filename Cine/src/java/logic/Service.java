package logic;

import cine.pelicula.Pelicula;
import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Service {
//
//    private static Service uniqueInstance;
//    HashMap<String,Pelicula> peliculas;
//    HashMap<String,Usuario> usuarios;
//    
//    
//    
//    public static Service instance() {
//        if (uniqueInstance == null) {
//            uniqueInstance = new Service();
//        }
//        return uniqueInstance;
//    }
//    private Service(){
//        peliculas = new HashMap<> ();
//        //String id, String nombre, String descripcion
//        peliculas.put("01", new Pelicula("01","Spider Man","The amazing spider-man","01:45"));
//        peliculas.put("02", new Pelicula("02","Matrix","God Keanu reeves","02:05"));
//        peliculas.put("03", new Pelicula("03","Matrix II","Super God Keanu Reeves","01:38"));
//        peliculas.put("04", new Pelicula("04","Sponge Bob","GOD KEANU REEVES","01:50"));
//        
//        
//        usuarios = new HashMap<> ();
//        usuarios.put("04", new Usuario("00","aaa"));
//        usuarios.put("04", new Usuario("01","bbb"));
//        usuarios.put("04", new Usuario("02","ccc"));
//        usuarios.put("04", new Usuario("03","ddd"));
//    }
//    
//    
//    
//    
//    
//    public List<Pelicula> peliculaListAll() {
//        return new ArrayList(peliculas.values());
//    } 
//    public List<Usuario> usuarioListAll() {
//        return new ArrayList(usuarios.values());
//    }
//    public List<Pelicula> peliculaSearch(String nombre) {
//        List<Pelicula> result = new ArrayList<>();
//        for(Pelicula p:peliculas.values()){
//            if(p.getNombre().contains(nombre)) result.add(p);
//        }
//        return result;
//    } 
//    public Pelicula peliculaAdd(Pelicula pel)throws Exception {
//        if (peliculas.get(pel.getId())!=null){
//            throw new Exception ("406-pelicula ya existe");
//        }
//        else{
//            peliculas.put(pel.getId(),pel);
//            return pel;
//        }
//    }    
//    public void peliculaUpdate(Pelicula per)throws Exception {
//        if (peliculas.get(per.getId())==null){
//            throw new Exception ("404-pelicula no existe");
//        }
//        else{
//            peliculas.put(per.getId(), per);
//        }
//    }   
//    public void peliculaDelete(String id)throws Exception {
//        if (peliculas.get(id)==null){
//            throw new Exception ("404-pelicula no existe");
//        }
//        else{
//            peliculas.remove(id);
//        }
//    } 
//    public Pelicula peliculaEdit(String id)throws Exception {
//        if (peliculas.get(id)!=null){
//            return peliculas.get(id);     
//        }
//        else{
//            throw new Exception ("404-pelicula no existe");
//        }
//    }
}