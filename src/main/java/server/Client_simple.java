package server;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client_simple {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1337);

            System.out.print("Veuillez entrer 1 pour consulter la liste des cours pour une session ou entrer 2 pour vous inscrire à un cours. \n");

            Scanner scanner1 = new Scanner(System.in);
            Integer choice = scanner1.nextInt();
            if (choice == 1) {
                voirSession(clientSocket);
            } else if (choice == 2) {
                inscrireCours(clientSocket);
            } else {
                System.out.println("Uhh okay");
            }
            scanner1.close();

        } catch (UnknownHostException e){
            System.out.println("IP address cannot be determined");
        } catch (IOException | ClassNotFoundException i){

        }
    }

    public static void voirSession(Socket clientSocket) throws IOException, ClassNotFoundException {
        System.out.println("*** Bienvenue au portail d'inscription de cours de l'UDEM *** \n" +
                            "Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                            "1. Automne \n2. Hiver\n3. Ete");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        //Envoyer la commande
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        scanner.close();

        String session = "";
        switch(choix) {
            case 1:
                session = "Automne";
                break;
            case 2:
                session = "Hiver";
                break;
            case 3:
                session = "Ete";
                break;
        }

        String line = ("CHARGER " + session);

        objectOutputStream.writeObject(line);
        System.out.println("Envoi de la requête: " + line);

        //Afficher les cours de la session spécifiée
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        ArrayList<Course> reponse = (ArrayList<Course>) objectInputStream.readObject();

        System.out.println("Les cours offerts pendant la session d'"+ session.toLowerCase() + " sont:");
        int nb = 0;
        for (Course cours: reponse){
            nb += 1;
            System.out.println(nb + " " + cours.getCode() + "\t" + cours.getName());
        }

        objectInputStream.close();
        objectOutputStream.close();
    }

    public static void inscrireCours(Socket clientSocket) throws IOException, ClassNotFoundException {
        //Envoi de la commande
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream()); //*** À modifier, car répétition dans le code
        String ligne = "INSCRIRE";
        objectOutputStream.writeObject(ligne);
        System.out.println("Envoi de la requête: " + ligne);

        //Inscription -- Envoi du formulaire d'inscription
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Veuillez saisir votre prénom: ");
        String prenom = scanner2.nextLine();
        System.out.print("Veuillez saisir votre nom: ");
        String nom = scanner2.nextLine();
        System.out.print("Veuillez saisir votre email: ");
        String email = scanner2.nextLine();
        System.out.print("Veuillez saisir votre matricule: ");
        String matricule = scanner2.nextLine();
        System.out.print("Veuillez saisir le code du cours: ");
        String code = scanner2.nextLine();

        //TODO probablement à modifier
        FileReader fr = new FileReader("src/main/java/server/data/cours.txt");
        BufferedReader reader = new BufferedReader(fr);

        //Créer liste d'objets Course à partir du fichier cours.txt
        ArrayList<Course> listeClasses = new ArrayList<>();
        String line; //ligne dans le fichier
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            //Créer nouvel objet Course à ajouter
            Course cours = new Course(parts[1], parts[0], parts[2]);
            listeClasses.add(cours);
        }

        Course coursAInscrire = null;
        //Chercher pour Course correspondant au code donné par l'utilisateur
        for (Course classe: listeClasses){
            if (classe.getCode().equals(code)){
                coursAInscrire = classe;
                break;
            }
        }

        System.out.println(coursAInscrire.toString());
        RegistrationForm formulaire = new RegistrationForm(prenom, nom, email, matricule, coursAInscrire);
        objectOutputStream.writeObject(formulaire);

        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        String reussite = (String) objectInputStream.readObject();
        System.out.println(reussite);
    }

}
