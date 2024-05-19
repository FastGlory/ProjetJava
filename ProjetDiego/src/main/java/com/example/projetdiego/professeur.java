package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

        ObjectNode rootNode;
        File file = new File(filePath);

        if (file.exists()) {
            rootNode = (ObjectNode) mapper.readTree(file);
        } else {
            rootNode = mapper.createObjectNode();
        }

        ObjectNode professeurNode = mapper.valueToTree(Professeur);
        rootNode.set(Professeur.getNom(), professeurNode);

        mapper.writeValue(file, rootNode);
    }

    public static void serialize(professeur Professeur) throws IOException {
        serialize(Professeur, path);
    }

    public static professeur deserialize(String filePath, String nom) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);
        JsonNode professeurNode = rootNode.get(nom);

        if (professeurNode == null) {
            throw new IOException("Le professeur avec l'identifiant spécifié n'a pas été trouvé.");
        }

        return mapper.treeToValue(professeurNode, professeur.class);
    }

    public static professeur deserialize(String identifiant) throws IOException {
        return deserialize(path, identifiant);
    }
}

// https://www.baeldung.com/jackson-json-node-tree-model (Copilot m'a proposé cette solution, voici des informations supplémentaire)