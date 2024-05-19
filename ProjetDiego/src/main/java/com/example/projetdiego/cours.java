package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class cours {
    private String Nom;
    private String Code;
    private String Description;
    private professeur Professeur;
    private static final String path = "src/main/java/com/example/projetdiego/Cours.json";

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

    public professeur getProfesseur() {
        return Professeur;
    }

    public void setProfesseur(professeur professeur) {
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
}
