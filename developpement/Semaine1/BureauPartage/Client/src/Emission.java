import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Emission implements Runnable {

	private OutputStream out;
	private String message = null;
	private Scanner sc = null;
	public static Bureau bureau = null;
	
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
