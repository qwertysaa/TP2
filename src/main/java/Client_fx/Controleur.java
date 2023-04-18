package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;

import java.io.File;

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
            Erreur.display("Erereur","non"); //TODO ** débogage
            String prenom = this.vue.getEnterPrenom().getText();
            String nom = this.vue.getEnterNom().getText();
            String email = this.vue.getEnterEmail().getText();
            String matricule = this.vue.getEnterMatricule().getText();
            Course coursSelectionne = this.vue.getCoursSession().getSelectionModel().getSelectedItem();
            System.out.println(prenom + " " + nom + " " + email + " " + matricule + " " + coursSelectionne.toString());
            System.out.println(this.modele.inscrireCours(prenom, nom, email, matricule, coursSelectionne));

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