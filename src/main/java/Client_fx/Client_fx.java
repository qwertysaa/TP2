package Client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        Modele laModele = new Modele();
        Erreur leErreur = new Erreur();
        ControleurErreur laControleur = new ControleurErreur(leModele, leErreur);
        Scene sceneErreur = new Scene(leErreur, 400, 200);
        stage.setTitle("Erreur");
        stage.setScene(sceneErreur);
        stage.show();
    }
}
