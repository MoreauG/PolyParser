package modele;

/**
 * Classe permettant de definir un professeur.
 * Un professeur est modelise de la sorte:
 * -Il a un nom
 * -Il donne un certains nombre d'heures de cours CM/CC par matiere
 */
public class Professeur {

    private String nom;
    private VolumeHorraire repartition;

    /**
     * Unique constructeur, permet d'instancier un professeur a l'aide de ses attributs et de creer un volume de cours correspondant
     *
     * @param nom,      le nom/prenom du professeur
     * @param cmVolume, le nombre d'heures de CM qu'il distribue
     * @param tdVolume, le nombre d'heures de TD qu'il distribue
     * @param tpVolume, le nombre d'heures de TP qu'il distribue
     * @param CCVolume, le nombre d'heures de CC qu'il distribue
     * @param ETVolume, le nombre d'heures d' ET qu'il distribue
     */
    public Professeur(String nom, int cmVolume, int tdVolume, int tpVolume, int CCVolume, int ETVolume) {

        this.nom = nom;
        repartition = new VolumeHorraire(cmVolume, tdVolume, tpVolume, CCVolume, ETVolume);
    }

    /**
     * fonction permettant d'obtenir le nom du professeur
     *
     * @return nom, le nom du professeur
     */
    public String getNom() {
        return nom;
    }

    /**
     * fonction permettant d'obtenir la repartition des heures d'un professeur
     *
     * @return repartition, la repartition des heures
     */
    public VolumeHorraire getRepartition() {
        return repartition;
    }

    /**
     * fonction retournant le nombre de types de "cours" donne par un professeur.
     * Par exemple, si nous avons un professeur qui donne des cours de CM/TD/TP, la fonction retournera 3
     *
     * @return total, le nombre de constituants
     */
    public int getVariete() {
        return repartition.getVariete();
    }


}