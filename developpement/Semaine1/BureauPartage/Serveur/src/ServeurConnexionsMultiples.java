import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class ServeurConnexionsMultiples implements Runnable {
    private Socket s;
    private int ID;

    public ServeurConnexionsMultiples(Socket socket, int i) {
        this.s = socket;
        this.ID = i;
    }

    public static void main(String[] args) {
        int port;
        int nbConnexions = 0;
        try {
            if(args.length!=1) {
                System.out.println("Veuillez entrer le numéro de port que vous souhaitez utiliser.");
            }else {
                port = Integer.parseInt(args[0]);
                ServerSocket serverSocket1 = new ServerSocket(port);
                System.out.println("Serveur en attente de connexion.");
                // boucle infinie permettant les connexions multiples
                while (true) {
                    Socket s = serverSocket1.accept();
                    Runnable runnable = new ServeurConnexionsMultiples(s, ++nbConnexions);
                    Thread thread = new Thread(runnable);
                    thread.start();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Bureau bureau = (Bureau)ois.readObject();
            if (bureau!=null) {
                System.out.println("L'objet envoyé n'a pas été reçu.");
            }
            System.out.println((String)ois.readObject());
            bis.close();
            s.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                s.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
