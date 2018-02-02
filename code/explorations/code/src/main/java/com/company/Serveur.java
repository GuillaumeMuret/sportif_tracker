package com.company;

import java.io.*;
import java.net.*;
import java.util.UUID;

// Echange de message entre Client et Serveur
/*public class Serveur {

    public static void main(String[] zero) {

        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        try {

            socketserver = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            socketduserveur = socketserver.accept();
            System.out.println("Un zéro s'est connecté");
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Vous êtes connecté zéro !");
            out.flush();

            socketduserveur.close();
            socketserver.close();

        }catch (IOException e) {

            e.printStackTrace();
        }
    }

}*/

/*// Utilisation de threads pour gérer la connexion
public class Serveur {

    public static void main(String[] zero){

        ServerSocket socket;
        try {
            socket = new ServerSocket(2009);
            Thread t = new Thread(new Accepter_clients(socket));
            t.start();
            System.out.println("Mes employeurs sont prêts !");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

class Accepter_clients implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private int nbrclient = 1;
    public Accepter_clients(ServerSocket s){
        socketserver = s;
    }

    public void run() {

        try {
            while(true){
                socket = socketserver.accept(); // Un client se connecte on l'accepte
                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                nbrclient++;
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/

public class Serveur{
    public static void main(String[] args) {
        System.out.println("bite");
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
    }
}