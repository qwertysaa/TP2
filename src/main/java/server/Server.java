package server;

import javafx.util.Pair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe représentant le modèle d'un serveur qui gère les inscriptions
 */
public class Server {

    /**
     * Commande de la ligne de commande correspondant à la commande pour l'inscription
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";

    /**
     * Commande de la ligne de commande correspondant à la commande pour obtenir la liste de cours pour une session
     */
    public final static String LOAD_COMMAND = "CHARGER";
    private final ServerSocket server;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ArrayList<EventHandler> handlers;

    /**
    * Crée un serveur qui écoute sur le port spécifié et qui n'accepte qu'un client.
    */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    /**
     * Ajoute un handler au serveur (à la liste de handlers).
     *
     * @param h handler
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     * Établit la connexion avec le client
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Passe les lignes de commande à tous les handlers du serveur.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    /**
     * Analyse la ligne de commande et la retourne séparée en commande et en arguments.
     *
     * @param line  la ligne de commande
     * @return      paire avec la commande et les arguments séparés
     */
    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    /**
     * Déconnecte le client du serveur.
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    /**
     * Gère les commandes pour regarder la liste de cours pour une session et pour l'inscription.
     *
     * @param cmd   commande
     * @param arg   arguments
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transformer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     La méthode gère les exceptions si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux.
     @param arg la session pour laquelle on veut récupérer la liste des cours
     */
    public void handleLoadCourses(String arg) {
        try{
            FileReader fr = new FileReader("src/main/java/server/data/cours.txt");
            BufferedReader reader = new BufferedReader(fr);

            //Créer liste d'objets "course" à partir du fichier cours.txt en ajoutant des lists de String contenant les données du cours
            ArrayList<Object> course = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                course.add(parts);
            }

            //Créer liste d'objets des cours à envoyer
            ArrayList<Object> coursSessionSpecifiee = new ArrayList<>();
            for (Object o : course) {
                String[] cours = (String[]) o;
                if (cours[2].equals(arg)){
                    coursSessionSpecifiee.add(o);
                    System.out.println(Arrays.asList((String[])o)); //pour débogage
                }
            }

            //Renvoyer la liste des cours pour une session au client avec objectOutputStream ***à réviser
            FileOutputStream fileOs = new FileOutputStream("listeCoursSessionSpecifiee.txt");
            ObjectOutputStream os = new ObjectOutputStream(fileOs);

            os.writeObject(coursSessionSpecifiee); //ajouter objet dans fichier "sérialisé" pour le client
            System.out.println(Arrays.asList(coursSessionSpecifiee)); //pour débogage

        } catch (IOException ex){
            System.out.println("Erreur à l'ouverture du fichier");
        }
    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client.
     La méthode gére les exceptions si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() {
        try {
            //Disons que le contenu du fichier RegistrationForm a été converti en ce tableau:
            String[] inscription = {"Hiver", "IFT2015", "12345678", "Leblanc", "Mathieu", "mathieu@umontreal.ca"};
            FileWriter fw = new FileWriter("src/main/java/server/data/inscription.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);

            String line = "\n";
            for (int i = 0; i < inscription.length; i++) {
                line += inscription[i];
                if (i < (inscription.length - 1)) {
                    line += "\t";
                }
            }
            System.out.println(line); //pour déboguage
            writer.append(line);
            writer.close();
            } catch (IOException e) {

        }
    }
}

