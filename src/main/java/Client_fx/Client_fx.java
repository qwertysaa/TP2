package Client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.models.Course;

public class Client_fx extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Modele leModele = new Modele();
        Vue laVue = new Vue();
        Controleur leControleur = new Controleur(leModele, laVue);
        Scene scene = new Scene(laVue, 600, 600);

        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();

        //TODO ** //Erreur.display("Erreur","Invalid"); // Debogage

    }
}
