package Client_fx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/*
 * Dans cette classe nous definissons les éléments graphiques de notre
 * application.
 * Notez que cette classe est completement independante de toute definition
 * de comportement.
 */
public class Vue extends HBox {
    private Text textCours = new Text("Liste des cours");
    private Button charger = new Button("charger");
    private Text textInscription = new Text("Formulaire d'inscription");
    private Button envoyer = new Button("envoyer");

    public Vue() {

        this.getChildren().add(textCours);
        this.getChildren().add(textInscription);


    }

}
