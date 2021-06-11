
package cine.sala;

import java.util.Map;


public class ModelSala {
     private static ModelSala uniqueInstance;
     static Map<String,Sala> salas;
     private Sala tmpSala;
  
    
    public static ModelSala instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelSala();
        }
        return uniqueInstance;
    }

    public ModelSala() {
        tmpSala = new Sala();
    }

    public ModelSala(Sala sala) {
        this.tmpSala = sala;
    }

    public Sala getSala() {
        return tmpSala;
    }

    public void setSala(Sala sala) {
        this.tmpSala = sala;
    }
    
    
    
    
     public static Sala get(Sala s)throws Exception{
         String aux=s.getSala();
        Sala result = salas.get(aux);
        if (result==null) throw new Exception("Sala no existe");
        return result;
    }
    
}
