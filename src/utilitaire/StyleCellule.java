package utilitaire;

/**
 * Created by germa on 12/11/2015.
 */
public class StyleCellule {
    public static final int BORDURE_SANS = 0;
    public static final int BORDURE_SIMPLE = 1;
    public static final int BORDURE_DOUBLE = 2;

    public static final int FOND_SANS = 10;
    public static final int FOND_VERT = 11;
    public static final int FOND_ROUGE = 12;
    public static final int FOND_JAUNE = 13;
    public static final int FOND_BLEU = 14;
    public static final int FOND_TURQUOISE = 15;
    public static final int FOND_BEIGE = 16;
    public static final int FOND_GRIS_CLAIR = 17;
    public static final int FOND_GRIS_FONCE = 18;
    public static final int FOND_BLEU_GRIS = 19;


    public static final int GRAS_AVEC = 20;
    public static final int GRAS_SANS = 21;

    public static final int POLICE_NOIRE = 30;
    public static final int POLICE_VERTE = 31;
    public static final int POLICE_ROUGE = 32;

    private int bordure;
    private int fond;
    private int gras;

    private int ecriture;

    public StyleCellule(int bordure, int fond, int gras, int ecriture) {
        this.bordure = bordure;
        this.fond = fond;
        this.gras = gras;
        this.ecriture = ecriture;
    }

    public StyleCellule() {
        bordure = BORDURE_SIMPLE;
        fond = FOND_SANS;
        gras = GRAS_SANS;
        ecriture = POLICE_NOIRE;
    }

    public int getBordure() {
        return bordure;
    }

    public int getFond() {
        return fond;
    }

    public int getGras() {
        return gras;
    }

    public int getEcriture() {
        return ecriture;
    }

    public void setEcriture(int ecriture) {
        this.ecriture = ecriture;
    }
}
