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

    public static List<etudiant> getAllEtudiants() throws IOException {
        List<etudiant> etudiants = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);

        Iterator<String> fieldNames = rootNode.fieldNames();
        while (fieldNames.hasNext()) {
            String identifiant = fieldNames.next();
            JsonNode etudiantNode = rootNode.get(identifiant);
            etudiant etudiant = mapper.treeToValue(etudiantNode, etudiant.class);
            etudiants.add(etudiant);
        }

        return etudiants;
    }

}


// https://www.baeldung.com/jackson-json-node-tree-model (Copilot m'a proposé de légère modification, voici en plus des informations supplémentaire que je me suis basé)
// Ce code ce base aussi sur les notes de cours de Mr.Fostiné
// aide : https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/