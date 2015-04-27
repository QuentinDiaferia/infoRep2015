import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class EmissionServeur implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public final Bureau bureau;

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
	        System.out.println("Premier envoi du bureau");
			while(true){
				oos = new ObjectOutputStream(out);
			    System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
				if(sc.nextLine().equals("o")){
				    WidgetBlocNote wbn = new WidgetBlocNote(false, "1", 0, 0, 0);
				    bureau.ajouterWidget(wbn);
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
