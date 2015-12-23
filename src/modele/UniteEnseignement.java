package modele;

import java.util.ArrayList;

/**
 * Classe permettant de definir une Unite d'Enseignement
 * une UE (ou module) possede:
 * -un nom
 * -une liste d'enseignement
 */
public class UniteEnseignement {

    private String nom;
    private ArrayList<Enseignement> enseignementList = new ArrayList();

    /**
     * unique Constructeur, permet d'instancier un module a l'aide de son nom
     *
     * @param nom, le nom de l'UE
     */
    public UniteEnseignement(String nom) {
        this.nom = nom;

    }

    /**
     * Fonction permettant d'obtenir le nom de l'unite d'enseignement
     *
     * @return nom, le nom de l'UE
     */
    public String getNom() {
        return nom;
    }

    /**
     * fonction permettant d'ajouter un enseignement a l'UE
     *
     * @param nouvelEnseignement, l'enseignement a rajouter a notre UE
     */
    public void addEnseignement(Enseignement nouvelEnseignement) {
        enseignementList.add(nouvelEnseignement);
    }

    /**
     * fonction permettant d'obtenir la liste des enseignements au sein d'un UE
     *
     * @return enseignementList, la liste des matieres de l'UE
     */
    public ArrayList<Enseignement> getEnseignementList() {
        return enseignementList;
    }

    /**
     * fonction retournant le nombre de types de "cours" donne au sein d'une UE.
     * s'il y'a 2CC + 2 TP + 3CM, notre fonction retourna 7
     *
     * @return total, le nombre de constituants
     */
    public int getVariete() {
        int total = 0;
        for (int icpt = 0; icpt < enseignementList.size(); icpt++) {
            total += enseignementList.get(icpt).getVariete();
        }
        return total;
    }

}
