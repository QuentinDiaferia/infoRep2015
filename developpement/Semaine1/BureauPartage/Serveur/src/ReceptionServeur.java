import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class ReceptionServeur implements Runnable {

	private BufferedInputStream in = null;
	private static Bureau bureau;
    Object retour;
    ObjectInputStream ois;

	public ReceptionServeur(BufferedInputStream in, Bureau b){
		
		this.in = in;
		this.bureau = b;
	}
	
	public void run() {
		
	        try {
				ois = new ObjectInputStream(in);
				retour = ois.readObject();
				System.out.println("Mise à jour du bureau:");
				if(retour != null) {
					bureau = (Bureau)retour;
					System.out.println(bureau.toString());
				} else {
					System.out.println("Dépassement du nombre d'utilisateurs autorisés.");
				}
		    } catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

}
