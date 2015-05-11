package bureau;

import java.lang.*;
import java.io.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.*;

public class WidgetMeteo extends Widget implements Serializable{
    private String meteo;
    private final JPanel panneau;

    public WidgetMeteo(String nom){
        super(nom);
        this.meteo =  "Il pleut aujourd'hui en Normandie.";
        panneau = new JPanel(){
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File("../Images/meteo.png")), 0, 0, this);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        };
        this.setContentPane(panneau);
        this.setSize(265,275);
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
