package com.example.projetdiego;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class etudiant extends personne {
    private double CoteR;
    private static final String path = "src/main/java/com/example/projetdiego/etudiant.json";
    public double getCoteR() {
        return CoteR;
    }

    public void setCoteR(double coteR) {
        CoteR = coteR;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "Nom='" + getNom() + '\'' +
                ", Prénom='" + getPrenom() + '\'' +
                ", Identifiant='" + getIdentifiant() + '\'' +
                ", Salaire=" + getCoteR() +
                '}';
    }

    public static void serialize(etudiant Etudiant, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), Etudiant);
    }

    public static void serialize(etudiant etudiant) throws IOException {
        serialize(etudiant, path);
    }

    public static etudiant deserialize(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), etudiant.class);
    }

    public static etudiant deserialize() throws IOException {
        return deserialize(path);
    }
}

