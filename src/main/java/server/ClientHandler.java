package server;

import javafx.util.Pair;
import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.lang.ref.Cleaner;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientHandler implements Runnable {
    private Socket client;
    private final ArrayList<EventHandler> handlers;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    public final static String REGISTER_COMMAND = "INSCRIRE";
    public final static String LOAD_COMMAND = "CHARGER";

    public ClientHandler(Socket socket) {
        this.client = socket;
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connecté au client: " + client);
            this.objectInputStream = new ObjectInputStream(client.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            System.out.println("bip");
            listen();
            disconnect();
            System.out.println("Client déconnecté!");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
     * @param line la ligne de commande
     * @return paire avec la commande et les arguments séparés
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
     * @param cmd commande
     * @param arg arguments
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     * Lire un fichier texte contenant des informations sur les cours et les transformer en liste d'objets 'Course'.
     * La méthode filtre les cours par la session spécifiée en argument.
     * Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     * La méthode gère les exceptions si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet
     * dans le flux.
     *
     * @param arg la session pour laquelle on veut récupérer la liste des cours
     */
    public void handleLoadCourses(String arg) {
        try {
            // Créer liste d'objets Course à partir du fichier cours.txt
            FileReader fr = new FileReader("src/main/java/server/data/cours.txt");
            BufferedReader reader = new BufferedReader(fr);
            ArrayList<Course> courses = new ArrayList<>();
            String line; //ligne dans le fichier
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                //Créer nouvel objet Course à ajouter
                Course cours = new Course(parts[1], parts[0], parts[2]);
                courses.add(cours);
            }

            // Créer la liste des cours pour la session spécifiée
            ArrayList<Course> coursSessionSpecifiee = new ArrayList<>();
            for (Course cours : courses) {
                if ((cours.getSession()).equals(arg)) {
                    coursSessionSpecifiee.add(cours);
                    System.out.println(cours.toString() + "heehaa"); //pour débogage
                }
            }

            // Renvoyer la liste des cours pour une session au client avec objectOutputStream
            this.objectOutputStream.writeObject(coursSessionSpecifiee); //ajouter liste des cours correspondant à la
            // Session spécifiée dans fichier à envoyer au client
            System.out.println(Arrays.asList(coursSessionSpecifiee) + "heehoo"); //pour débogage

        } catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier ou de l'écriture de l'objet.");
        }
    }

    /**
     * Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un
     * fichier texte et renvoyer un message de confirmation au client.
     * La méthode gère les exceptions si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier
     * ou dans le flux de sortie.
     */
    private final Object lockInscription = new Object();

    public void handleRegistration() {

        synchronized (lockInscription) {
            String message = "";
            System.out.println("1st beep");
            try {
                System.out.println("2nd beep");
                //Récupérer le RegistrationForm envoyé par le client
                RegistrationForm form = (RegistrationForm) objectInputStream.readObject();
                System.out.println("3rd beep");

                //Enregistrer l'inscription dans inscription.txt
                FileWriter fw = new FileWriter("src/main/java/server/data/inscription.txt", true);
                BufferedWriter writer = new BufferedWriter(fw);

                System.out.println(form.toString()); //débogage
                System.out.println(form.getCourse().toString()); //débogage
                String ligneInscription = "\n" + form.getCourse().getSession() + "\t" + form.getCourse().getCode() + "\t" +
                        form.getMatricule() + "\t" + form.getNom() + "\t" + form.getPrenom() + "\t" + form.getEmail();
                System.out.println(ligneInscription); //pour déboguage
                writer.append(ligneInscription);
                writer.close();

                String inscriptionMessage = "Inscription réussie!";
                message = inscriptionMessage;
                System.out.println(inscriptionMessage); // déboguage

            } catch (IOException | ClassNotFoundException e) {
                String erreurMessage = "Il y a eu une erreur pour compléter l'inscription. (dans Server)";
                message = erreurMessage;
                System.out.println(erreurMessage); //débogage
            } finally {
                try {
                    this.objectOutputStream.writeObject(message);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
