import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// La classe CarnetAdresses représente un carnet d'adresses avec des adresses IP.
public class CarnetAdresses {
    private ArrayList<String> ipList; // Liste des adresses IP stockées dans le carnet d'adresses.

    // Constructeur de la classe CarnetAdresses qui initialise le carnet d'adresses à partir d'un fichier.
    public CarnetAdresses() throws IOException {
        ArrayList<String> ipList = new ArrayList<String>(); // Initialisation de la liste des adresses IP.

        FileInputStream listFis = new FileInputStream("IP.txt");
        InputStreamReader VarBrIp = new InputStreamReader(listFis);
        BufferedReader ListeIP = new BufferedReader(VarBrIp); // Création d'un BufferedReader pour faciliter la lecture.

        String entry = ListeIP.readLine();
        while (entry != null) {
            ipList.add(entry); // Ajout de l'adresse IP à la liste.
            entry = ListeIP.readLine();
        }

        ListeIP.close();
        this.ipList = ipList; // Attribution de la liste d'adresses IP à l'attribut de la classe.
    }
    
    // Méthode getIp() retourne l'adresse IP à l'index spécifié.
    public String getIp(int i) {
        return this.ipList.get(i);
    }

    // Méthode getSize() retourne le nombre total d'adresses IP dans le carnet d'adresses.
    public int getSize() {
        return ipList.size();
    }
}
