package server.models;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1337);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            //ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            //Envoyer commande en format [commande][espace][arg1][espace][arg2]
            String line = scanner.nextLine();
            scanner.close();

            objectOutputStream.writeObject(line);
            System.out.println("Envoi de : " + line);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ArrayList<Course> reponse = (ArrayList<Course>) objectInputStream.readObject();
            for (Course cours: reponse){
                System.out.println(cours.toString());
            }


        } catch (UnknownHostException e){
            System.out.println("IP address cannot be determined");
        } catch (IOException | ClassNotFoundException i){

        }
    }
}
