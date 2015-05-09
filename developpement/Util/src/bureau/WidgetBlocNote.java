package bureau;

import java.lang.*;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import java.awt.*;

public class WidgetBlocNote extends Widget implements Serializable{
    private String contenu;
    private final JPanel panneau;
    private JTextArea jta;;

    public WidgetBlocNote(boolean statut, String nom){
        super(statut, nom);
        this.contenu =  "Vous pouvez écrire ici.";
        panneau = new JPanel();
        
        jta = new JTextArea(15, 20);
        jta.setLineWrap(true);
        JScrollPane scrollbar = new JScrollPane(jta);
		scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        panneau.add(scrollbar);
        this.setContentPane(panneau);
        this.setSize(265,275);
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
