package utilitaire;

/**
 * Classe permettant de definir un carre a l'aide de 4 coordonnes
 */
public class MatriceRendu {
    private CoordonneesCelulle coinHautGauche;
    private CoordonneesCelulle coinHautDroit;
    private CoordonneesCelulle coinBasGauche;
    private CoordonneesCelulle coinBasDroit;

    /**
     * Constructeur permettant la definition d'un carre
     *
     * @param coinHautGauche, le coin superieur gauche
     * @param coinHautDroit,  le coin superieur droit
     * @param coinBasGauche,  le coin inferieur gauche
     * @param coinBasDroit,   le coin inferieur droit
     * @throws IllegalArgumentException si les coordonnes donnees sont incoherentes
     */
    public MatriceRendu(CoordonneesCelulle coinHautGauche, CoordonneesCelulle coinHautDroit, CoordonneesCelulle coinBasGauche, CoordonneesCelulle coinBasDroit) throws IllegalArgumentException {

        if (coinHautGauche.getY() + 1 != coinHautDroit.getY() + 1) {
            throw new IllegalArgumentException("erreur: les ordonnes des coins superieurs ne correspondent pas");
        }

        if (coinBasGauche.getY() + 1 != coinBasDroit.getY() + 1) {
            throw new IllegalArgumentException("erreur: les ordonnes des coins inferieurs ne correspondent pas");
        }

        if (coinHautGauche.getX() + 1 != coinBasGauche.getX() + 1) {
            throw new IllegalArgumentException("erreur: les abscisses du cote gauche ne correspondent pas");
        }

        if (coinHautDroit.getX() + 1 != coinBasDroit.getX() + 1) {
            throw new IllegalArgumentException("erreur: les abscisses du cote droit ne correspondent pas");
        }

        if (coinHautGauche.getX() + 1 >= coinHautDroit.getX() + 1) {
            throw new IllegalArgumentException("incoherence: Sommet gauche et droit inverse");
        }

        if (coinHautGauche.getY() + 1 >= coinBasGauche.getY() + 1) {
            throw new IllegalArgumentException("incoherence: Sommet bas et haut inverse");
        }

        if (coinHautGauche.getY() + 1 != coinHautDroit.getY() + 1) {
            throw new IllegalArgumentException("incoherence: ordonnee non concordant pour les coins superieurs");
        }

        if (coinBasGauche.getY() + 1 != getCoinBasDroit().getY() + 1) {
            throw new IllegalArgumentException("incoherence: ordonnee non concordant pour les coins inferieurs");
        }

        if (coinHautGauche.getX() + 1 != coinBasGauche.getX() + 1) {
            throw new IllegalArgumentException("incoherence: abscisse non concordant pour les coins gauches");
        }

        if (coinHautDroit.getX() + 1 != coinBasDroit.getX() + 1) {
            throw new IllegalArgumentException("incoherence: abscisse non concordant pour les coins droits");
        }
        this.coinHautGauche = coinHautGauche;
        this.coinHautDroit = coinHautDroit;
        this.coinBasGauche = coinBasGauche;
        this.coinBasDroit = coinBasDroit;
    }

    /**
     * Constructeur par defaut, sans action
     */
    public MatriceRendu() {

    }

    /**
     * fonction permettant d'obtenir le coin superieur gauche du carre
     *
     * @return les coordonnes du coin superieur gauche
     * @throws NullPointerException si les coordonnes n'ont pas etes initialisees
     */
    public CoordonneesCelulle getCoinHautGauche() throws NullPointerException {

        if (coinHautGauche == null) {
            throw new NullPointerException("le coin haut gauche n'a pas ete initalise");
        }
        return coinHautGauche;
    }

    /**
     * fonction permettant de definir le coin superieur gauche du carre
     *
     * @param coinHautGauche, le coin superieur gauche
     * @throws IllegalArgumentException si on detecte une incoherence
     */
    public void setCoinHautGauche(CoordonneesCelulle coinHautGauche) throws IllegalArgumentException {

        if (coinHautDroit != null) {
            if (coinHautDroit.getX() + 1 <= coinHautGauche.getX() + 1) {
                throw new IllegalArgumentException("Abscisse invalide");
            }
            if (coinHautGauche.getY() + 1 != coinHautDroit.getY() + 1) {
                throw new IllegalArgumentException("difference d'ordonnee interdite");
            }

        }
        if (coinBasGauche != null) {
            if (coinHautGauche.getY() + 1 >= coinBasGauche.getY() + 1) {
                throw new IllegalArgumentException("Ordonnee invalide");
            }
            if (coinHautGauche.getX() + 1 != coinBasGauche.getX() + 1) {
                throw new IllegalArgumentException("difference d'abscisse interdite");
            }
        }
        this.coinHautGauche = coinHautGauche;
    }

