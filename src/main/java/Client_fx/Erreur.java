package Client_fx;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * Dans cette classe nous definissons les éléments graphiques de notre
 * application.
 * Notez que cette classe est completement independante de toute definition
 * de comportement.
 */
public class Erreur extends BorderPane {
    Erreur() {
        VBox erreurVBox = new VBox();
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
        erreurVBox.getChildren().add(messageHBox);
    }}