import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Receiver {
    // Indique si le serveur est en cours d'exécution
    private static Boolean running = true; 
    private static ServerSocket server;

    // Méthode permettant de modifier l'état d'exécution du serveur
    public static void setRunning(Boolean status) {
        Receiver.running = status;
    }

    public static void main(String[] args) {
        try {
            run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode principale responsable de l'exécution du serveur
    public static void run(String[] args) throws Exception {
        System.out.println("[Serveur] Démarrage de la réception");

        Receiver.server = new ServerSocket(2023); // Crée un serveur socket sur le port 2023

        System.out.println("[Serveur] Le serveur de réception est ouvert : " + server.toString());

        while (running) {
            try {
                Socket socket = server.accept(); // Accepte la connexion d'un client
                System.out.println("Connexion reçue " + socket.toString());

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                ArrayList<String> entry = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    entry.add(line);
                    System.out.println("Ligne reçue : " + line);
                }

                System.out.println("Fin de la réception. " + entry.size() + " message(s) reçu(s).");

                if (entry.size() != 4) {
                    writer.write("Erreur de réception : Nombre incorrect d'arguments");
                } else {
                    System.out.println("Décryptement en cours...");
                    String result = decode(entry);
                    writer.write(result);
                    writer.newLine();
                    writer.flush();

                    try (FileOutputStream fosLog = new FileOutputStream("messages.log", true);
                            OutputStreamWriter oswLog = new OutputStreamWriter(fosLog);
                            BufferedWriter bufLog = new BufferedWriter(oswLog)) {
                        bufLog.write(result);
                        bufLog.newLine();
                        bufLog.flush();
                    }

                    System.out.println(result);
                }

                writer.close();
                output.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("[Exception du Récepteur attrapée] : " + e);
            }
        }

        System.out.println("[Serveur] Utilisateur déconnecté, fermeture du serveur.");
    }

    // Méthode pour décrypter les données reçues
    private static String decode(ArrayList<String> entry) {
        return "Message décrypter : " + entry.toString();
    }
}
