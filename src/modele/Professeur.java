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

    public int getCmVolume() {
        return maRepartition.getCmVolume();
    }

    public int getTdVolume() {
        return maRepartition.getTdVolume();
    }

    public int getTpVolume() {
        return maRepartition.getTpVolume();
    }

    public int getCCVolume() {
        return maRepartition.getCCVolume();
    }

    public int getgetETVolume() {
        return maRepartition.getETVolume();
    }

    public VolumeHorraire getMaRepartition() {
        return maRepartition;
    }

}