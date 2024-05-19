package com.example.projetdiego;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class PageController {

    @FXML private TextArea DescriptionCours;
    @FXML private TextField NomCours;
    @FXML private TextField CodeCours;
    @FXML private TextField nomProfesseur;
    @FXML private TextField prenomProfesseur;
    @FXML private TextField identifiantProfesseur;
    @FXML private TextField SalaireProfesseur;
    @FXML private TextField nomEtudiant;
    @FXML private TextField prenomEtudiant;
    @FXML private TextField identifiantEtudiant;
    @FXML private TextField CoteRetudiant;
    @FXML private TextField PasswordConnexion;
    @FXML private TextField UserConnexion;

    @FXML private TreeTableView<cours> treeTableViewCours;
    @FXML private TreeTableColumn<cours, String> nomCoursColumn;
    @FXML private TreeTableColumn<cours, String> codeCoursColumn;
    @FXML private TreeTableColumn<cours, String> descriptionCoursColumn;
    @FXML private TreeTableColumn<cours, String> professeurCoursColumn;

    @FXML public TreeTableView<etudiant> treeTableViewEtudiant;
    @FXML public TreeTableColumn<etudiant, String> nomEtudiantColumn;
    @FXML public TreeTableColumn<etudiant, String> prenomEtudiantColumn;
    @FXML public TreeTableColumn<etudiant, String> identifiantEtudiantColumn;
    @FXML public TreeTableColumn<etudiant, String> coteREtudiantColumn;

    @FXML public TreeTableView<professeur> treeTableViewProfesseur;
    @FXML public TreeTableColumn<professeur, String> nomProfesseurColumn;
    @FXML public TreeTableColumn<professeur, String> prenomProfesseurColumn;
    @FXML public TreeTableColumn<professeur, String> identifiantProfesseurColumn;
    @FXML public TreeTableColumn<professeur, String> salaireProfesseurColumn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<cours> coursList = FXCollections.observableArrayList();
    private ObservableList<etudiant> EtudiantList = FXCollections.observableArrayList();
    private ObservableList<professeur> ProfesseurList = FXCollections.observableArrayList();

    public PageController() throws IOException, ParseException {}

    @FXML
    private void initialize() {
        // J'ai demandé à chatgpt de me fournir ce code afin de vérifier le null est avant ou après avoir cliquer sur ajouter
        System.out.println("nomCoursColumn: " + (nomCoursColumn != null));
        System.out.println("codeCoursColumn: " + (codeCoursColumn != null));
        System.out.println("descriptionCoursColumn: " + (descriptionCoursColumn != null));
        System.out.println("professeurCoursColumn: " + (professeurCoursColumn != null));
        System.out.println("nomEtudiantColumn: " + (nomEtudiantColumn != null));
        System.out.println("prenomEtudiantColumn: " + (prenomEtudiantColumn != null));
        System.out.println("identifiantEtudiantColumn: " + (identifiantEtudiantColumn != null));
        System.out.println("coteREtudiantColumn: " + (coteREtudiantColumn != null));
        System.out.println("nomProfesseurColumn: " + (nomProfesseurColumn != null));
        System.out.println("prenomProfesseurColumn: " + (prenomProfesseurColumn != null));
        System.out.println("identifiantProfesseurColumn: " + (identifiantProfesseurColumn != null));
        System.out.println("salaireProfesseurColumn: " + (salaireProfesseurColumn != null));

        if (!Session.isIsAdmin()) {
            initializeColumns();
            initializeTreeTableViews();
        }
    }

    private void initializeColumns(){
        if (nomCoursColumn != null) nomCoursColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        if (codeCoursColumn != null) codeCoursColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        if (descriptionCoursColumn != null) descriptionCoursColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        if (professeurCoursColumn != null) professeurCoursColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("professeur"));
        if (nomEtudiantColumn != null) nomEtudiantColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        if (prenomEtudiantColumn != null) prenomEtudiantColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        if (identifiantEtudiantColumn != null) identifiantEtudiantColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("identifiant"));
        if (coteREtudiantColumn != null) coteREtudiantColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("coteR"));
        if (nomProfesseurColumn != null) nomProfesseurColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        if (prenomProfesseurColumn != null) prenomProfesseurColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        if (identifiantProfesseurColumn != null) identifiantProfesseurColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("identifiant"));
        if (salaireProfesseurColumn != null) salaireProfesseurColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("salaire"));
    }

    private void initializeTreeTableViews() {
        try {
            List<cours> coursFromJson = cours.getAllCours("src/main/java/com/example/projetdiego/Cours.json");
            coursList.addAll(coursFromJson);
            TreeItem<cours> rootItemCours = new TreeItem<>(new cours());
            for (cours cours : coursList) {
                TreeItem<cours> item = new TreeItem<>(cours);
                rootItemCours.getChildren().add(item);
            }
            treeTableViewCours.setRoot(rootItemCours);
            treeTableViewCours.setShowRoot(false);

            List<etudiant> etudiantFromJson = etudiant.getAllEtudiants();
            EtudiantList.addAll(etudiantFromJson);
            TreeItem<etudiant> rootItemEtudiant = new TreeItem<>(new etudiant());
            for (etudiant etudiant : EtudiantList) {
                TreeItem<etudiant> item = new TreeItem<>(etudiant);
                rootItemEtudiant.getChildren().add(item);
            }
            treeTableViewEtudiant.setRoot(rootItemEtudiant);
            treeTableViewEtudiant.setShowRoot(false);

            List<professeur> professeurFromJson = professeur.getAllProfesseurs();
            ProfesseurList.addAll(professeurFromJson);
            TreeItem<professeur> rootItemProfesseur = new TreeItem<>(new professeur());
            for (professeur professeur : ProfesseurList) {
                TreeItem<professeur> item = new TreeItem<>(professeur);
                rootItemProfesseur.getChildren().add(item);
            }
            treeTableViewProfesseur.setRoot(rootItemProfesseur);
            treeTableViewProfesseur.setShowRoot(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void boutonBackClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        initializeColumns();
        initializeTreeTableViews();
    }

    public void boutonAjouterClicked(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InscriptionPageCours.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void EnvoyerProfesseurClicked(ActionEvent event) throws JsonProcessingException {
        if (!Session.isIsAdmin()) {
            System.out.println("Vous n'avez pas les autorisation nécessaire");
            return;
        }
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
        if (!Session.isIsAdmin()) {
            System.out.println("Vous n'avez pas les autorisation nécessaire");
            return;
        }
        String nom = nomEtudiant.getText();
        String prenom = prenomEtudiant.getText();
        String identifiant = identifiantEtudiant.getText();
        String coteR = CoteRetudiant.getText();
        etudiant Etudiant = new etudiant();

        Etudiant.setNom(nom);
        Etudiant.setPrenom(prenom);
        Etudiant.setIdentifiant(identifiant);
        double coteRDouble = Double.parseDouble(coteR);
        Etudiant.setCoteR(coteRDouble);

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

    public void DeconnexionClicked(ActionEvent actionEvent) {
        Session.setIsAdmin(false);
        System.out.println("Déconnexion !");
        System.out.println(Session.isIsAdmin());
    }

    public void SendCours(ActionEvent actionEvent) throws JsonProcessingException {
        if (!Session.isIsAdmin()) {
            System.out.println("Vous n'avez pas les autorisation nécessaire");
            return;
        }

        String descriptioncours = DescriptionCours.getText();
        String nomcours = NomCours.getText();
        String code = CodeCours.getText();
        cours Cours = new cours();

        Cours.setNom(nomcours);
        Cours.setDescription(descriptioncours);
        Cours.setCode(code);

        System.out.println("Nom de cours: " + Cours.getNom());
        System.out.println("Code cours: " + Cours.getCode());
        System.out.println("Description Cours: " + Cours.getDescription());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Cours);
        System.out.println("JSON représentant Etudiant :");
        System.out.println(json);

        cours deserializedCours = objectMapper.readValue(json, cours.class);
        System.out.println("\nObjet Etudiant désérialisé :");
        System.out.println(deserializedCours);

        try {
            cours.serialize(Cours);
            System.out.println("Cours enregistré dans le fichier JSON.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement du cours dans le fichier JSON.");
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
