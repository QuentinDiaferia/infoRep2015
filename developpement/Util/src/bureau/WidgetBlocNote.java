package bureau;

import java.lang.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WidgetBlocNote extends Widget implements Serializable{
    private String contenu;
    private final JPanel panneau;
    private JTextArea jta;
    private JScrollPane jsp;
    private ToucheListener tl=new ToucheListener();

    public WidgetBlocNote(String nom){
        super(nom);
        this.contenu =  "Vous pouvez écrire ici.";
        panneau = new JPanel();
        
        jta = new JTextArea(contenu, 15, 20);
        jta.setLineWrap(true);
        jsp = new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jta.addFocusListener(new FocusListener() {    
			public void focusGained(FocusEvent e) {
				jta.addKeyListener(tl);
			}

			public void focusLost(FocusEvent e) { }
        });
        panneau.add(jsp);
        this.setContentPane(panneau);
        this.setSize(265,275);
    }

    public String getContenu(){
        return this.contenu;
    }

    public void majListener(){
        jta.addKeyListener(tl);
    }

    public void setContenu(String _contenu){
        this.contenu = _contenu;
    }

    public String toString() {
        String result = new String(super.toString()+"\n Bloc note contenant le texte suivant : "+this.contenu);
        return result;
    }
    
    class ToucheListener implements KeyListener, Serializable {
		public void keyReleased(KeyEvent e) {
			setContenu(jta.getText());
			maj = true;
		}
		public void keyPressed(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {
			
		}	
	}
}
