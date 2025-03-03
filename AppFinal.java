import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class AppFinal {
    static List<Utente> registroUtenti = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu.menuPrincipale(scanner);
    }
}

// Classe Utente
class Utente {
    String nome;
    String password;
    int punti;
    int livello;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
        this.punti = 100;
        this.livello = 1;
    }

    //#region metodi gestione utente
    // Metodo per la registrazione di un utente
    public static void registrazione(Scanner scanner, List<Utente> registroUtenti) {
        System.out.print("Inserisci il nome utente: ");
        String nome = Controlli.controlloInputStringhe(scanner);

        // Controlla se il nome utente è già registrato
        if (trovaUtente(nome, registroUtenti) != null) {
            System.out.println("Errore: Il nome utente è già in uso!");
            return;
        }

        System.out.print("Inserisci la password: ");
        String password = Controlli.controlloInputStringhe(scanner);

        // Crea e aggiunge un nuovo utente
        Utente nuovoUtente = new Utente(nome, password);
        registroUtenti.add(nuovoUtente);
        System.out.println("Registrazione completata con successo!");
    }

    // Metodo per il login
    public static Utente login(Scanner scanner, List<Utente> registroUtenti) {

        if (registroUtenti == null || registroUtenti.isEmpty()) {
            System.out.println("Errore: Nessun utente registrato! Registrati prima di accedere.");
            return null;
        }

        System.out.print("Inserisci il nome utente: ");
        String nome = Controlli.controlloInputStringhe(scanner);
        System.out.print("Inserisci la password: ");
        String password = Controlli.controlloInputStringhe(scanner);

        // Trova l'utente nel registro
        Utente utente = trovaUtente(nome, registroUtenti);
        if (utente != null && utente.password.equals(password)) {
            System.out.println("Login effettuato con successo!");
            return utente;
        } else {
            System.out.println("Errore: Nome utente o password errati.");
            return null;
        }
    }

    // Metodo per stampare tutti gli utenti
    public static void stampaUtenti(List<Utente> registroUtenti) {
        if (registroUtenti.isEmpty()) {
            System.out.println("Nessun utente registrato.");
        } else {
            System.out.println("Elenco utenti registrati:");
            for (Utente utente : registroUtenti) {
                System.out.println(
                        "Nome: " + utente.nome + " | Punti: " + utente.punti + " | Livello: " + utente.livello);
            }
        }
    }

    // Metodo privato per trovare un utente
    private static Utente trovaUtente(String nome, List<Utente> registroUtenti) {
        for (Utente utente : registroUtenti) {
            if (utente.nome.toLowerCase().trim().equals(nome.toLowerCase().trim())) {
                return utente;
            }
        }
        return null;
    }

    // Metodo per cambiare il nome utente
    public static void cambiaUsername(Utente utente, Scanner scanner) {
        System.out.print("Inserisci il nuovo nome utente: ");
        String nuovoNome = Controlli.controlloInputStringhe(scanner);

        // Verifica se il nome utente è già in uso
        if (trovaUtente(nuovoNome, AppFinal.registroUtenti) != null) {
            System.out.println("Errore: Il nome utente è già stato preso. Scegli un altro nome.");
            return;
        }

        // Conferma il cambiamento
        System.out.println("Confermi il cambio nome utente? (s/n): ");
        String conferma = Controlli.controlloInputStringhe(scanner).trim().toLowerCase();
        if (conferma.equals("s")) {
            utente.nome = nuovoNome;
            System.out.println("Nome utente cambiato con successo!");
        } else {
            System.out.println("Operazione annullata.");
        }
    }

    // Metodo per cambiare la password
    public static void cambiaPassword(Utente utente, Scanner scanner) {
        System.out.print("Inserisci la password attuale: ");
        String passwordAttuale = Controlli.controlloInputStringhe(scanner);

        // Controllo se la password inserita è corretta
        if (!utente.password.equals(passwordAttuale)) {
            System.out.println("Errore: La password attuale non è corretta!");
            return;
        }

        System.out.print("Inserisci la nuova password: ");
        String nuovaPassword = Controlli.controlloInputStringhe(scanner);

        // Conferma la modifica
        System.out.println("Confermi la modifica della password? (s/n): ");
        String conferma = Controlli.controlloInputStringhe(scanner).trim().toLowerCase();
        if (conferma.equals("s")) {
            utente.password = nuovaPassword;
            System.out.println("Password cambiata con successo!");
        } else {
            System.out.println("Operazione annullata.");
        }
    }

    // Metodo per iniziare il gioco matematico
    public static void iniziaGioco(Utente utente, Scanner scanner) {
        Random rand = new Random();
        int livello = 1;
        int domandePerLivello = 1;
        int punteggioPerDomanda = 50;

        System.out.println("\n=== Inizio Test Matematico ===");
        System.out.println("Ogni risposta corretta ti darà " + punteggioPerDomanda + " punti.");
        System.out.println("Ogni risposta sbagliata ti toglierà " + punteggioPerDomanda + " punti.");
        System.out.println("Per avanzare al livello successivo, devi guadagnare almeno 50 punti.\n");

        while (livello <= 3 && utente.punti > 0) {
            System.out.println("Livello " + livello + " - Rispondi a " + domandePerLivello + " domande!");

            for (int i = 0; i < domandePerLivello; i++) {
                int num1 = rand.nextInt(10) + 1;
                int num2 = rand.nextInt(10) + 1;
                int rispostaCorretta = 0;
                String domanda = "";

                switch (livello) {
                    case 1: // Somma
                        domanda = "Quanto fa " + num1 + " + " + num2 + "? ";
                        rispostaCorretta = num1 + num2;
                        break;
                    case 2: // Moltiplicazione
                        domanda = "Quanto fa " + num1 + " * " + num2 + "? ";
                        rispostaCorretta = num1 * num2;
                        break;
                    case 3: // Divisione
                        num1 = num2 * (rand.nextInt(9) + 1); // Assicura una divisione intera
                        domanda = "Quanto fa " + num1 + " / " + num2 + "? ";
                        rispostaCorretta = num1 / num2;
                        break;
                }

                System.out.print(domanda);
                int rispostaUtente = Controlli.controlloInputInteri(scanner);

                if (rispostaUtente == rispostaCorretta) {
                    System.out.println("Risposta corretta! +10 punti");
                    utente.punti += punteggioPerDomanda;
                } else {
                    System.out.println("Risposta sbagliata! La risposta corretta era: " + rispostaCorretta);
                    utente.punti -= punteggioPerDomanda;

                    if (utente.punti <= 0) {
                        System.out.println("\nHai perso tutti i punti! Game over. 😢");
                        return;
                    }
                }

                System.out.println("Punti attuali: " + utente.punti + "\n");
            }

            if (utente.punti >= 50) {
                livello++;
                System.out.println("Complimenti! Sei passato al livello " + livello + "!\n");
            }
        }

        if (utente.punti > 0) {
            System.out.println("Hai completato il test matematico con " + utente.punti + " punti!");
        }
    }
    //#endregion
}

