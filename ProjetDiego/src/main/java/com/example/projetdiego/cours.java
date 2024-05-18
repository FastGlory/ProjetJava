package com.example.projetdiego;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class cours {
    private String Nom;
    private String Code;
    private String Description;
    private professeur Professeur ;
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

    public String toString() {
        return "Etudiant{" +
                "Nom='" + getNom() + '\'' +
                ", Code='" + getCode() + '\'' +
                ", Professeur='" + getProfesseur() + '\'' +
                ", description=" + getDescription() +
                '}';
    }

    public static void serialize(cours Cours, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), Cours);
    }

    public static void serialize(cours Cours) throws IOException {
        serialize(Cours, path);
    }

    public static cours deserialize(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), cours.class);
    }

    public static cours deserialize() throws IOException {
        return deserialize(path);
    }
}
