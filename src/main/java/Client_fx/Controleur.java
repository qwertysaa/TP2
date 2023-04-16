package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;

public class Controleur {

    private Modele modele;
    private Vue vue;

    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;

        this.vue.getChargerButton().setOnAction((event) ->{
            String sessionSelectionnee = this.vue.getSession().getSelectionModel().getSelectedItem();
            this.vue.getCoursSession().setItems((getCoursDisponibles(sessionSelectionnee)));
        });
        this.vue.getEnvoyerButton().setOnAction((event) ->{

        });
    }
    public ObservableList<Course> getCoursDisponibles(String session){
        ObservableList<Course> coursDisponibles = FXCollections.observableArrayList();
        for (Course course : this.modele.voirSession(session)) {
            coursDisponibles.add(course);
        }
        return coursDisponibles;
    }
}