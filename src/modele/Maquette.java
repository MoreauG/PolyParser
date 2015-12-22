package modele;

import java.util.ArrayList;

/**
 * Classe permettant de definir une maquette.
 * Il existe 2 maquettes par annee: une par semestre.
 * une maquette est caracterise par:
 * -un nom
 * -un nombre de jour travaille dans une semaine
 * -un nombre de creneau disponible par jour
 * -la duree d'un creneau
 * -un nombre de groupe de TD/DP
 * -une liste d'unite d'enseignement (UE ou Module)
 * -un planning contenant les periodes de cours et de vacances
 */
public class Maquette {

    private String nom;
    private double nombreJourDansUneSemaine;
    private int nombreCreneauParJour;
    private int dureCreneau;
    private int nombreDeGroupeDeTD;
    private int nombreDeGroupeDeTP;
    private ArrayList<UniteEnseignement> listeUniteEnseignement = new ArrayList();
    private Temporalite planning;

    /**
     * Unique constructeur, permet de definir une maquette a l'aide des parametres
     *
     * @param nom,                      le nom de la maquette
     * @param nombreJourDansUneSemaine, le nombre de jours travailles dans une semaine
     * @param nombreCreneauParJour,     le nombre de creneau disponible dans une journee
     * @param nombreDeGroupeDeTD,       le nombre de groupe de TD pour un semestre
     * @param nombreDeGroupeDeTP,       le nombre de groupe de TP pour un semestre
     * @param dureCreneau,              la duree d'un creneau de cours
     */
    public Maquette(String nom, double nombreJourDansUneSemaine, int nombreCreneauParJour, int nombreDeGroupeDeTD, int nombreDeGroupeDeTP, int dureCreneau) {
        this.nom = nom;
        this.nombreJourDansUneSemaine = nombreJourDansUneSemaine;
        this.nombreCreneauParJour = nombreCreneauParJour;
        this.nombreDeGroupeDeTD = nombreDeGroupeDeTD;
        this.nombreDeGroupeDeTP = nombreDeGroupeDeTP;
        this.dureCreneau = dureCreneau;
    }

    /**
     * Fonction permettant de definir le planning d'un semestre
     *
     * @param planning, le planning du semestre
     */
    public void setPlanning(Temporalite planning) {
        this.planning = planning;
    }

    /**
     * Fonction permettant de recuperer le planning d'un semestre
     *
     * @return planning, le planning du semestre
     */
    public Temporalite getPlanning() {
        return planning;
    }

    /**
     * Fonction permettant de recuperer la liste des UE (ou modules) au sein d'un maquette
     *
     * @return listeUniteEnseignement, la liste des modules
     */
    public ArrayList<UniteEnseignement> getListeUniteEnseignement() {
        return listeUniteEnseignement;
    }

    /**
     * Fonction permettant d'ajouter une UE a notre liste d'UE
     *
     * @param nouvelleUE, l'UE a ajouter
     */
    public void addUniteEnseignement(UniteEnseignement nouvelleUE) {
        listeUniteEnseignement.add(nouvelleUE);
    }

    /**
     * Fonction permettant de recuperer le nom d'une maquette
     *
     * @return nom, le nom de la maquette
     */
    public String getNom() {
        return nom;
    }

    /**
     * fonction permettant d'obtenir le nombre de creneau disponible au sein d'une semaine
     *
     * @return nbCreneauDisponible, le nombre de creneau
     */
    public int getNombreCreneauDispoSemaine() {
        return (int) (nombreJourDansUneSemaine * nombreCreneauParJour);
    }

    /**
     * Fonction permettant de recuperer la duree d'un creneau
     *
     * @return dureCreneau, la duree d'un creneau
     */
    public int getDureCreneau() {
        return dureCreneau;
    }

}
