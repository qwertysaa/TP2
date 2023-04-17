package Client_fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;

/*
 * Dans cette classe nous definissons les éléments graphiques d'une fenetre qui s'ouvre en appuyant sur le button
 * envoyer de classe vue.java. Cette dernière affiche soit une message félicitation, soit des erreurs à propos des
 * champs de formulaire.
 */

public class Erreur {
    public static void display(String title, String message) {
        Stage window = new Stage();
        VBox layout = new VBox();

        // Modality fait en sorte que le client ne peut pas faire quoi de soit avant de fermer la fenetre Erreur.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        // Label type est soit "Message", soit "Erreur". Ce dernier est utilisé dans le titre de la fenetre ainsi que
        // La première ligne de texte
        Label type = new Label();
        type.setText(title);
        type.setFont(Font.font(25));
        HBox titleHBox = new HBox();
        titleHBox.getChildren().add(type);

        // Ajouter le premier HBox dans le VBox et mettre de l'espace avant les lignes
        layout.getChildren().add(titleHBox);
        titleHBox.setPadding(new Insets(10));

        //Image img = new Image("img.png");
        //ImageView imageView = new ImageView(img);
        //layout.getChildren().add(imageView);
        //imageView.setFitWidth(200);
        //imageView.setFitHeight(150);


        // Separateur qui separe le titre avec le(s) message horizontalement
        Separator separateur = new Separator();
        separateur.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().add(separateur);

        // Label qui affiche les messages ou les erreur. On le met dans une deuxième HBox
        Label messageText = new Label();
        messageText.setText(message);
        HBox messageHBox = new HBox();
        messageHBox.getChildren().add(messageText);
        layout.getChildren().add(messageHBox);

        // Le dernier HBox qui est fait pour le button ok
        HBox buttonHBox = new HBox();
        Label vide = new Label();

        // On a mit un text vide avant le button pour pouvoir ensuite mettre une espace en devant. Ce qui fait que
        // le button soit à droite. Pour une raison inconnue, Allignement ou setRight ne fonctionnaient pas.
        vide.setText("");
        buttonHBox.getChildren().add(vide);
        Button okButton = new Button("Ok");

        // En appuyant sur le button ok, la fenetre ferme et le client essaye de nouveau le formulaire
        okButton.setOnAction(e -> window.close());
        buttonHBox.getChildren().add(okButton);
        buttonHBox.setSpacing(280);
        okButton.setMinWidth(70);
        layout.getChildren().add(buttonHBox);

        messageHBox.setPadding(new Insets(10));
        layout.setPadding(new Insets(15));

        // Pour afficher le VBox. fonction showAndWait() fait en sorte que le client doit absolument fermer la fenetre
        // avant de faire d'autre choses.
        Scene sceneErreur = new Scene(layout,400,200);
        window.setScene(sceneErreur);
        window.showAndWait();

        /** VBox erreurVBox = new VBox();
        erreurVBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Text textErreur = new Text("Erreur");
        HBox erreurHBox = new HBox();
        erreurHBox.getChildren().add(textErreur);
        erreurVBox.getChildren().add(erreurHBox);

        textErreur.setFont(Font.font(5));
        erreurVBox.setPadding(new Insets(10));

        Separator separateur = new Separator();
        separateur.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        erreurVBox.getChildren().add(separateur);

        HBox messageHBox = new HBox();
        Text textMessage = new Text("");
        messageHBox.getChildren().add(textMessage);
        erreurVBox.getChildren().add(messageHBox);**/

    }
}



