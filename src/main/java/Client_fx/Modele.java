package Client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Modele {
    Socket clientSocket;

    public Modele() {
    }

    public ArrayList<Course> voirSession(String session) {
        try {
            clientSocket = new Socket("localhost", 1337);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //Envoi de la commande appropriée
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            String line = ("CHARGER " + session);

            objectOutputStream.writeObject(line);
            System.out.println("Envoi de la requête: " + line); //débogage

            //Afficher les cours de la session spécifiée
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
        }
        return null; //???? faut l'ajouter car sinon erreur selon IDE
    }

    public String inscrireCours(String prenom, String nom, String email, String matricule, Course coursSelectionne) {
        try {
            clientSocket = new Socket("localhost", 1337);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //Envoi de la commande
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            //*** À modifier, car répétition dans le code
            String ligne = "INSCRIRE";
            objectOutputStream.writeObject(ligne);
            System.out.println("Envoi de la requête: " + ligne);

            //TODO probablement à modifier
            RegistrationForm formulaire = new RegistrationForm(prenom, nom, email, matricule, coursSelectionne);
            System.out.println("formulaire :"+ formulaire.toString());
            objectOutputStream.writeObject(formulaire);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            String reussite = (String) objectInputStream.readObject();
            return reussite;
        } catch (IOException e) {
            String erreur = "Il y a eu une erreur pour compléter l'inscription.";
            return erreur;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }
}
