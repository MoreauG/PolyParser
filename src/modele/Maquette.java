package modele;

import java.util.ArrayList;

/**
 * Created by germa on 16/12/2015.
 */
public class Maquette {

    private String nomMaquette;
    private double nombreJourDansUneSemaine;
    private int nombreCreneauParJour;
    private int nombreDeGroupeDeTD;
    private int nombreDeGroupeDeTP;
    private int dureCreneau;
    private ArrayList<UniteEnseignement> listeUniteEnseignement = new ArrayList();
    private Temporalite planning;

    public Maquette(String nomMaquette, double nombreJourDansUneSemaine, int nombreCreneauParJour, int nombreDeGroupeDeTD, int nombreDeGroupeDeTP, int dureCreneau) {
        this.nomMaquette = nomMaquette;
        this.nombreJourDansUneSemaine = nombreJourDansUneSemaine;
        this.nombreCreneauParJour = nombreCreneauParJour;
        this.nombreDeGroupeDeTD = nombreDeGroupeDeTD;
        this.nombreDeGroupeDeTP = nombreDeGroupeDeTP;
        this.dureCreneau = dureCreneau;
    }

    public void setPlanning(Temporalite monPlanning) {
        planning=monPlanning;
    }

    public ArrayList<UniteEnseignement> getListeUniteEnseignement() {
        return listeUniteEnseignement;
    }

    public void addUniteEnseignement(UniteEnseignement monUe) {
        listeUniteEnseignement.add(monUe);
    }

    public int getMaxSemaine() {
        return (int) (nombreJourDansUneSemaine * nombreCreneauParJour);
    }

    public void setNombreJourDansUneSemaine(double nombreJourDansUneSemaine) {
        this.nombreJourDansUneSemaine = nombreJourDansUneSemaine;
    }

    public void setNombreCreneauParJour(int nombreCreneauParJour) {
        this.nombreCreneauParJour = nombreCreneauParJour;
    }

    public String getNomMaquette() {
        return nomMaquette;
    }

    public Temporalite getPlanning() {
        return planning;
    }


}
