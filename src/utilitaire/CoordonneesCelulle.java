package utilitaire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe representant un jeu de coordonee au sein d'une feuille de calcul
 */
public class CoordonneesCelulle {
    int x;
    int y;

    /**
     * Constructeur permettant la creation d'un jeu de coordonee valide
     * Pour (A,1), il faut ecrire (1,1), pour (B,3) il faut ecrire (2,3)
     *
     * @param x, la coordonne en abscisse
     * @param y, la coordonne en ordonnee
     * @throws IllegalArgumentException si l'une des coordonnees est negative
     */
    public CoordonneesCelulle(int x, int y) throws IllegalArgumentException {
        x--;
        y--;
        if (x < 0) {
            throw new IllegalArgumentException("l'abscisse est invalide, x= " + x);
        }
        if (y < 0) {
            throw new IllegalArgumentException("l'ordonee est invalide, y= " + y);
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur permettant la creation d'un jeu de coordonee valide
     * Pour (A,1), il faut ecrire (A,1), pour (B,3) il faut ecrire (B,3)
     *
     * @param x, la coordonne en abscisse
     * @param y, la coordonne en ordonnee
     * @throws IllegalArgumentException si l'une des coordonnees n'est pas viable pour la feuille
     */
    public CoordonneesCelulle(String x, int y) throws IllegalArgumentException {
        this.x = ParseX(x) - 1;
        y--;
        if (y < 0) {
            throw new IllegalArgumentException("l'ordonee est invalide, y= " + y);
        }
        this.y = y;
    }

    /**
     * Fonction retournant la valeur en abscisse
     *
     * @return x, la valeur sur l'axe des abscisses, Pour x=A la fonction retournera 0, Pour x=Z 25
     */
    public int getX() {
        return x;
    }

    /**
     * Fonction retournant la valeur en abscisse
     *
     * @return x, la valeur sur l'axe des abscisses, Pour x=A la fonction retournera A
     */
    public String getxEnChaine() {
        return convertX(x);
    }

    /**
     * Fonction retournant la valeur en ordonnee
     *
     * @return x, la valeur sur l'axe des ordonnee
     */
    public int getY() {
        return y;
    }

    /**
     * Fonction modifinant la valeur en abscisse
     * Pour (A,sans_importance), il faut avoir x=1
     *
     * @param x, la coordonne en abscisse
     * @throws IndexOutOfBoundsException si la coordonnee est negative
     */
    public void setX(int x) throws IllegalArgumentException {
        x--;
        if (x < 0) {
            throw new IllegalArgumentException("l'abscisse est invalide, x= " + x);
        }
        this.x = x;
    }

    /**
     * Fonction modifinant la valeur en ordonnee
     * Pour (AB,sans_importance), il faut x=AB
     *
     * @param x, la coordonne en abscisse
     * @throws IllegalArgumentException si x n'est pas du bon format
     */
    public void setX(String x) throws IllegalArgumentException {
        this.x = ParseX(x) - 1;
    }

    /**
     * Fonction modifinant la valeur en ordonnee
     * Pour (sans_importance,1), il faut y=1
     *
     * @param y, la coordonne en abscisse
     * @throws IndexOutOfBoundsException si la coordonnee est negative
     */
    public void setY(int y) throws IllegalArgumentException {
        y--;
        if (y < 0) {
            throw new IllegalArgumentException("l'ordonee est invalide, y= " + y);
        }
        this.y = y;
    }

    /**
     * Fonction parsant la valeur en abscisse
     * Pour A, la fonction retournera 0
     *
     * @param x, la coordonne en abscisse
     * @return resultat, le nombre converti en tant qu'entier en base 10
     * @throws IndexOutOfBoundsException si la coordonnee n'est pas parsable
     */
    private int ParseX(String x) throws IllegalArgumentException {
        int resultat = 0;

        x = x.toUpperCase();
        Pattern maRegle = Pattern.compile("^[A-Z]*$");
        Matcher monResultat = maRegle.matcher(x);

        if (!monResultat.matches()) {
            throw new IllegalArgumentException("abscisse invalide, x= " + x);
        }
        for (int i = 0; i < x.length(); i++) {
            int valeurChar = x.charAt(i) - 'A' + 1;
            int facteurBase = (int) Math.pow('Z' - 'A' + 1, x.length() - 1 - i);
            resultat += valeurChar * facteurBase;
        }
        return resultat;
    }

    /**
     * Fonction formattant l'abscisse
     * Pour 0, la fonction retournera A
     *
     * @param x, la coordonne en abscisse
     * @return chaine, la chaine de caractere correspondant a l'entier donne
     * @throws IndexOutOfBoundsException si la coordonnee n'est pas parsable
     */
    private static String convertX(int x) {
        x++;
        int quotient;
        int reste;
        int resteAconvertir = x;
        StringBuilder monRetour = new StringBuilder();

        while (resteAconvertir != 0) {
            quotient = resteAconvertir / 26;
            reste = resteAconvertir - 26 * quotient;
            if (reste == 0) {
                if (quotient == 1) {
                    monRetour.insert(0, "Z");
                    resteAconvertir = 0;
                } else {
                    resteAconvertir = 0;
                    monRetour.insert(0, (char) ('A' + quotient - 2) + "Z");
                }
            } else {
                resteAconvertir = quotient;
                monRetour.insert(0, (char) ('A' + reste - 1));
            }
        }
        return monRetour.toString();
    }

    @Override
    public String toString() {
        return "Valeur: " + "(" + x + "," + y + ") <=> (" + convertX(x) + "," + (y + 1) + ")";
    }
}