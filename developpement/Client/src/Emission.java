import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Emission implements Runnable {

	public final Bureau bureau;
	public final Client client;

	public Emission(Client client, Bureau b) {
		this.bureau = b;
		this.client = client;
	}

	public void run() {
		String message = null;
		Scanner sc = null;
		ObjectOutputStream oos;
		sc = new Scanner(System.in);
        try {
			while(true){
				oos = new ObjectOutputStream(this.client.getBos());
			    System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
				if(sc.nextLine().equals("o")){
				    WidgetMeteo wbn = new WidgetMeteo(true, "Meteo");
				    this.bureau.ajouterWidget(wbn);
					oos.writeObject(this.client.getBureau());
			    	this.client.getBos().flush();
				}else{
					this.client.deconnecter();
				}
			}
		}
        catch (IOException e) {
            System.out.println("Attention ! : "+e.getMessage());
        }
	}
}
