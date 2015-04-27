import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class ReceptionServeur implements Runnable {

	private BufferedInputStream in = null;
	private final Bureau bureau;
    Object retour;
    ObjectInputStream ois;

	public ReceptionServeur(BufferedInputStream in, Bureau bureau){
		this.in = in;
		this.bureau = bureau;
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
				} else {
					System.out.println("Dépassement du nombre d'utilisateurs autorisés.");
				}
		    } catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
