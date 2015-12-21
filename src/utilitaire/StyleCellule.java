package utilitaire;

/**
 * Classe representant un style de cellule (ou de plage de cellules) au sein d'une feuille de calcul
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
    private int couleurPolice;

    /**
     * Constructeur permettant la creation d'un style de cellule
     *
     * @param bordure,       le type de bordure souhaitee
     * @param fond,          le couleur du fond souhaitee
     * @param gras,          si l'on ecris en gras ou non
     * @param couleurPolice, la couleur de la police d'ecriture
     * @throws IllegalArgumentException, si on essaye de cree notre objet avec un/des parametres non definis
     */
    public StyleCellule(int bordure, int fond, int gras, int couleurPolice) throws IllegalArgumentException {

        if (bordure > BORDURE_DOUBLE || bordure < BORDURE_SANS) {
            throw new IllegalArgumentException("constante non existante");
        }
        this.bordure = bordure;


        if (fond > FOND_BLEU_GRIS || fond < FOND_SANS) {
            throw new IllegalArgumentException("constante non existante");
        }
        this.fond = fond;


        if (gras > GRAS_SANS || gras < GRAS_AVEC) {
            throw new IllegalArgumentException("constante non existante");
        }
        this.gras = gras;

        if (couleurPolice > POLICE_ROUGE || couleurPolice < POLICE_NOIRE) {
            throw new IllegalArgumentException("constante non existante");
        }
        this.couleurPolice = couleurPolice;
    }

    /**
     * constructeur par defaut, permet de definir un style de base
     */
    public StyleCellule() {
        bordure = BORDURE_SIMPLE;
        fond = FOND_SANS;
        gras = GRAS_SANS;
        couleurPolice = POLICE_NOIRE;
    }

    /**
     * fonction retourant le type de bordure du style
     *
     * @return bordure, le type de bordure
     */
    public int getBordure() {
        return bordure;
    }

    /**
     * fonction retourant le type de fond du style
     *
     * @return fond, le type de fond
     */
    public int getFond() {
        return fond;
    }

    /**
     * fonction retourant le type de police: gras ou non, du style
     *
     * @return gras, le type de police
     */
    public int getGras() {
        return gras;
    }

    /**
     * fonction retourant le type de couleur de la police du style
     *
     * @return couleurPolice, le type de couleur de la police
     */
    public int getCouleurPolice() {
        return couleurPolice;
    }

    /**
     * fonction permettant de definir la couleur de la police d'ecriture
     *
     * @param couleurPolice, la couleur souhaitee
     * @throws IllegalArgumentException, si le parametre passe ne correspond pas a une constante definie
     */
    public void setCouleurPolice(int couleurPolice) throws IllegalArgumentException {
        if (couleurPolice > POLICE_ROUGE || couleurPolice < POLICE_NOIRE) {
            throw new IllegalArgumentException("constante non existante");
        }
        this.couleurPolice = couleurPolice;
    }
}
