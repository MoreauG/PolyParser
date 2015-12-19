package modele;

import java.util.ArrayList;

/**
 * Created by germa on 16/12/2015.
 */
public class Enseignement {

    private int projetVolume;
    private String nomEnseignement;
    private ArrayList<Professeur> reparitionProfesseur = new ArrayList();


    public Enseignement(int projetVolume, String nomEnseignement, Professeur monProfesseur) {
        this.projetVolume = projetVolume;
        this.nomEnseignement = nomEnseignement;
        reparitionProfesseur.add(monProfesseur);
    }

    public ArrayList<Professeur> getReparitionProfesseur() {
        return reparitionProfesseur;
    }

    public void ajoutProfesseur(Professeur monProfesseur) {
        reparitionProfesseur.add(monProfesseur);
    }

    public String getNomEnseignement() {
        return nomEnseignement;
    }

    public int getProjetVolume() {
        return projetVolume;
    }

    public int getVariete() {
        int total = 0;
        for (int icpt = 0; icpt < reparitionProfesseur.size(); icpt++) {
            total += reparitionProfesseur.get(icpt).getVariete();
        }
        return total;
    }
}
