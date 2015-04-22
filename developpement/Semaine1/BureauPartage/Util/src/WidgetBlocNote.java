package bureau;

import java.lang.*;
import java.io.*;

public class WidgetBlocNote extends Widget implements Serializable{
    private String contenu;    
    
    public WidgetBlocNote(boolean statut, String nom, int x, int y, int z){
        super(statut, nom, x, y, z);
        this.contenu =  "Vous pouvez écrire ici.";
    }
    
    public String getContenu(){
        return this.contenu;
    }
    
    public void setContenu(String _contenu){
        this.contenu = _contenu;
    }
        
    public String toString() {
        String result = new String(super.toString()+"\n Bloc note contenant le texte suivant : "+this.contenu);
		return result;
    }
}
