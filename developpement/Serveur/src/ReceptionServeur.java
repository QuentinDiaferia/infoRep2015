import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class ReceptionServeur implements Runnable {

	private BufferedInputStream in = null;
	private final Bureau bureau;
	public final ArrayList<Socket> listeSockets;
	private Socket s;
	Object retour;
	ObjectInputStream ois;

	public ReceptionServeur(Socket s, Bureau bureau, ArrayList<Socket> listeSockets){
		this.s =s;
		this.bureau = bureau;
		this.listeSockets = listeSockets;
		try{
			this.in = new BufferedInputStream(s.getInputStream());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		try {
			while(true){
				ois = new ObjectInputStream(in);
				retour = ois.readObject();
				System.out.println("Mise à jour du bureau:");
				if(retour != null) {
					this.bureau.copy((Bureau)retour);
					System.out.println(bureau.toString());
					System.out.println("Envoi du bureau aux clients:");
                    // initialisation du thread de broadcast
					Thread broadcast = new Thread(new EmissionServeur(listeSockets,bureau));
					broadcast.start();
				} else {
					System.out.println("Dépassement du nombre d'utilisateurs autorisés.");
				}
			}
		} catch (IOException e1) {
			System.out.println("Connexion perdue");
			System.out.println("Nb restant : "+listeSockets.size());
			listeSockets.remove((Object)s);
			System.out.println("Nb restant : "+listeSockets.size());
		} catch (Exception e2){
			System.out.println(e2.getMessage());
		} finally {

		}
	}

}
