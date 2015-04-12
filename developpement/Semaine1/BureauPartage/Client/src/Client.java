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
    public static BufferedOutputStream bos;

    public Client(Socket s, Bureau b){
        this.s=s;
        bureau=b;
        System.out.println(bureau.toString());
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
                bos = new BufferedOutputStream(s.getOutputStream());
                ois = new ObjectInputStream(bis);
                retour = ois.readObject();
                t1 = new Thread(new Client(s, (Bureau)retour));
                t1.start();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            
            Thread t3 = new Thread(new Reception(bis, bureau));
            t3.start();
            Thread t2 = new Thread(new Emission(bos, bureau));
            t2.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
