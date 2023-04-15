package Client_fx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.models.Course;

/*
 * Dans cette classe nous definissons les éléments graphiques de notre
 * application.
 * Notez que cette classe est completement independante de toute definition
 * de comportement.
 */
public class Vue extends BorderPane {
    Vue() {

        VBox leftVBox = new VBox();
        Text textCours = new Text("Liste des cours");
        leftVBox.getChildren().add(textCours);

        TableView<Course> coursSession = new TableView<>();
        leftVBox.getChildren().add(coursSession);

        HBox bottomLeftHBox = new HBox();
        ChoiceBox<String> session = new ChoiceBox<>();
        String[] sessions = {"Hiver", "Automne", "Ete"};
        session.getItems().addAll(sessions);
        bottomLeftHBox.getChildren().add(session);
        Button charger = new Button("charger");
        bottomLeftHBox.getChildren().add(charger);
        leftVBox.getChildren().add(bottomLeftHBox);

        this.setLeft(leftVBox);

        VBox rightVBox = new VBox();
        Text textInscription = new Text("Formulaire d'inscription");
        rightVBox.getChildren().add(textInscription);

        HBox prenom = new HBox();
        Text textPrenom = new Text("Prénom");
        prenom.getChildren().add(textPrenom);
        TextField enterPrenom = new TextField();
        prenom.getChildren().add(enterPrenom);
        rightVBox.getChildren().add(prenom);

        HBox nom = new HBox();
        Text textNom = new Text("Nom");
        nom.getChildren().add(textNom);
        TextField enterNom = new TextField();
        nom.getChildren().add(enterNom);
        rightVBox.getChildren().add(nom);

        HBox email = new HBox();
        Text textEmail = new Text("Email");
        email.getChildren().add(textEmail);
        TextField enterEmail = new TextField();
        email.getChildren().add(enterEmail);
        rightVBox.getChildren().add(email);

        HBox matricule = new HBox();
        Text textMatricule = new Text("Matricule");
        matricule.getChildren().add(textMatricule);
        TextField enterMatricule = new TextField();
        matricule.getChildren().add(enterMatricule);
        rightVBox.getChildren().add(matricule);

        Button envoyer = new Button("envoyer");
        rightVBox.getChildren().add(envoyer);
        this.setRight(rightVBox);
    }test.
}
