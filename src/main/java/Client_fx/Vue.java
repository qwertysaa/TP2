package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Course> coursSession = new TableView<>();
    private ChoiceBox<String> session = new ChoiceBox<>();
    private Button charger = new Button("charger");
    private TextField enterPrenom = new TextField();
    private TextField enterNom = new TextField();
    private TextField enterEmail = new TextField();
    private TextField enterMatricule = new TextField();
    private Button envoyer = new Button("envoyer");

    Vue() {

        VBox leftVBox = new VBox();
        Text textCours = new Text("                          Liste des cours");
        HBox coursHBox = new HBox();
        coursHBox.getChildren().add(textCours);
        leftVBox.getChildren().add(coursHBox);
        coursHBox.setPadding(new Insets(7));

        leftVBox.setPadding(new Insets(10));

        leftVBox.getChildren().add(coursSession);
        coursSession.setEditable(true);
        TableColumn<Course, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Course, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        coursSession.getColumns().setAll(codeColumn,coursColumn);
        coursSession.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox tableHBox = new HBox();
        tableHBox.getChildren().add(coursSession);
        leftVBox.getChildren().add(tableHBox);

        HBox bottomLeftHBox = new HBox();
        Separator sep = new Separator();
        sep.setPrefHeight(25);
        leftVBox.getChildren().add(sep);
        String[] sessions = {"Hiver", "Automne", "Ete"};
        session.getItems().addAll(sessions);
        bottomLeftHBox.getChildren().add(session);
        bottomLeftHBox.getChildren().add(charger);
        bottomLeftHBox.setSpacing(100);
        leftVBox.getChildren().add(bottomLeftHBox);

        this.setLeft(leftVBox);

        VBox rightVBox = new VBox();
        HBox inscription = new HBox();
        Text textInscription = new Text("                         Formulaire d'inscription");
        inscription.getChildren().add(textInscription);
        rightVBox.getChildren().add(inscription);
        inscription.setPadding(new Insets(10));

        HBox prenom = new HBox();
        Text textPrenom = new Text("Prénom    ");
        prenom.getChildren().add(textPrenom);
        prenom.getChildren().add(enterPrenom);
        rightVBox.getChildren().add(prenom);
        enterPrenom.setMinWidth(200);
        prenom.setPadding(new Insets(10));


        HBox nom = new HBox();
        Text textNom = new Text("Nom         ");
        nom.getChildren().add(textNom);
        nom.getChildren().add(enterNom);
        rightVBox.getChildren().add(nom);
        nom.setPadding(new Insets(10));
        enterNom.setMinWidth(200);

        HBox email = new HBox();
        Text textEmail = new Text("Email        ");
        email.getChildren().add(textEmail);
        email.getChildren().add(enterEmail);
        rightVBox.getChildren().add(email);
        email.setPadding(new Insets(10));
        enterEmail.setMinWidth(200);

        HBox matricule = new HBox();
        Text textMatricule = new Text("Matricule  ");
        matricule.getChildren().add(textMatricule);
        matricule.getChildren().add(enterMatricule);
        rightVBox.getChildren().add(matricule);
        matricule.setPadding(new Insets(10));
        enterMatricule.setMinWidth(200);

        rightVBox.getChildren().add(envoyer);
        envoyer.setPadding(new Insets(10));

        this.setCenter(rightVBox);
    }
    public TableView<Course> getCoursSession() {
        return this.coursSession;
    }
    public ChoiceBox<String> getSession() {
        return session;
    }
    public Button getChargerButton() {
        return this.charger;
    }
    public TextField getEnterPrenom() {
        return enterPrenom;
    }
    public TextField getEnterNom() {
        return enterNom;
    }
    public TextField getEnterEmail() {
        return enterEmail;
    }
    public TextField getEnterMatricule() {
        return enterMatricule;
    }
    public Button getEnvoyerButton() {
        return this.envoyer;
    }

}
