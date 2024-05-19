package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


public class PageController {


    @FXML
    private TextArea DescriptionCours;
    @FXML
    private TextField NomCours;
    @FXML
    private TextField CodeCours;

    @FXML
    private TextField nomProfesseur;

    @FXML
    private TextField prenomProfesseur;

    @FXML
    private TextField identifiantProfesseur;

    @FXML
    private TextField SalaireProfesseur;

    @FXML
    private TextField nomEtudiant;

    @FXML
    private TextField prenomEtudiant;

    @FXML
    private TextField identifiantEtudiant;

    @FXML
    private TextField CoteRetudiant;
    @FXML
    private TextField PasswordConnexion;
    @FXML
    private TextField UserConnexion;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public PageController() throws IOException, ParseException {
    }


    public void boutonBackClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void boutonAjouterClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionPageCours.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void EnvoyerProfesseurClicked(ActionEvent event) throws JsonProcessingException {
        if(Session.isIsAdmin()==false){
            System.out.println("Vous n'avez pas les autorisation nécessaire");
        }else {

            String nom = nomProfesseur.getText();
            String prenom = prenomProfesseur.getText();
            String identifiant = identifiantProfesseur.getText();
            String salaire = SalaireProfesseur.getText();
            professeur Professeur = new professeur();

            Professeur.setNom(nom);
            Professeur.setPrenom(prenom);
            Professeur.setIdentifiant(identifiant);
            double salaireProfesseurDouble = Double.parseDouble(salaire);
            Professeur.setSalaire(salaireProfesseurDouble);

            System.out.println("Nom: " + Professeur.getNom());
            System.out.println("Prenom: " + Professeur.getPrenom());
            System.out.println("Identifiant: " + Professeur.getIdentifiant());
            System.out.println("Salaire: " + Professeur.getSalaire());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(Professeur);
            System.out.println("JSON représentant Professeur :");
            System.out.println(json);

            professeur deserializedProfesseur = objectMapper.readValue(json, professeur.class);
            System.out.println("\nObjet Professeur désérialisé :");
            System.out.println(deserializedProfesseur);

            try {
                professeur.serialize(Professeur);
                System.out.println("Professeur enregistré dans le fichier JSON.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de l'enregistrement du professeur dans le fichier JSON.");
            }
        }
    }

    public void ReinitialiserProfesseurClicked(ActionEvent event) {
        nomProfesseur.clear();
        prenomProfesseur.clear();
        identifiantProfesseur.clear();
        SalaireProfesseur.clear();
        System.out.println("Réinitalisation !");
    }

    public void EnvoyerEtudiantClicked(ActionEvent event) throws JsonProcessingException {
        if(Session.isIsAdmin() == false){
            System.out.println("Vous n'avez pas les autorisation nécessaire");
        }else {
            String nom = nomEtudiant.getText();
            String prenom = prenomEtudiant.getText();
            String identifiant = identifiantEtudiant.getText();
            String coteR = CoteRetudiant.getText();
            etudiant Etudiant = new etudiant();

            Etudiant.setNom(nom);
            Etudiant.setPrenom(prenom);
            Etudiant.setIdentifiant(identifiant);
            double salaireEtudiantDouble = Double.parseDouble(coteR);
            Etudiant.setCoteR(salaireEtudiantDouble);

            System.out.println("Nom: " + Etudiant.getNom());
            System.out.println("Prenom: " + Etudiant.getPrenom());
            System.out.println("Identifiant: " + Etudiant.getIdentifiant());
            System.out.println("Cote: " + Etudiant.getCoteR());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(Etudiant);
            System.out.println("JSON représentant Etudiant :");
            System.out.println(json);

            etudiant deserializedEtudiant = objectMapper.readValue(json, etudiant.class);
            System.out.println("\nObjet Etudiant désérialisé :");
            System.out.println(deserializedEtudiant);

            try {
                Etudiant.serialize(Etudiant);
                System.out.println("Etudiant enregistré dans le fichier JSON.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de l'enregistrement de l'étudiant dans le fichier JSON.");
            }
        }
    }

    public void ReinitialiserEtudiantClicked(ActionEvent event) {
        nomEtudiant.clear();
        prenomEtudiant.clear();
        identifiantEtudiant.clear();
        CoteRetudiant.clear();
        System.out.println("Réinitalisation !");
    }

    public void ConnexionClicked(ActionEvent actionEvent) {
        String username = UserConnexion.getText();
        String password = PasswordConnexion.getText();

        // Généré par chatgpt
        JSONObject users = new JSONObject();

        JSONObject user1 = new JSONObject();
        user1.put("NomUtilisateur", "Juba");
        user1.put("MotDePasse", "juba123");
        user1.put("AccesAdmin", false);
        users.put("Juba", user1);

        JSONObject admin = new JSONObject();
        admin.put("NomUtilisateur", "admin");
        admin.put("MotDePasse", "admin123");
        admin.put("AccesAdmin", true);
        users.put("admin", admin);
        // La logique derrière est simple : On va vérifie si le nom saisi est bien présent dans le fichier json, si oui on va récupérer les information dans un objet et on va comparer pour voir si le mot de passe et bon. Une fois que ca c'est bon on va simplement récupérer son autorisation et le set
        if (users.has(username)) {
            JSONObject userRecuper = users.getJSONObject(username);
            if (userRecuper.getString("MotDePasse").equals(password)) {
                Session.setIsAdmin(userRecuper.getBoolean("AccesAdmin"));
                if (Session.isIsAdmin()) {
                    System.out.println("Bienvenue Administrateur !");
                } else {
                    System.out.println("Bienvenue étudiant !");
                }
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect !");
            }
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect !");
        }
        System.out.println(Session.isIsAdmin());
    }

    // Aide : https://www.javatpoint.com/how-to-get-value-from-json-object-in-java-example#:~:text=getJsonObject()%20Method&text=It%20is%20used%20to%20get%20the%20(JsonObject)get(name,mapping%20for%20the%20parse%27s%20parameter.
    // Aide : https://processing.org/reference/JSONObject_getJSONObject_.html

    public void DeconnexionClicked(ActionEvent actionEvent) {
        Session.setIsAdmin(false);
        System.out.println("Déconnexion !");
        System.out.println(Session.isIsAdmin());
    }

    public void SendCours(ActionEvent actionEvent) throws JsonProcessingException {
        if(Session.isIsAdmin() == false){
            System.out.println("Vous n'avez pas les autorisation nécessaire");
        }else {

            String descriptioncours = DescriptionCours.getText();
            String nomcours =NomCours.getText();
            String Code =CodeCours.getText();
            cours Cours = new cours();

            Cours.setNom(nomcours);
            Cours.setDescription(descriptioncours);
            Cours.setCode(Code);


            System.out.println("Nom de cours: " +Cours.getNom() );
            System.out.println("Code cours: " +Cours.getCode() );
            System.out.println("Description Cours: " +Cours.getDescription() );

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(Cours);
            System.out.println("JSON représentant Etudiant :");
            System.out.println(json);

            cours deserializedCours = objectMapper.readValue(json, cours.class);
            System.out.println("\nObjet Etudiant désérialisé :");
            System.out.println(deserializedCours);

            try {
                Cours.serialize(Cours);
                System.out.println("Cours enregistré dans le fichier JSON.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de l'enregistrement du cours dans le fichier JSON.");
            }
        }
    }

    public void boutonAjouterProfesseur(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionProf.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void boutonAjouterEtudiant(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionEtudiant.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void GoToTableView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


