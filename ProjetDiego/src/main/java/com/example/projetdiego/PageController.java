package com.example.projetdiego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PageController {

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

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void ReinitialiserProfesseurClicked(ActionEvent event) {
        nomProfesseur.clear();
        prenomProfesseur.clear();
        identifiantProfesseur.clear();
        SalaireProfesseur.clear();
        System.out.println("Réinitalisation !");
    }

    public void EnvoyerEtudiantClicked(ActionEvent event) throws JsonProcessingException {
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
        System.out.println("Salaire: " + Etudiant.getCoteR());

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

    public void ReinitialiserEtudiantClicked(ActionEvent event) {
        nomEtudiant.clear();
        prenomEtudiant.clear();
        identifiantEtudiant.clear();
        CoteRetudiant.clear();
        System.out.println("Réinitalisation !");
    }
}