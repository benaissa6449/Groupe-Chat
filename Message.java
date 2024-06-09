import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// La classe Message représente un message avec des informations associées.
public class Message {
    private String nom;             
    private String dateFormatted;   
    private String taille;          
    private String message;   
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // Constructeur de la classe Message pour créer un nouveau message.
    public Message(String nom, String message) {
        LocalDateTime localDT = LocalDateTime.now();
        this.nom = nom;
        this.dateFormatted = localDT.format(dateFormat); 
        this.taille = String.valueOf(message.length());   // Calcul de la taille du message.
        this.message = message;                          
    }

    // Méthode send() génère une chaîne de caractères formatée pour l'envoi du message.
    public String send() {
        return String.format("%s%n%s%n%s%n%s", this.nom, this.dateFormatted, this.taille, this.message);
    }

    // Méthode toString() génère une représentation en chaîne de caractères de l'objet Message.
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.nom, this.dateFormatted, this.taille, this.message);
    }
}
