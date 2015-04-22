import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class EmissionServeur implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public  Bureau bureau = null;
	
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
			out.flush();
	        System.out.println("Envoie premier bureau");
			bureau.setNbWidgets(bureau.getNbWidgets()+1);
			oos = new ObjectOutputStream(out);
			oos.writeObject(bureau);
			out.flush();	  
	        System.out.println("Envoie deuxieme premier bureau");
			while(true){
				oos = new ObjectOutputStream(out);
			    // System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
				if(sc.nextLine().equals("o")){
					bureau.setNbWidgets(bureau.getNbWidgets()+1);
					oos.writeObject(bureau);
				    out.flush();
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
