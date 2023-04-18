package Client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;

import java.io.File;
import java.io.IOError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe représentant le controleur de client_fx
 */
public class Controleur {

    private Modele modele;
    private Vue vue;
    public String messageOuErreur = "Message";
    public String lesErreurs = "Le formulaire est invalid. \n";

    /**
     * Constructeur du controleur
     * @param m modele donné en paramètre pour ce controleur
     * @param v vue donnée en paramètre pour ce controleur
     */
    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;

        this.vue.getChargerButton().setOnAction((event) ->{
            String sessionSelectionnee = this.vue.getSession().getSelectionModel().getSelectedItem();
            this.vue.getCoursSession().setItems((getCoursDisponibles(sessionSelectionnee)));
        });
        this.vue.getEnvoyerButton().setOnAction((event) ->{
        try {

            // Vérifie si tout les champs de formulaires sont valides.
            String prenom = checkPrenom(this.vue.getEnterPrenom().getText());
            String nom = checkNom(this.vue.getEnterNom().getText());
            String email = checkEmail(this.vue.getEnterEmail().getText());
            String matricule = checkNumber(this.vue.getEnterMatricule().getText());

            Course coursSelectionne = this.vue.getCoursSession().getSelectionModel().getSelectedItem();

            System.out.println(this.modele.inscrireCours(prenom, nom, email, matricule, coursSelectionne));
            String message = this.modele.inscrireCours(prenom, nom, email, matricule, coursSelectionne);

            if (messageOuErreur.equals("Message")){
                String messageFelicitation = "Félicitation! " + prenom + " " + nom + " est inscrit(e) \n " +
                        "avec succès pour le cours "+ coursSelectionne.getCode() + "!";

                // Ouvre une fenetre pop qui affiche le message félicitation
                Erreur.display("Message", messageFelicitation);
                lesErreurs="Le formulaire est invalid. \n";

            } else {

                // Ouvre une fenetre pop qui affiche les messages d'erreurs
                Erreur.display("Erreur",lesErreurs);
                messageOuErreur = "Message";
                lesErreurs="Le formulaire est invalid. \n";

            }
        } catch (NullPointerException e){
            String messageCours = "Veuillez choisir un cours parmis la liste.";
            Erreur.display("Erreur", messageCours);
        }

        });

    };

    /**Vérifie si le champs "Nom" est valide.
     *
     * @param valeur valeur entrée par le client
     * @return  retourne la valeur du champ nom
     */
    public String checkNom(String valeur){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(valeur);

        if (m.find() && m.group().equals(valeur)){

            return valeur;

        }else{

            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Le champs 'Nom' est invalid!\n";

        }
        return valeur;
    }

    /**Vérifie si le champs "Prenom" est valide.
     *
     * @param valeur valeur entrée par le client
     * @return  retourne la valeur du champ prenom
     */
    public String checkPrenom(String valeur) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(valeur);

        if (m.find() && m.group().equals(valeur)) {

            return valeur;

        } else {

            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Le champs 'Prénom' est invalid!\n";

        }
        return valeur;
    }

    /**Vérifie si le champs "Email" est valide.
     *
     * @param valeur valeur entrée par le client
     * @return  retourne la valeur du champ email
     */
    public String checkEmail(String valeur){
        Pattern p = Pattern.compile("[a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(valeur);

        if (m.find() && m.group().equals(valeur)){

            return valeur;

        }else{

            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Le champs 'Email' est invalid!\n";

        }
        return valeur;
    }

    /**Vérifie si le champs "Matricule" est valide.
     *
     * @param valeur valeur entrée par le client
     * @return  retourne la valeur du champ matricule
     */
    public String checkNumber(String valeur) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(valeur);

        if (m.find() && m.group().equals(valeur)) {

            return valeur;

        } else {

            messageOuErreur = "Erreur";
            lesErreurs = lesErreurs + "Le champs 'Matricule' est invalide!\n";

        }
        return valeur;
    }


    /** Reçoit les cours disponibles et retourne la liste des cours pour le tableau.
     *
     * @param session   session désirée
     * @return  retourne une liste prise en compte par le tableau TableView
     */
    public ObservableList<Course> getCoursDisponibles(String session){
        ObservableList<Course> coursDisponibles = FXCollections.observableArrayList();
        for (Course course : this.modele.voirSession(session)) {
            coursDisponibles.add(course);
        }
        return coursDisponibles;
    }

}
