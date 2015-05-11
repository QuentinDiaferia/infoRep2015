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
				if(this.bureau.miseAJour()){
					oos = new ObjectOutputStream(this.client.getBos());
					System.out.println("maj bureau ");
					oos.writeObject(this.bureau);
				    this.client.getBos().flush();
				}else{
					// System.out.print(".");
					try{
					 	Thread.sleep(10);
					}
			        catch (Exception e) {
			            
			        }
				}
			}
		}
        catch (IOException e) {
            System.out.println("Attention ! : "+e.getMessage());
        }
	}
}
