package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        // Le VBox à gauche qui montre les cours disponibles
        VBox leftVBox = new VBox();

        // Pour mettre couleur Beige au fond
        leftVBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        Text textCours = new Text("              Liste des cours");
        textCours.setFont(Font.font(18));

        // Separer le leftVBox en 3 HBox, cours HBox est le premier
        HBox coursHBox = new HBox();
        coursHBox.getChildren().add(textCours);
        leftVBox.getChildren().add(coursHBox);
        coursHBox.setPadding(new Insets(5));

        // pour mettre l'espace entre leftVBox avec le mur et le rightVBox
        leftVBox.setPadding(new Insets(10));

        // Construire deux colonnes dans le Table qui représentent le code et le nom de cours disponibles
        leftVBox.getChildren().add(coursSession);
        coursSession.setEditable(true);
        TableColumn<Course, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Course, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        coursSession.getColumns().setAll(codeColumn,coursColumn);
        coursSession.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Le deuxième HBox dans le leftVBox qui contient Table avec les cours
        HBox tableHBox = new HBox();
        tableHBox.getChildren().add(coursSession);
        leftVBox.getChildren().add(tableHBox);

        // Separateur horizontale qui sépare le Table avec les deux boutons
        Separator sepHoriz = new Separator();
        sepHoriz.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        leftVBox.getChildren().add(sepHoriz);

        // Le deux derniers HBoxs dans le leftVBox qui contient boutons session et charger qui envoyent une demande au
        // Serveur pour afficher les cours dispoibles dans la session désiré
        HBox bottomLeftHBox = new HBox();
        String[] sessions = {"Hiver", "Automne", "Ete"};
        session.getItems().addAll(sessions);

        HBox chargerHBox = new HBox();
        chargerHBox.getChildren().add(charger);
        chargerHBox.setAlignment(Pos.BASELINE_RIGHT);
        bottomLeftHBox.getChildren().add(session);
        bottomLeftHBox.getChildren().add(chargerHBox);
        bottomLeftHBox.setHgrow(chargerHBox, Priority.ALWAYS);
        leftVBox.getChildren().add(bottomLeftHBox);
        charger.setPadding(new Insets(10));
        session.setPadding(new Insets(6));
        session.setValue("Session");

        // Ajouter de l'espace entre le table et les boutons
        leftVBox.setSpacing(20);

        this.setLeft(leftVBox);

        // Construire le VBox à droite qui s'agit le formulaire d'inscription
        VBox rightVBox = new VBox();

        Separator sepVert = new Separator();
        sepVert.setOrientation(Orientation.VERTICAL);
        sepVert.setPrefHeight(300);
        sepVert.setPrefWidth(1);

        // Pour mettre couleur Beige au fond
        rightVBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        rightVBox.setPadding(new Insets(5));

        // Le premier HBox dans le rightVBox qui contient le titre
        HBox inscription = new HBox();
        Text textInscription = new Text("            Formulaire d'inscription");
        inscription.getChildren().add(textInscription);
        rightVBox.getChildren().add(inscription);
        inscription.setPadding(new Insets(10));
        textInscription.setFont(Font.font(18));

        // Le deuxième HBox dans le rightVBox qui contient le champ prenom du formulaire
        HBox prenom = new HBox();
        Text textPrenom = new Text("Prénom    ");
        prenom.getChildren().add(textPrenom);
        prenom.getChildren().add(enterPrenom);
        rightVBox.getChildren().add(prenom);
        enterPrenom.setMinWidth(240);
        prenom.setPadding(new Insets(10));

        // Le troisième HBox dans le rightVBox qui contient le champ nom du formulaire
        HBox nom = new HBox();
        Text textNom = new Text("Nom         ");
        nom.getChildren().add(textNom);
        nom.getChildren().add(enterNom);
        rightVBox.getChildren().add(nom);

        nom.setPadding(new Insets(10));
        enterNom.setMinWidth(240);

        // Le quatrième HBox dans le rightVBox qui contient le champ email du formulaire
        HBox email = new HBox();
        Text textEmail = new Text("Email        ");
        email.getChildren().add(textEmail);
        email.getChildren().add(enterEmail);
        rightVBox.getChildren().add(email);

        email.setPadding(new Insets(10));
        enterEmail.setMinWidth(240);

        // Le cinquième HBox dans le rightVBox qui contient le champ prenom du formulaire
        HBox matricule = new HBox();
        Text textMatricule = new Text("Matricule  ");
        matricule.getChildren().add(textMatricule);
        matricule.getChildren().add(enterMatricule);
        rightVBox.getChildren().add(matricule);

        matricule.setPadding(new Insets(10));
        enterMatricule.setMinWidth(240);

        // Le dernier HBox dans le rightVBox qui contient le bouton envoyer, ce dernier verifie si les informations
        // sont rentrés correctement et ensuite update la fiche d'inscription et envoyer en message
        HBox envoyerHBox = new HBox();
        envoyerHBox.getChildren().add(envoyer);
        //TODO ** //envoyer.setOnAction(e -> Erreur.display("Erreur","non"));
        rightVBox.getChildren().add(envoyerHBox);


        envoyer.setPadding(new Insets(10));
        envoyerHBox.setPadding(new Insets(10));

        // Pour mettre l'espace blanc entre le rightVBox et le leftVBox
        this.setRight(rightVBox);
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
