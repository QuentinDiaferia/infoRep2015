package bureau;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class Bureau extends JFrame implements Serializable, ActionListener{
    private final java.util.List<Widget> listeWidgets;
	private final java.util.List<Integer> listeUtilisateurs;
	public static final int nbMaxUtilisateurs = 4;
	public static final int nbMaxWidgets = 6;
    private final JDesktopPane panneauBureau;

    // Constructeur sans argument
	public Bureau() throws IOException{
        super("Bureau partagé");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension);
        panneauBureau = new JDesktopPane(){
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File("fondEcran.jpg")), 0, 0, this);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
           }
        };
        this.setContentPane(panneauBureau);
        this.setJMenuBar(createMenuBar());
        panneauBureau.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		this.listeWidgets = (java.util.List<Widget>)Collections.synchronizedList(new ArrayList<Widget>());
		this.listeUtilisateurs = (java.util.List<Integer>)Collections.synchronizedList(new ArrayList<Integer>());

	}

    // Constructeur par recopie
	public Bureau(Bureau b) throws IOException{
        super("Bureau partagé");
        panneauBureau = (JDesktopPane)b.getContentPane();
        this.setContentPane(panneauBureau);
        this.setJMenuBar(b.getJMenuBar());
        panneauBureau.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.listeWidgets = b.getListeWidgets();
        this.listeUtilisateurs = b.getListeUtilisateurs();
	}

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("Ajouter un widget");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //Set up the first menu item.
        JMenuItem meteo = new JMenuItem("Meteo");
        meteo.setMnemonic(KeyEvent.VK_N);
        meteo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        meteo.setActionCommand("meteo");
        meteo.addActionListener(this);
        menu.add(meteo);

        //Set up the second menu item.
        JMenuItem galerie = new JMenuItem("Galerie");
        galerie.setMnemonic(KeyEvent.VK_Q);
        galerie.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        galerie.setActionCommand("galerie");
        galerie.addActionListener(this);
        menu.add(galerie);

        //Set up the second menu item.
        JMenuItem blocnote = new JMenuItem("Bloc-Notes");
        blocnote.setMnemonic(KeyEvent.VK_Q);
        blocnote.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        blocnote.setActionCommand("blocnote");
        blocnote.addActionListener(this);
        menu.add(blocnote);

        JMenuItem calculatrice = new JMenuItem("Calculatrice");
        calculatrice.setMnemonic(KeyEvent.VK_Q);
        calculatrice.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        calculatrice.setActionCommand("calculatrice");
        calculatrice.addActionListener(this);
        menu.add(calculatrice);

        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.setMnemonic(KeyEvent.VK_Q);
        quitter.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        quitter.setActionCommand("quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if (("meteo").equals(e.getActionCommand())) {
            Widget widgetMeteo = new WidgetMeteo(true, "Meteo");
            ajouterWidget(widgetMeteo);
        } else {
            if (("galerie").equals(e.getActionCommand())) {
                Widget widgetGalerie = new WidgetGalerie(true, "Galerie");
                ajouterWidget(widgetGalerie);
            } else {
                if (("blocnote").equals(e.getActionCommand())) {
                    Widget widgetblocnote = new WidgetBlocNote(true, "Bloc-Notes");
                    ajouterWidget(widgetblocnote);
                } else {
                    if (("calculatrice").equals(e.getActionCommand())) {
                        Widget widgetcalculatrice = new WidgetCalculatrice(true, "Calculatrice");
                        ajouterWidget(widgetcalculatrice);
                    } else {
                        quit();
                    }
                }
            }
        }
    }

    //Create a new internal frame.
    protected void createFrame(Widget widget) {
        widget.setVisible(true); //necessary as of 1.3
        panneauBureau.add(widget);
        try {
            widget.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }

	// Méthode de copie
	public void copy(Bureau b){
		this.listeWidgets.clear();
		this.listeUtilisateurs.clear();
	    copyListeWidgets(this.listeWidgets,b.getListeWidgets());
	    copyListeUtilisateurs(this.listeUtilisateurs,b.getListeUtilisateurs());
	}

    // Méthodes de gestion des widgets
    public void ajouterWidget(Widget widget){
        this.listeWidgets.add(widget);
        this.createFrame(widget);
    }

    public void supprimerWidget(Widget widget){
        this.listeWidgets.remove((Object)widget);
    }

    public int getNbWidgets(){
		return this.listeWidgets.size() ;
	}

	public int getNbMaxWidgets(){
		return this.nbMaxWidgets ;
	}

	public java.util.List<Widget> getListeWidgets(){
	    return this.listeWidgets;
	}

    // Méthodes de gestion des utilisateurs
    public void ajouterUtilisateur(Integer ID){
        this.listeUtilisateurs.add(ID);
    }

    public void supprimerUtilisateur(Integer ID){
        this.listeUtilisateurs.remove((Object)ID);
    }

	public int getNbUtilisateurs(){
		return this.listeUtilisateurs.size() ;
	}

	public int getNbMaxUtilisateurs(){
		return this.nbMaxUtilisateurs ;
	}

	public java.util.List<Integer> getListeUtilisateurs(){
	    return this.listeUtilisateurs;
	}

    // Méthode toString
	public String toString() {
		String result = new String("Bureau possédant:\n"+ getNbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getNbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs);
		return result;
	}

	// Copie de liste
	private static void copyListeWidgets(java.util.List<Widget> destination, java.util.List<Widget> source){
        destination.clear();
        for( int i = 0 ; i < source.size() ; i++ ){
            destination.add(source.get(i));
        }
    }

    private static void copyListeUtilisateurs(java.util.List<Integer> destination, java.util.List<Integer> source){
        destination.clear();
        for( int i = 0 ; i < source.size() ; i++ ){
            destination.add(source.get(i));
        }
    }
}
