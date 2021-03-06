package bureau;

import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;

public class WidgetGalerie extends Widget implements Serializable{
    private Integer image;
    private HashMap<Integer, String> liensImages = new HashMap<>();
    private final JPanel panneau;
    private  PrecedentButtonListener precedentButton = new PrecedentButtonListener ();
    private  SuivantButtonListener suivantButton = new SuivantButtonListener ();
    private JButton precedent;
    private JButton suivant;

    public WidgetGalerie(String nom){
        super(nom);
        this.image = 1 ;
        this.liensImages.put(1, "../Images/galerie1.jpg");
        this.liensImages.put(2, "../Images/meteo.png");
        panneau = new JPanel(){
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File(getImageActuelle())), 0, 0, this);
                }
                catch(Exception e){
                    
                }
            }
        };

        add(panneau, BorderLayout.SOUTH);
        this.setContentPane(panneau);
        this.setSize(250,330);

        JButton precedent = new JButton("<");
        JButton suivant = new JButton(">");
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1,2));
        menu.add(precedent);
        menu.add(suivant);
        add(menu, BorderLayout.NORTH);

        //register listener
        

        //add listeners to corresponding componenets 
        precedent.addActionListener(this.precedentButton);
        suivant.addActionListener(this.suivantButton);

    }

    class PrecedentButtonListener implements ActionListener, Serializable {
        public void actionPerformed(ActionEvent e)
            {
                precedent();
                repaint(); 
            }
    }

    public void majListener(){
        precedent.addActionListener(precedentButton);
        suivant.addActionListener(suivantButton);
    }

    class SuivantButtonListener implements ActionListener, Serializable {
        public void actionPerformed(ActionEvent e)
            {
                suivant();
                repaint();                 
            }
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
        this.maj=true;
    }

    public void precedent(){
        if (this.image == 1) {
            this.image = this.liensImages.size();
        } else {
            this.image = this.image - 1;
        }
        this.maj=true;
    }

    public String toString() {
        String result = new String(super.toString()+"\n Galerie affichant l'image à l'adresse : "+this.liensImages.get(this.image));
        return result;
    }
}
