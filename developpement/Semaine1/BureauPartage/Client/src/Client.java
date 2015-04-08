import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class Client {
    public static void main(String[] args) {
        Socket s;
        int port;
        InetAddress adresse;
        OutputStream os;
        ObjectOutputStream oos;
        String reponse;
        Bureau bureau = new Bureau();

        try {
            if(args.length!=2){
                System.out.println("2 arguments necessaires : IP Port");
            }else{
                port = Integer.parseInt(args[1]);
                adresse = InetAddress.getByName(args[0]);
                s = new Socket(adresse, port);
                os = s.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(bureau);
                oos.close();
                os.close();
                s.close();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
