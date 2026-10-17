package clientPackage;

import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            String ipServeur = "192.168.1.45";
            int port = 1234;

            System.out.println("Tentative de connexion au serveur sur " + ipServeur + ":" + port + "...");

            Socket socket = new Socket(ipServeur, port);
            System.out.println("Connexion réussie  Je suis connecté au serveur distant.");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            int choix;
            do {
                System.out.println("   MENU CALCULATRICE RESEAU   ");
                System.out.println("1. Addition (+)");
                System.out.println("2. Soustraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.println("0. Quitter");
                System.out.print("Faites votre choix (0-4) : ");
                choix = scanner.nextInt();

                outputStream.writeInt(choix);
                outputStream.flush();

                if (choix == 0) {
                    System.out.println("Fermeture de la connexion demandée...");
                    break;
                }

                if (choix >= 1 && choix <= 4) {
                    System.out.print("Entrez la première valeur x) : ");
                    double x = scanner.nextDouble();
                    System.out.print("Entrez la deuxième valeur y : ");
                    double y = scanner.nextDouble();

                    outputStream.writeDouble(x);
                    outputStream.writeDouble(y);
                    outputStream.flush();

                    boolean erreur = inputStream.readBoolean();
                    if (erreur) {
                        String messageErreur = inputStream.readUTF();
                        System.out.println(" Erreur  : " + messageErreur);
                    } else {
                        double resultat = inputStream.readDouble();
                        System.out.println(" Résultat  = " + resultat);
                    }
                } else {
                    System.out.println(" Choix invalide. Veuillez choisir une option entre 0 et 4.");
                }

            } while (choix != 0);

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
