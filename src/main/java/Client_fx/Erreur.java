package Client_fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;



/**
 * Dans cette classe, nous définissons les éléments graphiques d'une fenêtre qui s'ouvre en appuyant sur le button
 * "envoyer" implémentée dans la classe vue.java. Cette dernière affiche soit un message félicitation, soit des messages
 * d'erreurs à propos des champs de formulaire.
 */
public class Erreur {
    /**Affiche la fenêtre pop-up
     *
     * @param title titre à afficher
     * @param message   message à afficher
     */
    public static void display(String title, String message) {
        Stage window = new Stage();
        VBox layout = new VBox();

        // Modality fait en sorte que le client ne peut pas faire quoi ce soit avant de fermer la fenêtre Erreur.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        // Label type est soit "Message", soit "Erreur". Ce dernier est utilisé dans le titre de la fenêtre ainsi que
        // pour la première ligne de texte.
        Label type = new Label();
        type.setText(title);
        type.setFont(Font.font(25));
        HBox titleHBox = new HBox();
        titleHBox.getChildren().add(type);

        // si le title de display est Erreur, on affiche une image d'un X rouge, else, on affiche un image d'un ! blue
        if (title == "Erreur"){
            Image img = new Image("file:src/main/java/Client_fx/img.png");
            ImageView imageView = new ImageView(img);
            titleHBox.getChildren().add(imageView);
            imageView.setFitWidth(35);
            imageView.setFitHeight(35);
            titleHBox.setSpacing(240);

        }else {
            Image img = new Image("file:src/main/java/Client_fx/img_1.png");
            ImageView imageView = new ImageView(img);
            titleHBox.getChildren().add(imageView);
            imageView.setFitWidth(35);
            imageView.setFitHeight(35);
            titleHBox.setSpacing(205);

        }

        // Ajouter le premier HBox dans le VBox et mettre de l'espace avant les lignes
        layout.getChildren().add(titleHBox);
        titleHBox.setPadding(new Insets(10));

        // Separateur qui separe le titre avec le(s) message horizontalement
        Separator separateur = new Separator();
        separateur.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().add(separateur);

        // Label qui affiche les messages ou les erreurs. On le met dans une deuxième HBox
        Label messageText = new Label();
        messageText.setText(message);
        HBox messageHBox = new HBox();
        messageHBox.getChildren().add(messageText);
        layout.getChildren().add(messageHBox);

        // Le dernier HBox qui est fait pour le button ok
        HBox buttonHBox = new HBox();
        Label vide = new Label();

        // On a mis un text vide avant le button pour pouvoir ensuite mettre une espace en devant. Ce qui fait que
        // le button soit à droite. Pour une raison inconnue, Allignement et setRight ne fonctionnaient pas.
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

    }
}



