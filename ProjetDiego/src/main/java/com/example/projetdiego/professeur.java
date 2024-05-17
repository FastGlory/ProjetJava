package com.example.projetdiego;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class professeur extends personne{
    private double salaire;
    private static final String path = "src/main/java/com/example/projetdiego/professeur.json";
    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "Nom='" + getNom() + '\'' +
                ", Prénom='" + getPrenom() + '\'' +
                ", Identifiant='" + getIdentifiant() + '\'' +
                ", Salaire=" + getSalaire() +
                '}';
    }

    public static void serialize(professeur Professeur, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), Professeur);
    }

    //  Ici je rajoute ca parce que
    public static void serialize(professeur Professeur) throws IOException {
        serialize(Professeur, path);
    }

    public static professeur deserialize(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.readValue(new File(filePath), professeur.class);
    }

    public static professeur deserialize() throws IOException {
        return deserialize(path);
    }


}
