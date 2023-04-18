package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;

import java.io.File;
import java.io.IOError;

public class Controleur {

    private Modele modele;
    private Vue vue;
    public String messageOuErreur = "Message";
    public String lesErreurs = "";

    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;

        this.vue.getChargerButton().setOnAction((event) ->{
            String sessionSelectionnee = this.vue.getSession().getSelectionModel().getSelectedItem();
            this.vue.getCoursSession().setItems((getCoursDisponibles(sessionSelectionnee)));
        });
        this.vue.getEnvoyerButton().setOnAction((event) ->{
            String prenom = checkLettres(this.vue.getEnterPrenom().getText());
            String nom = checkLettres(this.vue.getEnterNom().getText());
            String email = checkEmail(this.vue.getEnterEmail().getText());
            String matricule = this.vue.getEnterMatricule().getText();
            Course coursSelectionne = this.vue.getCoursSession().getSelectionModel().getSelectedItem();
            System.out.println(prenom + " " + nom + " " + email + " " + matricule + " " + coursSelectionne.toString());
            System.out.println(this.modele.inscrireCours(prenom, nom, email, matricule, coursSelectionne));
            String message = this.modele.inscrireCours(prenom, nom, email, matricule, coursSelectionne);
            System.out.println("message reçu: " + message); //débogage
            if (messageOuErreur.equals("Message")){
                Erreur.display("Message",message);
            }else{
                Erreur.display("Erreur",lesErreurs);
                messageOuErreur = "Message";
                lesErreurs="";
            }

        });

    };


    public boolean isAllLetters(String value) {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public boolean isAllNumbers(String value) {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**Vérifie si la valeur entrée ne contient que des lettres.
     *
     * @param valeur
     * @return
     */
    public String checkLettres(String valeur){
        Boolean allLetters = isAllLetters(valeur);
        if (allLetters) {
            return valeur;

        }else{
            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Nom ou prénom ne doit contenir que des lettres.\n";
        }
        return valeur;
    }


    public String checkEmail(String valeur){
        Boolean bonEmail = valeur.endsWith("@umontreal.ca");
        if (bonEmail) {
            return valeur;

        }else{
            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Courriel doit venir de l'Université de Montréal.\n";
            // throw new WrongEntryException("Courriel doit venir de l'Université de Montréal.");
        }
        return valeur;
    }


    public ObservableList<Course> getCoursDisponibles(String session){
        ObservableList<Course> coursDisponibles = FXCollections.observableArrayList();
        for (Course course : this.modele.voirSession(session)) {
            coursDisponibles.add(course);
        }
        return coursDisponibles;
    }
}