//#region Menus
// Classe Menu
class Menu {
    // menu principale
    public static void menuPrincipale(Scanner scanner) {
        int scelta;
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            System.out.println("\n==== Menu Principale ====");
            System.out.println("1. Login");
            System.out.println("2. Registrazione");
            System.out.println("3. Stampa tutti gli utenti");
            System.out.println("4. Esci");

            System.out.print("Scegli un'opzione (1-4): ");
            scelta = Controlli.controlloInputInteri(scanner);
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    Utente utenteLoggato = Utente.login(scanner, AppFinal.registroUtenti);
                    if (utenteLoggato != null) {
                        menuSecondario(scanner, utenteLoggato);
                    }
                    break;
                case 2:
                    Utente.registrazione(scanner, AppFinal.registroUtenti);
                    break;
                case 3:
                    Utente.stampaUtenti(AppFinal.registroUtenti);
                    break;
                case 4:
                    System.out.println("Uscita dal programma.");
                    exitMainMenu = true;
                    break;
                default:
                    System.out.println("Opzione non valida! Riprova.");
            }
        }
    }

    // menu secondario
    public static void menuSecondario(Scanner scanner, Utente utente) {
        int scelta;
        boolean exitSecondaryMenu = false;

        while (!exitSecondaryMenu) {
            System.out.println("\n==== Menu Utente ====");
            System.out.println("1. Modifica Username");
            System.out.println("2. Modifica password");
            System.out.println("3. Gioca");
            System.out.println("4. Logout");

            System.out.print("Scegli un'opzione (1-3): ");
            scelta = Controlli.controlloInputInteri(scanner);
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    Utente.cambiaUsername(utente, scanner);
                    break;
                case 2:
                    Utente.cambiaPassword(utente, scanner);
                    break;
                case 3:
                    Utente.iniziaGioco(utente, scanner);
                    break;
                case 4:
                    System.out.println("Logout effettuato con successo.");
                    exitSecondaryMenu = true;
                    break;
                default:
                    System.out.println("Opzione non valida! Riprova.");
            }
        }
    }
}
//#endregion

//#region controlli input
// Classe Controlli
class Controlli {
    // Metodo per controllare che l'input stringa non sia vuoto
    public static String controlloInputStringhe(Scanner scanner) {
        String valore;
        do {
            valore = scanner.nextLine().trim();
            if (valore.isEmpty()) {
                System.out.print("Input non valido. Inserisci un testo: ");
            }
        } while (valore.isEmpty());
        return valore;
    }

    // Metodo per controllare l'input intero
    public static Integer controlloInputInteri(Scanner scanner) {
        Integer valore;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Devi inserire un numero intero. Riprova ");
                scanner.next();
            }
            valore = scanner.nextInt();
            if (valore < 0) {
                System.out.print("Il numero non può essere negativo. Riprova: ");
            }
        } while (valore < 0);
        return valore;
    }
}

//#endregion