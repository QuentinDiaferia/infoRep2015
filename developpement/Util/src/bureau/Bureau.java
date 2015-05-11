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
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class Bureau extends JFrame implements Serializable, ActionListener{
    private final java.util.List<Widget> listeWidgets;
	private final java.util.List<Integer> listeUtilisateurs;
	public static final int nbMaxUtilisateurs = 3;
	public static final int nbMaxWidgets = 6;
	private final JDesktopPane panneauBureau;
	public static boolean maj;
	private static MouvementListener listener;

    // Constructeur sans argument
	public Bureau(){
        super("Bureau partagé");
        this.maj=false;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension);
        panneauBureau = new JDesktopPane(){
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File("../Images/fondEcran.jpg")), 0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight(), this);
                }
                catch(Exception e){
                   
                }
           }
        };
        this.setContentPane(panneauBureau);
        this.setJMenuBar(createMenuBar());
        System.out.println("t1");
        
        panneauBureau.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); 
		this.listeWidgets = (java.util.List<Widget>)Collections.synchronizedList(new ArrayList<Widget>());
		this.listeUtilisateurs = (java.util.List<Integer>)Collections.synchronizedList(new ArrayList<Integer>());
        panneauBureau.add(this.createLaunchBar());
		listener = new MouvementListener(this);
        this.copy(this);
        repaint();
        System.out.println("t2");
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
        System.out.println("t3");
	for(Widget w : listeWidgets) {
		w.addComponentListener(listener);
	}

        this.listeUtilisateurs = b.getListeUtilisateurs();
	}
	
	protected JPanel createLauncher(final String nom) {
		final String urlImg;
		final Widget widget;
		switch(nom) {
			case "Meteo":
				urlImg = "../Images/meteoLanceur.png";
				widget = new WidgetMeteo(true, nom);
				break;
			case "Bloc-Notes":
				urlImg = "../Images/blocLanceur.png";
				widget = new WidgetBlocNote(true, nom);
				break;
			case "Galerie":
				urlImg = "../Images/galerieLanceur.png";
				widget = new WidgetGalerie(true, nom);
				break;
			// case "Calculatrice":
			// 	urlImg = "../Images/calculLanceur.png";
			// 	widget = null;
			// 	break;
			default:
				urlImg = "../Images/decoLanceur.png";
				widget = null;
				break;
		}
		JPanel launcher = new JPanel() {
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File(urlImg)), 0, 0, this);
                }
                catch(Exception e){
                    
                }
            }
        };
        System.out.println("t4");
        launcher.setOpaque(false);
        launcher.setSize(117, 108);
        
        launcher.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(nom.equals("Quitter")){
                    listeUtilisateurs.remove(listeUtilisateurs.size()-1);
                    maj=true;
                    try{
                    Thread.sleep(100);
                    }catch(Exception ee){}
					quit();
                }
				else{
					ajouterWidget(widget);
                    maj=true;
                }

			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
        System.out.println("t5");
        return launcher;
	}
	
	protected JPanel createLaunchBar() {
		JPanel barre = new JPanel(new GridLayout(1, 5, 25, 0)){
            public void paintComponent(Graphics g){
                try{
                    super.paintComponent(g);
                    g.drawImage(ImageIO.read(new File("../Images/barreLancement.png")), 0, 0, this);
                }
                catch(Exception e){
                    
                }
            }
        };
        System.out.println("t6");
        barre.setOpaque(false);
        barre.setBounds((int)this.getSize().getWidth()/2-783/2, (int)this.getSize().getHeight()-155, 783, 110);

        barre.add(createLauncher("Meteo"));
        barre.add(createLauncher("Bloc-Notes"));
        barre.add(createLauncher("Galerie"));
        // barre.add(createLauncher("Calculatrice"));
        barre.add(createLauncher("Quitter"));
        System.out.println("t7");
        return barre;
	}


    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("Ajouter un widget");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);
        System.out.println("t8");
        //Set up the first menu item.
        JMenuItem meteo = new JMenuItem("Meteo");
        meteo.setMnemonic(KeyEvent.VK_N);
        meteo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M, ActionEvent.ALT_MASK));
        meteo.setActionCommand("meteo");
        meteo.addActionListener(this);
        menu.add(meteo);

        //Set up the second menu item.
        JMenuItem galerie = new JMenuItem("Galerie");
        galerie.setMnemonic(KeyEvent.VK_Q);
        galerie.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.ALT_MASK));
        galerie.setActionCommand("galerie");
        galerie.addActionListener(this);
        menu.add(galerie);
        System.out.println("t9");
        //Set up the second menu item.
        JMenuItem blocnote = new JMenuItem("Bloc-Notes");
        blocnote.setMnemonic(KeyEvent.VK_Q);
        blocnote.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_B, ActionEvent.ALT_MASK));
        blocnote.setActionCommand("blocnote");
        blocnote.addActionListener(this);
        menu.add(blocnote);

        // JMenuItem calculatrice = new JMenuItem("Calculatrice");
        // calculatrice.setMnemonic(KeyEvent.VK_Q);
        // calculatrice.setAccelerator(KeyStroke.getKeyStroke(
        // KeyEvent.VK_C, ActionEvent.ALT_MASK));
        // calculatrice.setActionCommand("calculatrice");
        // calculatrice.addActionListener(this);
        // menu.add(calculatrice);

        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.setMnemonic(KeyEvent.VK_Q);
        quitter.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        quitter.setActionCommand("quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        System.out.println("t10");
        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        System.out.println("t11");
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
                    // if (("calculatrice").equals(e.getActionCommand())) {
                    //     Widget widgetcalculatrice = new WidgetCalculatrice(true, "Calculatrice");
                    //     ajouterWidget(widgetcalculatrice);
                    // } else {
                        listeUtilisateurs.remove(listeUtilisateurs.size()-1);
                        maj=true;
                        try{
                        Thread.sleep(100);
                        }catch(Exception ee){}
                        quit();
                    
                }
            }
        }
        maj=true;
        System.out.println("t12");
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

    //Affichage du bureau
    public void affichageBureau(){
        System.out.println("t13");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension);
        this.setContentPane(panneauBureau);
        this.setJMenuBar(createMenuBar());
        panneauBureau.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        repaint();
    }

	// Méthode de copie
	public void copy(Bureau b){
        System.out.println("t14");
        listener = new MouvementListener(this);
        this.panneauBureau.removeAll();
        this.listeWidgets.clear();
        this.listeUtilisateurs.clear();
	    copyListeWidgets(this.listeWidgets,b.getListeWidgets());
	    copyListeUtilisateurs(this.listeUtilisateurs,b.getListeUtilisateurs());
        panneauBureau.add(this.createLaunchBar());
        repaint();
        System.out.println("t15");
	}

    // Méthodes de gestion des widgets
    public void ajouterWidget(Widget widget){
        System.out.println("t16");
        if(this.listeWidgets.size()<this.getNbMaxWidgets()){
            this.listeWidgets.add(widget);
            this.createFrame(widget);
            widget.addComponentListener(listener);
        }else{
            JOptionPane.showMessageDialog(panneauBureau, new String("Le nombre maximum de widgets est: "+this.getNbWidgets()+"."));

        }
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

    public boolean miseAJour(){
        System.out.println("t17");
        boolean res=false;
        Widget wSupp=null;
        if(this.maj){
            this.maj=false;
            res = true;
        }else{
            for(Widget w : this.listeWidgets){
                if(w.maj || w.isClosed()){

                    if(w.isClosed())
                        wSupp=w;

                    w.maj=false;
                    res = true;
                }
            }
        }
        if(wSupp!=null)
            this.listeWidgets.remove(wSupp);
        System.out.println("t18");
        return res;
        
    }

	public static void setMaj(boolean m) {
		maj = m;
        System.out.println("t19");
	}

    // Méthode toString
	public String toString() {
		String result = new String("Bureau possédant:\n"+ getNbWidgets() +" widgets et dont le nombre maximum est: "+nbMaxWidgets+"\n"+getNbUtilisateurs()+ " utilisateurs et dont le nombre maximum est: "+nbMaxUtilisateurs + " - etat :" + maj);
		return result;
	}

	// Copie de liste
	private  void copyListeWidgets(java.util.List<Widget> destination, java.util.List<Widget> source){
        System.out.println("t20");
        destination.clear();
        for( Widget temp : source ){
            destination.add(temp);
            createFrame(temp);
            temp.addComponentListener(listener);
        }
        System.out.println("t21");
        
    }

    private static void copyListeUtilisateurs(java.util.List<Integer> destination, java.util.List<Integer> source){
        destination.clear();
        for( int i = 0 ; i < source.size() ; i++ ){
            destination.add(source.get(i));
        }
    }

    /*class clicListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}*/
}
