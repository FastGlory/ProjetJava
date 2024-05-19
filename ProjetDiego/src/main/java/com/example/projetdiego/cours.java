package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cours {
    private String Nom;
    private String Code;
    private String Description;
    private String Professeur; // Change this to String to match your FXML

    private static final String path = "src/main/java/com/example/projetdiego/Cours.json";

    // Getters and Setters
    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProfesseur() {
        return Professeur;
    }

    public void setProfesseur(String professeur) {
        Professeur = professeur;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "Nom='" + Nom + '\'' +
                ", Code='" + Code + '\'' +
                ", Description='" + Description + '\'' +
                ", Professeur='" + Professeur + '\'' +
                '}';
    }

    // Serialization method
    public static void serialize(cours cours, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode rootNode;
        File file = new File(filePath);

        if (file.exists()) {
            rootNode = (ObjectNode) mapper.readTree(file);
        } else {
            rootNode = mapper.createObjectNode();
        }

        ObjectNode coursNode = mapper.valueToTree(cours);
        rootNode.set(cours.getNom(), coursNode);

        mapper.writeValue(file, rootNode);
    }

    public static void serialize(cours cours) throws IOException {
        serialize(cours, path);
    }

    // Deserialization method
    public static cours deserialize(String filePath, String nom) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);
        JsonNode coursNode = rootNode.get(nom);

        if (coursNode == null) {
            throw new IOException("Aucun cours trouvé dans le fichier JSON.");
        }

        return mapper.treeToValue(coursNode, cours.class);
    }

    public static cours deserialize(String identifiant) throws IOException {
        return deserialize(path, identifiant);
    }

    // Load all courses from JSON
    public static List<cours> getAllCours(String filePath) throws IOException {
        List<cours> coursList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);
        Iterator<String> fieldNames = rootNode.fieldNames();
        while (fieldNames.hasNext()) {
            String nom = fieldNames.next();
            JsonNode coursNode = rootNode.get(nom);
            cours cours = mapper.treeToValue(coursNode, cours.class);
            coursList.add(cours);
        }

        return coursList;
    }
}
