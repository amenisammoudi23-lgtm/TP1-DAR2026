package clientPackage;

import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            System.out.println("Je suis un client pas encore connecté…");

            Socket socket = new Socket("127.0.0.1", 1234);

            System.out.println("Je suis un client connecté");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            int x;
            do {
                System.out.print("Entrez un entier (0 pour quitter) : ");
                x = scanner.nextInt();

                outputStream.writeInt(x);
                outputStream.flush();

                if (x == 0) {
                    System.out.println("Déconnexion demandée...");
                    break;
                }

                int resultat = inputStream.readInt();


                System.out.println("Résultat reçu du serveur (x * 5) = " + resultat);

            } while (x != 0);

            scanner.close();
            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Client déconnecté.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
