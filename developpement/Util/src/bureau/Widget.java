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
    private static int openFrameCount = 1;
    private static final int xOffset = 10, yOffset = 10;
    public boolean affiche;
    public static boolean maj;

    public Widget(String nom){
        super("Widget " + nom,
        false, //resizable
        true, //closable
        false, //maximizable
        false);//iconifiable
        openFrameCount = openFrameCount+1;
        affiche=false;
        maj=false;
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

    }

    public void majListener(){
    }

    public String toString() {
        String result = new String("Widget nommé :"+this.getTitle());
        return result;
    }
    
}
