package bureau;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public abstract class Widget extends JInternalFrame implements Serializable {
    private static int openFrameCount = 0;
    private static boolean statut;
    private static final int xOffset = 30, yOffset = 30;
    public boolean affiche;
    public static boolean maj;
    InternalFrameListener listener = new InternalFrameListener() {
        public void internalFrameActivated(InternalFrameEvent event) {}
        public void internalFrameClosed(InternalFrameEvent event) {
            maj=true;
        }
        public void internalFrameClosing(InternalFrameEvent event) {}
        public void internalFrameDeactivated(InternalFrameEvent event) {}
        public void internalFrameDeiconified(InternalFrameEvent event) {}
        public void internalFrameIconified(InternalFrameEvent event) {}
        public void internalFrameOpened(InternalFrameEvent event) {}
    };

    public Widget(boolean statut, String nom){
        super("Widget " + nom,
        false, //resizable
        true, //closable
        false, //maximizable
        false);//iconifiable
        this.setStatut(statut);
        openFrameCount = openFrameCount+1;
        affiche=false;
        maj=false;
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

        addInternalFrameListener(listener);
    }

    public void setStatut(boolean _statut){
        this.statut = _statut;
    }

    public boolean getStatut(){
        return this.statut;
    }

    public void majListener(){
        addInternalFrameListener(listener);
    }

    public String toString() {
        String result = new String("Widget nommé :"+this.getTitle()+", avec le statut : "+this.getStatut());
        return result;
    }
    
    
}