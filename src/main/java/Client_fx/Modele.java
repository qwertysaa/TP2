package Client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe qui représente la partie logique de client_fx.
 */
public class Modele {
    private Socket clientSocket;

    /**
     * Contructeur de la classe
     */
    public Modele() {}

    /**
     * Méthode qui cherche la liste des cours de la session spécifiée
     * @param session   session spécifiée
     * @return  retourne un ArrayList<Course> des cours de la session spécifiée
     */
    public ArrayList<Course> voirSession(String session) {
        try {
            clientSocket = new Socket("localhost", 1337);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Envoi de la commande appropriée
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            String line = ("CHARGER " + session);

            objectOutputStream.writeObject(line);
            System.out.println("Envoi de la requête: " + line); //débogage

            // Afficher les cours de la session spécifiée
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ArrayList<Course> reponse = (ArrayList<Course>) objectInputStream.readObject();

            System.out.println("Les cours offerts pendant la session d'" + session.toLowerCase() + " sont:");
            int nb = 0;
            for (Course cours : reponse) {
                nb += 1;
                System.out.println(nb + " " + cours.getCode() + "\t" + cours.getName());
            }

            objectInputStream.close();
            objectOutputStream.close();

            return reponse;

        } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erreur lors de l'écriture ou de la lecture de l'objet.");
        }
        return null;
    }

    /**
     * Méthode qui inscrit avec les données entrées par le client.
     * @param prenom    prenom saisi par le client
     * @param nom       nom saisi par le client
     * @param email     email saisi par le client
     * @param matricule matricule saisi par le client
     * @param coursSelectionne  cours selectionné par le client
     * @return retourne un message de réussite ou d'échec
     */
    public String inscrireCours(String prenom, String nom, String email, String matricule, Course coursSelectionne) {
        try {
            clientSocket = new Socket("localhost", 1337);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            // Envoi de la commande au serveur
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // *** À modifier, car répétition dans le code
            String ligne = "INSCRIRE";
            objectOutputStream.writeObject(ligne);
            System.out.println("Envoi de la requête: " + ligne);

            RegistrationForm formulaire = new RegistrationForm(prenom, nom, email, matricule, coursSelectionne);
            System.out.println("formulaire :"+ formulaire.toString());
            objectOutputStream.writeObject(formulaire);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            String message = (String) objectInputStream.readObject();
            return message;
        } catch (IOException e) {
            String erreur = "Il y a eu une erreur pour compléter l'inscription. (dans Modèle)";
            return erreur;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
