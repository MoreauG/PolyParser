package modele;

/**
 * Created by germa on 16/12/2015.
 */
public class Professeur {

    private String nom;
    private VolumeHorraire maRepartition;

    public Professeur(String nom, int cmVolume, int tdVolume, int tpVolume, int CCVolume, int ETVolume) {

        this.nom = nom;
        maRepartition = new VolumeHorraire(cmVolume, tdVolume, tpVolume, CCVolume, ETVolume);
    }

    public String getNom() {
        return nom;
    }

    public int getVariete() {
        return maRepartition.getVariete();
    }

    public VolumeHorraire getMaRepartition() {
        return maRepartition;
    }

}