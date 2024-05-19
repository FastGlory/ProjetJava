package com.example.projetdiego;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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

    public static ArrayList<String> recuperationNames() throws IOException {
        ArrayList<String> noms = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Lecture du fichier JSON en tant que nœud JSON
            JsonNode rootNode = objectMapper.readTree(new File(path));

            // Parcourir les nœuds JSON pour récupérer les noms des professeurs
            Iterator<String> fieldNames = rootNode.fieldNames();
            while (fieldNames.hasNext()) {
                String nom = fieldNames.next();
                noms.add(nom);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return noms;
    }
    // aide : https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    public static List<professeur> getAllProfesseurs() throws IOException {
        List<professeur> Professeur = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);

        if (!file.exists()) {
            throw new IOException("Le fichier JSON n'existe pas.");
        }

        JsonNode rootNode = mapper.readTree(file);

        Iterator<String> fieldNames = rootNode.fieldNames();
        while (fieldNames.hasNext()) {
            String identifiant = fieldNames.next();
            JsonNode professeurNode = rootNode.get(identifiant);
            professeur professeur = mapper.treeToValue(professeurNode, professeur.class);
            Professeur.add(professeur);
        }

        return Professeur;
    }


}

// https://www.baeldung.com/jackson-json-node-tree-model (Copilot m'a proposé cette solution, voici des informations supplémentaire)