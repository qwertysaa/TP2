package Client_fx;

import javafx.geometry.Insets;
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
import javafx.geometry.HPos;
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
        Text textCours = new Text("                              Liste des cours");
        HBox coursHBox = new HBox();
        coursHBox.getChildren().add(textCours);
        leftVBox.getChildren().add(textCours);
        coursHBox.setPadding(new Insets(10));
        Text ligneVide = new Text("");

        leftVBox.setPadding(new Insets(10));

        HBox tableHBox = new HBox();
        TableView<Course> coursSession = new TableView<>();
        tableHBox.getChildren().add(coursSession);
        leftVBox.getChildren().add(tableHBox);

        HBox bottomLeftHBox = new HBox();
        ChoiceBox<String> session = new ChoiceBox<>();
        String[] sessions = {"Hiver", "Automne", "Ete"};
        session.getItems().addAll(sessions);
        bottomLeftHBox.getChildren().add(session);
        Button charger = new Button("charger");
        bottomLeftHBox.getChildren().add(charger);
        bottomLeftHBox.setSpacing(150);
        leftVBox.getChildren().add(bottomLeftHBox);

        this.setLeft(leftVBox);

        VBox rightVBox = new VBox();
        HBox inscription = new HBox();
        Text textInscription = new Text("                         Formulaire d'inscription");
        inscription.getChildren().add(textInscription);
        rightVBox.getChildren().add(inscription);
        inscription.setPadding(new Insets(5));

        HBox prenom = new HBox();
        Text textPrenom = new Text("Prénom    ");
        prenom.getChildren().add(textPrenom);
        TextField enterPrenom = new TextField();
        prenom.getChildren().add(enterPrenom);
        rightVBox.getChildren().add(prenom);
        enterPrenom.setMinWidth(200);
        prenom.setPadding(new Insets(10));


        HBox nom = new HBox();
        Text textNom = new Text("Nom         ");
        nom.getChildren().add(textNom);
        TextField enterNom = new TextField();
        nom.getChildren().add(enterNom);
        rightVBox.getChildren().add(nom);
        nom.setPadding(new Insets(10));
        enterNom.setMinWidth(200);

        HBox email = new HBox();
        Text textEmail = new Text("Email        ");
        email.getChildren().add(textEmail);
        TextField enterEmail = new TextField();
        email.getChildren().add(enterEmail);
        rightVBox.getChildren().add(email);
        email.setPadding(new Insets(10));
        enterEmail.setMinWidth(200);

        HBox matricule = new HBox();
        Text textMatricule = new Text("Matricule  ");
        matricule.getChildren().add(textMatricule);
        TextField enterMatricule = new TextField();
        matricule.getChildren().add(enterMatricule);
        rightVBox.getChildren().add(matricule);
        matricule.setPadding(new Insets(10));
        enterMatricule.setMinWidth(200);

        Button envoyer = new Button("envoyer");
        rightVBox.getChildren().add(envoyer);
        envoyer.setPadding(new Insets(10));

        this.setCenter(rightVBox);
    }
}