    /**
     * fonction permettant d'obtenir le coin superieur droit du carre
     *
     * @return les coordonnes du coin superieur droit
     * @throws NullPointerException si les coordonnes n'ont pas etes initialisees
     */
    public CoordonneesCelulle getCoinHautDroit() throws NullPointerException {
        if (coinHautDroit == null) {
            throw new NullPointerException("le coin haut droit n'a pas ete initalise");
        }
        return coinHautDroit;
    }

    /**
     * fonction permettant de definir le coin superieur gauche du carre
     *
     * @param coinHautDroit, le coin superieur droit
     * @throws IllegalArgumentException si on detecte une incoherence
     */
    public void setCoinHautDroit(CoordonneesCelulle coinHautDroit) throws IllegalArgumentException {

        if (coinHautGauche != null) {
            if (coinHautGauche.getX() + 1 >= coinHautDroit.getX() + 1) {
                throw new IllegalArgumentException("Abscisse invalide");
            }
            if (coinHautGauche.getY() + 1 != coinHautDroit.getY() + 1) {
                throw new IllegalArgumentException("difference d'ordonnee interdite");
            }
        }

        if (coinBasDroit != null) {
            if (coinHautDroit.getY() + 1 >= coinBasDroit.getY() + 1) {
                throw new IllegalArgumentException("Ordonnee invalide");
            }
            if (coinBasDroit.getX() + 1 != coinHautDroit.getX() + 1) {
                throw new IllegalArgumentException("difference d'abscisse interdite");
            }
        }
        this.coinHautDroit = coinHautDroit;
    }

    /**
     * fonction permettant d'obtenir le coin inferieur gauche du carre
     *
     * @return les coordonnes du coin inferieur gauche
     * @throws NullPointerException si les coordonnes n'ont pas etes initialisees
     */
    public CoordonneesCelulle getCoinBasGauche() throws NullPointerException {
        if (coinBasGauche == null) {
            throw new NullPointerException("le coin haut droit n'a pas ete initalise");
        }
        return coinBasGauche;
    }

    /**
     * fonction permettant de definir le coin inferieur gauche du carre
     *
     * @param coinBasGauche, le coin inferieur gauche
     * @throws IllegalArgumentException si on detecte une incoherence
     */
    public void setCoinBasGauche(CoordonneesCelulle coinBasGauche) throws IllegalArgumentException {
        if (coinHautGauche != null) {
            if (coinHautGauche.getY() + 1 >= coinBasGauche.getY() + 1) {
                throw new IllegalArgumentException("Ordonnee invalide");
            }
            if (coinBasGauche.getX() + 1 != coinHautGauche.getX() + 1) {
                throw new IllegalArgumentException("difference d'abscisse interdite");
            }
        }
        if (coinBasDroit != null) {
            if (coinBasGauche.getX() + 1 >= coinBasDroit.getX() + 1) {
                throw new IllegalArgumentException("Abscisse invalide");
            }
            if (coinBasGauche.getY() + 1 != coinBasDroit.getY() + 1) {
                throw new IllegalArgumentException("difference d'ordonnee interdite");
            }
        }
        this.coinBasGauche = coinBasGauche;
    }

    /**
     * fonction permettant d'obtenir le coin inferieur droit du carre
     *
     * @return les coordonnes du coin inferieur droit
     * @throws NullPointerException si les coordonnes n'ont pas etes initialisees
     */
    public CoordonneesCelulle getCoinBasDroit() throws NullPointerException {
        if (coinBasDroit == null) {
            throw new NullPointerException("le coin haut droit n'a pas ete initalise");
        }
        return coinBasDroit;
    }

    /**
     * fonction permettant de definir le coin inferieur droit du carre
     *
     * @param coinBasDroit, le coin inferieur droit
     * @throws IllegalArgumentException si on detecte une incoherence
     */
    public void setCoinBasDroit(CoordonneesCelulle coinBasDroit) throws IllegalArgumentException {
        if (coinHautDroit != null) {
            if (coinBasGauche.getX() + 1 >= coinBasDroit.getX() + 1) {
                throw new IllegalArgumentException("Abscisse invalide");
            }
            if (coinHautDroit.getX() + 1 != coinBasDroit.getX() + 1) {
                throw new IllegalArgumentException("difference d'abscisse interdite");
            }
        }
        if (coinBasGauche != null) {
            if (coinHautDroit.getY() + 1 >= coinBasDroit.getY() + 1) {
                throw new IllegalArgumentException("Ordonnee invalide");
            }
            if (coinBasGauche.getY() + 1 != coinBasDroit.getY() + 1) {
                throw new IllegalArgumentException("difference d'ordonnee interdite");
            }
        }
        this.coinBasDroit = coinBasDroit;
    }

    @Override
    public String toString() {
        String retour = "Coin haut gauche:" +
                "\t" + coinHautGauche.toString() + "\n" +
                "Coin haut droit:" +
                "\t" + coinHautDroit.toString() + "\n" +
                "Coin bas gauche:" +
                "\t" + coinBasGauche.toString() + "\n" +
                "Coin bas gauche:" +
                "\t" + coinBasDroit.toString() + "\n";
        return retour;
    }
}
