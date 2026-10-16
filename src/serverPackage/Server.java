package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    public static void main(String[] args) {
        try {

            System.out.println("Je suis un serveur en attente de la connexion d'un client...");
            ServerSocket serverSocket = new ServerSocket(1234);

            Socket socket = serverSocket.accept();

            System.out.println("Un client est connecté");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            boolean continuer = true;
            while (continuer) {
                int x = inputStream.readInt();
                System.out.println("Nombre reçu du client : " + x);

                if (x == 0) {
                    System.out.println("Le client a demandé l'arrêt de la communication.");
                    continuer = false;
                    break;
                }

                int resultat = x * 5;
                System.out.println("Calcul effectué : " + x + " * 5 = " + resultat);

                outputStream.writeInt(resultat);
                outputStream.flush();
            }


            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
            System.out.println("Serveur déconnecté et fermé.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}