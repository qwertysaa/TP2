package Client_simple;

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
            Scanner scanner = new Scanner(System.in);
            Socket clientSocket = new Socket("localhost", 1337);
            System.out.println("*** Bienvenue au portail d'inscription de cours de l'UDEM *** \n" +
                    "Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                    "1. Automne \n2. Hiver\n3. Ete");
            voirSession(clientSocket, scanner);

            System.out.print("1. Consulter les cours offerts pour une autre session\n" +
                    "2. Inscription à un cours \n");

            System.out.print("> Choix: ");
            boolean repeat = true;
            while (repeat) {
                clientSocket = new Socket("localhost", 1337);
                int choice = scanner.nextInt();
                if (choice == 1) {
                    voirSession(clientSocket, scanner);
                    repeat = false;
                } else if (choice == 2) {
                    inscrireCours(clientSocket, scanner);
                    repeat = false;
                } else {
                    System.out.println("Veuillez entrer 1 ou 2.");
                    repeat = true;
                }
            }
            scanner.close();

        } catch (UnknownHostException e){
            System.out.println("IP address cannot be determined");
        } catch (IOException | ClassNotFoundException i){
            System.out.println("Code du cours non disponible à la session choisie.");
        }
    }
    public static ArrayList<Course> reponse;
    public static void voirSession(Socket clientSocket, Scanner scanner) throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        //Envoyer la commande
        System.out.print("> Choix: ");
        int choix = scanner.nextInt();

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
        reponse = (ArrayList<Course>) objectInputStream.readObject();

        System.out.println("Les cours offerts pendant la session d'"+ session.toLowerCase() + " sont:");
        int nb = 0;
        for (Course cours: reponse){
            nb += 1;
            System.out.println(nb + " " + cours.getCode() + "\t" + cours.getName());
        }

        objectInputStream.close();
        objectOutputStream.close();
    }

    public static void inscrireCours(Socket clientSocket, Scanner scanner) throws IOException, ClassNotFoundException {
        //Envoi de la commande
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        //*** À modifier, car répétition dans le code
        String ligne = "INSCRIRE";
        objectOutputStream.writeObject(ligne);
        System.out.println("Envoi de la requête: " + ligne);

        //Inscription -- Envoi du formulaire d'inscription
        String blank = scanner.nextLine(); //petit bug de scanner
        System.out.print("Veuillez saisir votre prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Veuillez saisir votre nom: ");
        String nom = scanner.nextLine();
        System.out.print("Veuillez saisir votre email: ");
        String email = scanner.nextLine();
        System.out.print("Veuillez saisir votre matricule: ");
        String matricule = scanner.nextLine();
        System.out.print("Veuillez saisir le code du cours: ");
        String code = scanner.nextLine();

        /*FileReader fr = new FileReader("src/main/java/server/data/cours.txt");
        BufferedReader reader = new BufferedReader(fr);

        //Créer liste d'objets Course à partir du fichier cours.txt
        ArrayList<Course> listeClasses = new ArrayList<>();
        String line; //ligne dans le fichier
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            //Créer nouvel objet Course à ajouter
            Course cours = new Course(parts[1], parts[0], parts[2]);
            listeClasses.add(cours);
        }*/

        Course coursAInscrire = null;
        //Chercher pour Course correspondant au code donné par l'utilisateur
        for (Course classe: reponse){
            if (classe.getCode().equals(code)){
                coursAInscrire = classe;
                break;
            }
        }
        if (coursAInscrire == null){
            throw new ClassNotFoundException();
        }

        System.out.println(coursAInscrire.toString());
        RegistrationForm formulaire = new RegistrationForm(prenom, nom, email, matricule, coursAInscrire);
        objectOutputStream.writeObject(formulaire);

        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        String reussite = (String) objectInputStream.readObject();
        System.out.println(reussite);
    }

}
