import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Emission implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public final Bureau bureau;
	
	public Emission(OutputStream out, Bureau b) {
		this.out = out;
		this.bureau = b;
	}

	public void run() {
		
		ObjectOutputStream oos;
		sc = new Scanner(System.in);	
        try {
			while(true){
				oos = new ObjectOutputStream(out);
			    System.out.println("=> Action: ajouter un widget (ou se déconnecter)? o/n ");
				if(sc.nextLine().equals("o")){
				    WidgetBlocNote wbn = new WidgetBlocNote(false, "1", 0, 0, 0);
				    this.bureau.ajouterWidget(wbn);
					oos.writeObject(this.bureau);
			    	out.flush();
				}else{
					oos.close();
				}
			}
		}
        catch (IOException e) {
            System.out.println("Attention ! : "+e.getMessage());
        }
	}
}
