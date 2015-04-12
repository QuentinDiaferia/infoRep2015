import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class ServeurConnexionsMultiples implements Runnable {
    private Socket s;
    private int ID;
    public static BufferedInputStream bis;
    OutputStream os;
    public Thread t2, t3;
    
    private static Bureau bureau;
    
    static {
		bureau = new Bureau();
	}

    public ServeurConnexionsMultiples(Socket socket, int i) {
        this.s = socket;
        this.ID = i;
    }

    public static void main(String[] args) {
        int port;
        int nbConnexions = 0;
        try {
            if(args.length!=1) {
                System.out.println("Veuillez entrer le numéro de port que vous souhaitez utiliser.");
            }else {
                port = Integer.parseInt(args[0]);
                ServerSocket serverSocket1 = new ServerSocket(port);
                System.out.println("Serveur en attente de connexion.");
                // boucle infinie permettant les connexions multiples
                while (true) {
					Socket s = serverSocket1.accept();
					if(bureau.getnbUtilisateurs() < Bureau.nbMaxUtilisateurs) {
						System.out.println("Connexion d'un utilisateur.");
						bureau.setnbUtilisateurs(bureau.getnbUtilisateurs() + 1);
						Runnable runnable = new ServeurConnexionsMultiples(s, ++nbConnexions);
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
            os = s.getOutputStream();
            Thread t3 = new Thread(new ReceptionServeur(bis, bureau));
            t3.start();
            Thread t2 = new Thread(new EmissionServeur(os, bureau));
            t2.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
