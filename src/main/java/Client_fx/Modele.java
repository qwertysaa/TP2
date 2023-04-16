package Client_fx;

import server.models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Modele {
    Socket clientSocket;
    public Modele(){
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
        }catch (IOException | ClassNotFoundException e){

        }
        return null; //???? faut l'ajouter car sinon erreur selon IDE
    }
}

