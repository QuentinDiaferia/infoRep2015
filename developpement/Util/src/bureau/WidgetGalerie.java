package bureau;

import java.lang.*;
import java.io.*;
import java.util.*;

public class WidgetGalerie extends Widget implements Serializable{
    private Integer image;
    private HashMap<Integer, String> liensImages = new HashMap<>();

    public WidgetGalerie(boolean statut, String nom){
        super(statut, nom);
        this.image = 1 ;
        this.liensImages.put(1, "lien");
    }

    public Integer getImage(){
        return this.image;
    }

    public String getImageActuelle(){
        return this.liensImages.get(this.image);
    }

    public HashMap<Integer, String> getImages(){
        return this.liensImages;
    }

    public void setImage(Integer _image){
        this.image = _image;
    }

    public void suivant(){
        if (this.liensImages.size() > this.image) {
            this.image = this.image + 1;
        } else {
            this.image = 1;
        }
    }

    public void precedent(){
        if (this.image == 1) {
            this.image = this.liensImages.size();
        } else {
            this.image = this.image - 1;
        }
    }

    public String toString() {
        String result = new String(super.toString()+"\n Galerie affichant l'image à l'adresse : "+this.liensImages.get(this.image));
        return result;
    }
}
