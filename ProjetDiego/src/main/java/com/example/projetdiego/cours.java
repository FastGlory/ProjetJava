package com.example.projetdiego;

public class cours {
    private String Nom;
    private String Code;
    private String Description;
    private professeur Professeur ;

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
}
