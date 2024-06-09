import java.io.*;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            new Sender(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir le nom de l'expéditeur à partir des arguments de la ligne de commande.
    private static String getSenderName(String[] args) {
        StringBuilder var = new StringBuilder();
        for (String arg : args) {
            var.append(arg);
        }
        return var.toString();
    }

    // Constructeur de la classe Sender.
    public Sender(String[] args) throws Exception {
        System.out.println("Démarrage du programme Expediteur.");

        CarnetAdresses carnet = new CarnetAdresses();

        // Initialisation des lecteurs pour la saisie utilisateur.
        InputStreamReader inputReader = new InputStreamReader(System.in);
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String entry = bufferReader.readLine();
        boolean estMessageAEnvoyer = true;

        while (estMessageAEnvoyer) {
            int nbMessagesEnvoyes = 0;

            // Boucle pour envoyer le message à toutes les adresses IP dans le carnet d'adresses.
            for (int i = 0; i < carnet.getSize(); i++) {
                try {
                    // Établissement de la connexion avec le destinataire.
                    Socket socket = new Socket(carnet.getIp(i), 2023);
                    socket.setSoTimeout(15);

                    // Configuration du flux de sortie pour envoyer le message.
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter outputWriter = new OutputStreamWriter(os);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);

                    // Création du message avec le nom de l'expéditeur et le contenu du message.
                    Message msg = new Message(getSenderName(args), entry);

                    // Envoi du message.
                    bufferedWriter.write(msg.send());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    // Fermeture de la connexion.
                    socket.close();
                    nbMessagesEnvoyes++;
                } catch (Exception e) {
                    System.out.println("[IGNORÉ] Impossible de se connecter à " + carnet.getIp(i) + " - n'est pas connecté");
                }
            }

            // Affichage du nombre de messages envoyés.
            System.out.println("Message envoyé à " + nbMessagesEnvoyes + " sur " + carnet.getSize() + " adresses.");

            // Lecture du message suivant.
            entry = bufferReader.readLine();
            estMessageAEnvoyer = (entry != null && !entry.isEmpty());
        }
    }
}
