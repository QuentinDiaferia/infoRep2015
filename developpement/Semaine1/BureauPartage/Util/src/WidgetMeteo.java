package bureau;

import java.lang.*;
import java.io.*;

public class WidgetMeteo extends Widget implements Serializable{
    private String meteo;    
    
    public WidgetMeteo(boolean statut, String nom, int x, int y, int z){
        super(statut, nom, x, y, z);
        this.meteo =  "Il pleut aujourd'hui en Normandie.";
    }
    
    public String getMeteo(){
        return this.meteo;
    }
    
    public void setMeteo(String _meteo){
        this.meteo = _meteo;
    }
        
    public String toString() {
        String result = new String(super.toString()+"\n La météo d'aujourd'hui : "+this.meteo);
		return result;
    }
}
