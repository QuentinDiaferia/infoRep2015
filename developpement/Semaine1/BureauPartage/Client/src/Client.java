import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class Client implements Runnable {

    public static Thread t1, t2, t3;
    private Socket s;
    OutputStream os;
    InputStream is;
    public static Bureau bureau ;
    public static BufferedInputStream bis;

    public Client(Socket s){
        this.s=s;
    }

    public static void main(String[] args) {
        Socket s;
        int port;
        InetAddress adresse;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        String reponse;
        Object retour;

        try {
            if(args.length!=2){
                System.out.println("2 arguments necessaires : IP Port");
            }else{
                port = Integer.parseInt(args[1]);
                adresse = InetAddress.getByName(args[0]);
                s = new Socket(adresse, port);
                bis = new BufferedInputStream(s.getInputStream());
                    t1 = new Thread(new Client(s));
                    t1.start();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {


            os = s.getOutputStream();
                    System.out.println("run");
            
            Thread t3 = new Thread(new Reception(bis, bureau));
            t3.start();
            Thread t2 = new Thread(new Emission(os, bureau));
            t2.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
