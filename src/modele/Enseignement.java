package modele;

import java.util.ArrayList;

/**
 * Classe permettant de definir un enseignement, un enseignement est caracterise par:
 * -une liste de professeur (qui assurent tous un certains volume de cours)
 * -un volume de projet
 * -un nom
 */
public class Enseignement {

    private int projetVolume;
    private String nom;
    private ArrayList<Professeur> listeProfesseur = new ArrayList();

    /**
     * Constructeur unique, permet de definir un enseignement a l'aide de ses constituants
     *
     * @param projetVolume,    temps de projet requis pour l'enseignement
     * @param nom, nom de l'enseignement
     * @param monProfesseur,   un enseignement est construit a l'aide d'un unique professeur, il est possible d'en ajouter d'autre par la suite
     */
    public Enseignement(int projetVolume, String nom, Professeur monProfesseur) {
        this.projetVolume = projetVolume;
        this.nom = nom;
        listeProfesseur.add(monProfesseur);
    }

    /**
     * fonction permettant d'obtenir la liste des professeurs intervenant dans un enseignement
     *
     * @return listeProfesseur, la liste des professeurs
     */
    public ArrayList<Professeur> getListeProfesseur() {
        return listeProfesseur;
    }

    /**
     * fonction permettant d'integrer un nouveau professeur au sein d'un enseignement
     *
     * @param professeurAjoute, le professeur a integrer dans l'enseignement
     */
    public void ajoutProfesseur(Professeur professeurAjoute) {
        listeProfesseur.add(professeurAjoute);
    }

    /**
     * fonction permettant de recuperer le nom d'un enseignement
     *
     * @return nom, le nom de l'enseignement
     */
    public String getNom() {
        return nom;
    }

    /**
     * fonction permettant de recuperer le nombre d'heure de projet d'un enseignement
     *
     * @return projetVolume, le nombre d'heures de projet de l'enseignement
     */
    public int getProjetVolume() {
        return projetVolume;
    }

    /**
     * fonction retournant le nombre de types de "cours" constituant un enseignement.
     * Par exemple, si nous avons deux enseignements A et B, constituee respectivement de CM,TD,ET et CM,TP,CC: notre fonction retournera 6
     *
     * @return total, le nombre de constituants de mon enseignement (CF exemple
     */
    public int getVariete() {
        int total = 0;
        for (int icpt = 0; icpt < listeProfesseur.size(); icpt++) {
            total += listeProfesseur.get(icpt).getVariete();
        }
        return total;
    }
}
