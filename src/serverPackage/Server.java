package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {

            System.out.println("Je suis un serveur en attente de la connexion d'un client...");
            ServerSocket serverSocket = new ServerSocket(1234);

            Socket socket = serverSocket.accept();

            System.out.println("Un client est connecté");
            socket.close();
            serverSocket.close();
            System.out.println("Serveur déconnecté et fermé.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}