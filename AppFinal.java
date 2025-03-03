import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppFinal {
    
    static List<Utente> registroUtenti = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
    }

   
}

// Classe utente per gestire nome e password
class Utente {
    String nome;
    String password;
    int punti;
    int livello;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
        this.punti = 0;
        this.livello = 1;
    }
}