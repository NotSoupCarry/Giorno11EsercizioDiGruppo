import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AppFinal {

    static List<Utente> registroUtenti = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Benvenuto! Cosa vuoi fare?");
            System.out.println("1. Registrati");
            System.out.println("2. Accedi");
            System.out.println("3. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuming the leftover newline

            if (scelta == 1) {
                registrati();
            } else if (scelta == 2) {
                accedi();
            } else if (scelta == 3) {
                System.out.println("Arrivederci!");
                break;
            } else {
                System.out.println("Scelta non valida.");
            }
        }
    }

    // Metodo per registrare un nuovo utente
    public static void registrati() {
        System.out.println("Inserisci il tuo nome:");
        String nome = scanner.nextLine();
        System.out.println("Inserisci la tua password:");
        String password = scanner.nextLine();

        // Verifica se l'utente esiste già
        for (Utente utente : registroUtenti) {
            if (utente.nome.equals(nome)) {
                System.out.println("Nome utente già registrato.");
                return;
            }
        }

        Utente nuovoUtente = new Utente(nome, password);
        registroUtenti.add(nuovoUtente);
        System.out.println("Registrazione avvenuta con successo!");
    }

    // Metodo per accedere con un utente esistente
    public static void accedi() {
        System.out.println("Inserisci il tuo nome:");
        String nome = scanner.nextLine();
        System.out.println("Inserisci la tua password:");
        String password = scanner.nextLine();

        Utente utenteTrovato = null;

        // Ricerca utente
        for (Utente utente : registroUtenti) {
            if (utente.nome.equals(nome) && utente.password.equals(password)) {
                utenteTrovato = utente;
                break;
            }
        }

        if (utenteTrovato != null) {
            System.out.println("Accesso avvenuto con successo!");
            mostraMenu(utenteTrovato);
        } else {
            System.out.println("Nome utente o password errati.");
        }
    }

    // Metodo per gestire il menu principale dell'utente una volta loggato
    public static void mostraMenu(Utente utente) {
        while (true) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Mostra punti e livello");
            System.out.println("2. Inizia il test matematico");
            System.out.println("3. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuming the leftover newline

            if (scelta == 1) {
                System.out.println("Punti: " + utente.punti + ", Livello: " + utente.livello);
            } else if (scelta == 2) {
                iniziaTest(utente);
            } else if (scelta == 3) {
                System.out.println("Uscita...");
                break;
            } else {
                System.out.println("Scelta non valida.");
            }
        }
    }

    // Metodo per iniziare il test matematico
    public static void iniziaTest(Utente utente) {
        int difficolta = 1; // Inizia con la difficoltà della somma
        Random rand = new Random();

        while (difficolta <= 3 && utente.punti > 0) {
            System.out.println("Livello " + difficolta);
            boolean testCompletato = false;
            int punteggioPerDomanda = 10;

            for (int i = 0; i < 5; i++) {
                int num1 = rand.nextInt(10) + 1;
                int num2 = rand.nextInt(10) + 1;
                int rispostaCorretta = 0;
                String domanda = "";

                switch (difficolta) {
                    case 1: // Somma
                        domanda = "Quanto fa " + num1 + " + " + num2 + "? ";
                        rispostaCorretta = num1 + num2;
                        break;
                    case 2: // Moltiplicazione
                        domanda = "Quanto fa " + num1 + " * " + num2 + "? ";
                        rispostaCorretta = num1 * num2;
                        break;
                    case 3: // Divisione
                        num1 = num2 * (rand.nextInt(9) + 1); // Assicura che la divisione sia sempre intera
                        domanda = "Quanto fa " + num1 + " / " + num2 + "? ";
                        rispostaCorretta = num1 / num2;
                        break;
                }

                System.out.println(domanda);
                int rispostaUtente = scanner.nextInt();

                if (rispostaUtente == rispostaCorretta) {
                    System.out.println("Risposta corretta!");
                    utente.punti += punteggioPerDomanda; // Guadagna punti
                } else {
                    System.out.println("Risposta sbagliata!");
                    utente.punti -= punteggioPerDomanda; // Perde punti
                    if (utente.punti <= 0) {
                        System.out.println("Punteggio troppo basso. Sei stato rimosso dal gioco.");
                        return; // Rimuove l'utente dal gioco
                    }
                }
            }

            if (utente.punti >= 50) { // Aumenta di livello se ha abbastanza punti
                difficolta++;
                System.out.println("Congratulazioni! Passi al livello successivo.");
            }
        }

        if (utente.punti > 0) {
            System.out.println("Hai completato il test!");
        }
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
        this.punti = 100; // Inizia con 100 punti
        this.livello = 1;
    }
}
