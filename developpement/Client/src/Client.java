import java.io.*;
import java.util.*;
import java.net.*;
import bureau.*;

public class Client implements Runnable {

    private Socket s;
    private final BufferedInputStream bis;
    private final BufferedOutputStream bos;
    private final Bureau bureau ;

    public Client(Socket s, Bureau bureau, BufferedInputStream bis, BufferedOutputStream bos){
        this.s = s;
        this.bis = bis;
        this.bos = bos;
        this.bureau = bureau;
        this.bureau.affichageBureau();
        System.out.println(this.bureau.toString());
    }

    public static void main(String[] args) throws Exception {
        Socket s;
        int port;
        InetAddress adresse;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        OutputStream os;
        InputStream is;
        String reponse;
        Object retour;
        Thread t1;

        if(args.length!=2){
            System.out.println("2 arguments necessaires : IP Port");
        }else{
            port = Integer.parseInt(args[1]);
            adresse = InetAddress.getByName(args[0]);
            s = new Socket(adresse, port);
            // Réception du premier bureau pour initialisation
            bis = new BufferedInputStream(s.getInputStream());
            bos = new BufferedOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(bis);
            retour = ois.readObject();
            t1 = new Thread(new Client(s, (Bureau)retour, bis, bos));
            t1.start();
        }
    }

    public void run() {
        Thread t2, t3;

        try {
            t3 = new Thread(new Reception(this, this.bureau));
            t3.start();
            t2 = new Thread(new Emission(this, this.bureau));
            t2.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deconnecter(){
        try {
            this.bos.close();
            this.bis.close();
            this.s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getS(){
        return this.s;
    }

    public BufferedInputStream getBis(){
        return this.bis;
    }

    public BufferedOutputStream getBos(){
        return this.bos;
    }

    public Bureau getBureau(){
        return this.bureau;
    }
    
}
