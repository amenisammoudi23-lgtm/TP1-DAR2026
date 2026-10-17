package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        try {

            InetAddress adresseServeur = InetAddress.getLocalHost();
            int port = 1234;

            InetSocketAddress socketAddress = new InetSocketAddress(adresseServeur, port);

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(socketAddress);

            System.out.println("Serveur démarré sur l'adresse : " + serverSocket.getLocalSocketAddress());
            System.out.println("En attente de la connexion d'un client...");

            Socket socket = serverSocket.accept();
            System.out.println("Un client s'est connecté depuis l'adresse : " + socket.getRemoteSocketAddress());

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            boolean continuer = true;
            while (continuer) {

                int choix = inputStream.readInt();

                if (choix == 0) {
                    System.out.println("Le client a demandé l'arrêt de la communication.");
                    continuer = false;
                    break;
                }

                double x = inputStream.readDouble();
                double y = inputStream.readDouble();
                double resultat = 0;
                boolean erreur = false;
                String messageErreur = "";

                switch (choix) {
                    case 1:
                        resultat = x + y;
                        System.out.println("Calcul effectué : " + x + " + " + y + " = " + resultat);
                        break;
                    case 2:
                        resultat = x - y;
                        System.out.println("Calcul effectué : " + x + " - " + y + " = " + resultat);
                        break;
                    case 3:
                        resultat = x * y;
                        System.out.println("Calcul effectué : " + x + " * " + y + " = " + resultat);
                        break;
                    case 4:
                        if (y == 0) {
                            erreur = true;
                            messageErreur = "Division par zéro impossible !";
                            System.out.println("Erreur : Tentative de division par zéro.");
                        } else {
                            resultat = x / y;
                            System.out.println("Calcul effectué : " + x + " / " + y + " = " + resultat);
                        }
                        break;
                    default:
                        erreur = true;
                        messageErreur = "Opération inconnue.";
                        break;
                }
                outputStream.writeBoolean(erreur);
                if (erreur) {
                    outputStream.writeUTF(messageErreur);
                } else {
                    outputStream.writeDouble(resultat);
                }
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