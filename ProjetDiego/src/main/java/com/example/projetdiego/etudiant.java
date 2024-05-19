package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
                ", CoteR=" + getCoteR() +
                '}';
    }

    public static void serialize(etudiant etudiant, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode rootNode;
        File file = new File(filePath);

        if (file.exists()) {
            rootNode = (ObjectNode) mapper.readTree(file);
        } else {
            rootNode = mapper.createObjectNode();
        }

        ObjectNode etudiantNode = mapper.valueToTree(etudiant);
        rootNode.set(etudiant.getIdentifiant(), etudiantNode);

        mapper.writeValue(file, rootNode);
    }

    public static void serialize(etudiant etudiant) throws IOException {
        serialize(etudiant, path);
    }

    public static etudiant deserialize(String filePath, String nom) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);
        JsonNode etudiantNode = rootNode.get(nom);

        if (etudiantNode == null) {
            throw new IOException("L'étudiant avec l'identifiant spécifié n'a pas été trouvé.");
        }

        return mapper.treeToValue(etudiantNode, etudiant.class);
    }

    public static etudiant deserialize(String nom) throws IOException {
        return deserialize(path, nom);
    }
}
