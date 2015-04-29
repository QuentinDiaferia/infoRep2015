import java.util.*;
import java.io.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.io.IOException;

import bureau.*;

public class Fenetre extends JFrame implements Serializable{
    private Bureau bureau = new Bureau();

    public Fenetre() throws IOException{
        this.setTitle("Bureau partagé");
        this.setSize(bureau.getFondEcran().getIconWidth(), bureau.getFondEcran().getIconHeight());
        this.setLocationRelativeTo(null);
        this.setContentPane(bureau);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Fenetre(Bureau b) throws IOException{
        this.setTitle("Bureau partagé");
        this.setSize(bureau.getFondEcran().getIconWidth(), bureau.getFondEcran().getIconHeight());
        this.setLocationRelativeTo(null);
        this.setContentPane(b);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Bureau getBureau(){
        return this.bureau;
    }
}
