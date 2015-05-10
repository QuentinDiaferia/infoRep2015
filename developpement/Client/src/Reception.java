import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Reception implements Runnable {

	public final Bureau bureau;
	public final Client client;

	public Reception(Client client, Bureau bureau){

		this.client = client ;
		this.bureau = bureau ;

	}

	public void run() {

		ObjectInputStream ois;
		Object retour;

		try {
			while(true){
				ois = new ObjectInputStream(this.client.getBis());
				retour = ois.readObject();
				if(retour != null) {
					System.out.println("Mise à jour du bureau:");
					this.bureau.copy((Bureau)retour);
					System.out.println(bureau.toString());
				} else {
					System.out.println("Dépassement du nombre d'utilisateurs autorisés.");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
