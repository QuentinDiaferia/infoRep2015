import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class EmissionServeur implements Runnable {

	private final List<Socket> listeSockets;
	private String message = null;
	private Scanner sc = null;
	public final Bureau bureau;

	public EmissionServeur(List<Socket> liste, Bureau b) {
		this.listeSockets = liste;
		this.bureau = b;
	}


	public void run() {
		try{
			if(listeSockets.size()>0){
				for(Socket s : listeSockets){
					OutputStream out = s.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(bureau);
					out.flush();
				}
			}
			System.out.println("Bureaux des clients à jour.");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
