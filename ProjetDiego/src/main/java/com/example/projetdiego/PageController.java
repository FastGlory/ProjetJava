package com.example.projetdiego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void boutonBackClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void boutonAjouterClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionPageCours.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Fixe complet grace à cette vidéo : https://www.youtube.com/watch?v=hcM-R-YOKkQ
}
