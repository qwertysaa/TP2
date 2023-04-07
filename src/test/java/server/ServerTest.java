package server;

import junit.framework.TestCase;
import org.junit.Test;
import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerTest extends TestCase {
    @Test
    public void testHandleLoadCourses() {
        try {
            Server testserver = new Server(1337);
            testserver.handleLoadCourses("Automne");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    @Test // expérimentation: comment reconvertir le fichier object envoyé au client pour récupérer le ArrayList
    public void testListeCoursSessionSpecifiee() {
        try {
            FileInputStream fileOs = new FileInputStream("listeCoursSessionSpecifiee.txt");
            ObjectInputStream os = new ObjectInputStream(fileOs);

            ArrayList<Object> listecours = (ArrayList<Object>) os.readObject(); //ajouter objet dans fichier "sérialisé" pour le client
            System.out.println(Arrays.asList(listecours)); //pour débogage
            for (Object o : listecours) {
                System.out.println(Arrays.asList((String[]) o)); //pour débogage
            }
        } catch (FileNotFoundException i) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHandleRegistration() {
        try {
            //TODO put a file for objectInputStream
            Server testserver = new Server(1337);
            testserver.handleRegistration();
        } catch (IOException e) {
        }
    }

    @Test //expérimentation avec bufferedwriter
    public void testUn() {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\tokat\\IdeaProjects\\TP2\\src\\main\\java\\server\\data\\inscription.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            String line = "ABC";
            writer.append(line);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}