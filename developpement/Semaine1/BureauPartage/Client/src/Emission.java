import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Emission implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public  Bureau bureau = null;
	
	public Emission(OutputStream out, Bureau b) {
		this.out = out;
		bureau = b;
	}

	
	public void run() {
		
		ObjectOutputStream oos;
		sc = new Scanner(System.in);	
        try {
			// oos = new ObjectOutputStream(out);
			// bureau.setnbWidgets(bureau.getNbWidgets()+1);
			// oos.writeObject(bureau);	  
	        //System.out.println("Envoie test");
			// out.flush();
			while(true){
				oos = new ObjectOutputStream(out);
			    System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
				if(sc.nextLine().equals("o")){
				    //WidgetBlocNote wbn = new WidgetBlocNote(false, "1", 0, 0, 0);
				    //bureau.ajouterWidget(wbn);
					bureau.setNbWidgets(bureau.getNbWidgets()+1);
					oos.writeObject(bureau);
			    	out.flush();
				}else{
					oos.close();
				}
			}
		}
        catch (Exception e) {
            System.out.println("Attention ! : "+e.getMessage());
        }
	}
}
