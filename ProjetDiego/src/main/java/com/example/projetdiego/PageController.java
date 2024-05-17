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


public class PageController {

    @FXML
    private TextField nomProfesseur;

    @FXML
    private TextField prenomProfesseur;

    @FXML
    private TextField identifiantProfesseur;

    @FXML
    private TextField SalaireProfesseur;

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

    public void EnvoyerProfesseurClicked(ActionEvent event) {
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

    }

    public void ReinitialiserProfesseurClicked(ActionEvent event) {
        nomProfesseur.clear();
        prenomProfesseur.clear();
        identifiantProfesseur.clear();
        SalaireProfesseur.clear();
        System.out.println("Réinitalisation !");
    }
}