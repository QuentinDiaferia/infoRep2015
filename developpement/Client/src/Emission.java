import bureau.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class Emission implements Runnable {

	private OutputStream output;
	private String message = null;
	private Scanner sc = null;
	public final Bureau bureau;

	public Emission(OutputStream output, Bureau b) {
		this.output = output;
		this.bureau = b;
	}

	public void run() {

		ObjectOutputStream oos;
		sc = new Scanner(System.in);
        try {
			while(true){
				if(this.bureau.maj==true){
				    this.bureau.maj=false;
					oos = new ObjectOutputStream(output);
				 	System.out.println("maj bureau ");
					oos.writeObject(this.bureau);
				    output.flush();
				}else{
				 	System.out.print(".");
				}
			}
		}
        catch (IOException e) {
            System.out.println("Attention ! : "+e.getMessage());
        }
	}
}
