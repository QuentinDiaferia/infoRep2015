import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class ReceptionServeur implements Runnable {

	private BufferedInputStream in = null;
	private final Bureau bureau;
    public final HashMap<Integer,Socket> listeSockets;
	private Socket s;
    Object retour;
    ObjectInputStream ois;

	public ReceptionServeur(Socket s, Bureau bureau, HashMap<Integer,Socket> socket){
		this.s =s;
		this.bureau = bureau;
        this.listeSockets = socket;
		try{
			this.in = new BufferedInputStream(s.getInputStream());
		}catch(Exception e) {
            System.out.println(e.getMessage());
		}
	}

	public void run() {
		while(true){
	        try {
				ois = new ObjectInputStream(in);
				retour = ois.readObject();
				System.out.println("Mise à jour du bureau:");
				if(retour != null) {
					this.bureau.copy((Bureau)retour);
					System.out.println(bureau.toString());
					System.out.println("Envoie du bureau aux clients:");
                    // initialisation du thread de broadcast
                    Thread broadcast = new Thread(new EmissionServeur(listeSockets,bureau));
                    broadcast.start();
				} else {
					System.out.println("Dépassement du nombre d'utilisateurs autorisés.");
				}
		    } catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
