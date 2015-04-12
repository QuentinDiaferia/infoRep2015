import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class EmissionServeur implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public static Bureau bureau = null;
	
	public EmissionServeur(OutputStream out, Bureau b) {
		this.out = out;
		bureau = b;
	}

	
	public void run() {
		
		ObjectOutputStream oos;
		sc = new Scanner(System.in);
        try {
		oos = new ObjectOutputStream(out);	
		oos.writeObject(bureau);	  
        System.out.println("Envoie premier bureau");
			while(true){
				oos = new ObjectOutputStream(out);
				    // System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
					if(sc.nextLine().equals("o")){
					bureau.setnbUtilisateurs(bureau.getnbUtilisateurs()+1);
					oos.writeObject(bureau);
				}else{
					oos.close();
				}
			}
		}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
