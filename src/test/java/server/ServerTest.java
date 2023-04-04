package server;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerTest extends TestCase {
@Test
    public void testHandleLoadCourses() {
    try{
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
        for (Object o: listecours){
            System.out.println(Arrays.asList((String[])o)); //pour débogage
        }
    }catch (FileNotFoundException i){

    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}
}