package com.example.projetdiego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PageController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAccueil.fxml"));
            Parent pageAccueil = loader.load();
            Scene scene = new Scene(pageAccueil);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boutonAjouterClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionPageCours.fxml"));
            Parent pageInscription = loader.load();
            Scene scene = new Scene(pageInscription);
            stage.setScene(scene);
            System.out.println("Cliqué ! Vous avez changé de page...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void boutonBackClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAccueil.fxml"));
            Parent pageAccueil = loader.load();
            Scene scene = new Scene(pageAccueil);
            stage.setScene(scene);
            System.out.println("Cliqué ! Vous avez changé de page...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
