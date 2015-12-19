package utilitaire;

/**
 * Created by germa on 19/12/2015.
 */
public class MatriceRendu {
    private CoordonneesCelulle coinHautGauche;
    private CoordonneesCelulle coinHautDroit;
    private CoordonneesCelulle coinBasGauche;
    private CoordonneesCelulle coinBasDroit;

    public void MatriceRendu() {
    }

    public CoordonneesCelulle getCoinHautGauche() {
        return coinHautGauche;
    }

    public void setCoinHautGauche(CoordonneesCelulle coinHautGauche) {
        this.coinHautGauche = coinHautGauche;
    }

    public CoordonneesCelulle getCoinHautDroit() {
        return coinHautDroit;
    }

    public void setCoinHautDroit(CoordonneesCelulle coinHautDroit) {
        this.coinHautDroit = coinHautDroit;
    }

    public CoordonneesCelulle getCoinBasGauche() {
        return coinBasGauche;
    }

    public void setCoinBasGauche(CoordonneesCelulle coinBasGauche) {
        this.coinBasGauche = coinBasGauche;
    }

    public CoordonneesCelulle getCoinBasDroit() {
        return coinBasDroit;
    }

    public void setCoinBasDroit(CoordonneesCelulle coinBasDroit) {
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
