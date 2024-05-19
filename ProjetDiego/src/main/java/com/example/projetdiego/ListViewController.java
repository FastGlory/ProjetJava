package com.example.projetdiego;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListViewController {
    @FXML
    public Label selection;
    @FXML
    public ListView<String> listView;
    private Stage stage;

    public void backBouton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionPageCours.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void confirmerChoix(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {
        try {
            ArrayList<String> nomsProfesseurs = professeur.recuperationNames();
            listView.getItems().addAll(nomsProfesseurs);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectionChanged(ObservableValue<? extends String> observable, String oldVal, String newVal) {
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String selectedItemText = selectedItems.isEmpty() ? "No Selected Item" : String.join(", ", selectedItems);
        selection.setText(selectedItemText);
    }

    // Aide : https://www.youtube.com/watch?v=Z7th7RSRitw tres grandement inspirer de cela

}