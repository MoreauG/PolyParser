package modele;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Created by germa on 16/12/2015.
 */
public class Maquette {
    public String getNomMaquette() {
        return nomMaquette;
    }

    private String nomMaquette;
    private double nombreJourDansUneSemaine;
    private int nombreCreneauParJour;
    private int nombreDeGroupeDeTD;
    private int nombreDeGroupeDeTP;
    private int dureCreneau;
    private ArrayList<UniteEnseignement> listeUniteEnseignement = new ArrayList();

    public ArrayList<UniteEnseignement> getListeUniteEnseignement() {
        return listeUniteEnseignement;
    }

    public void addUniteEnseignement(UniteEnseignement monUe) {
        listeUniteEnseignement.add(monUe);
    }

    public Maquette(String nomMaquette) {
        this.nomMaquette = nomMaquette;
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

}
