package bureau;

import java.lang.*;
import java.io.*;

public abstract class Widget implements Serializable {
    private boolean statut;
    private String nom;
    private int x;
    private int y;
    private int z;
    
    public Widget(boolean statut, String nom, int x, int y, int z){
        this.setStatut(statut);
        this.setNom(nom);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    
    public void setStatut(boolean _statut){
        this.statut = _statut;
    }
    
    public void setNom(String _nom){
        this.nom = _nom;
    }
    
    public void setX(int _x){
        this.x = _x;
    }
    
    public void setY(int _y){
        this.y = _y;
    }
    
    public void setZ(int _z){
        this.z = _z;
    }
    
    public boolean getStatut(){
        return this.statut;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public String toString() {
        String result = new String("Widget nommé :"+this.nom+", avec le statut : "+this.statut+", avec les positions x,y,z :"+x+","+y+","+z+" .");
		return result;
    }
}
