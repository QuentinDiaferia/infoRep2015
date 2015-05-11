import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class ServeurConnexionsMultiples implements Runnable {
    private Socket s;
    private int ID;
    public static BufferedInputStream bis;
    OutputStream os;
    public final ArrayList<Socket> listeSockets;
    public Thread t3;
    public final Bureau bureau;

    public ServeurConnexionsMultiples(Socket so, ArrayList<Socket> sockets, int i, Bureau bureau) {
        this.s = so;
        this.listeSockets = sockets;
        this.ID = i;
        this.bureau = bureau;
    }

    public static void main(String[] args) {
        int port;
        int nbConnexions = 0;
        ArrayList<Socket> listeSockets = new ArrayList<Socket>();
        try {
            Bureau bureau = new Bureau();
            if(args.length!=1) {
                System.out.println("Veuillez entrer le numéro de port que vous souhaitez utiliser.");
            }else {
                port = Integer.parseInt(args[0]);
                ServerSocket serverSocket1 = new ServerSocket(port);

                System.out.println("Serveur en attente de connexion.");
                // boucle infinie permettant les connexions multiples
                while (true) {
					Socket s = serverSocket1.accept();
                      // ajout du socket de l'utilisateur demandant la connexion
                      listeSockets.add(s);
                      // initialisation du thread de broadcast
                      Thread broadcast = new Thread(new EmissionServeur(listeSockets,bureau));
					Runnable runnable = new ServeurConnexionsMultiples(s, listeSockets, ++nbConnexions, bureau);
					if(nbConnexions < bureau.getNbMaxUtilisateurs()+1) {
                        bureau.ajouterUtilisateur(new Integer(nbConnexions));
						System.out.println("Connexion d'un utilisateur, mise à jour du bureau en conséquence \n" + bureau.toString());
	
            broadcast.start();
						Thread thread = new Thread(runnable);
            bis = new BufferedInputStream(s.getInputStream());
						thread.start();
					} else {
						System.out.println("Dépassement du nombre d'utilisateur maximum.");
						OutputStream os;
						ObjectOutputStream oos;
						os = s.getOutputStream();
						oos = new ObjectOutputStream(os);
						oos.writeObject(null);
						oos.close();
						os.close();
						s.close();
					}
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void run() {
        try {
            Thread t3 = new Thread(new ReceptionServeur(s, bureau, listeSockets));
            t3.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
