package test;



import utilitaire.CoordonneesCelulle;

import static org.junit.Assert.assertEquals;


public class CoordonneesCelulleTest {

    private String[] monTableauDeChaine = {"AA", "AB", "AG", "AZ", "BA", "BB", "BC", "BZ", "ZA", "ZB", "ZZ", "AAA", "AKC"};
    private int[] monTableauDentier = {27, 28, 33, 52, 53, 54, 55, 78, 677, 678, 702, 703, 965};

    @org.junit.Test
    public void testGetxEnChaineAvecString() throws Exception {
        String monTest;
        CoordonneesCelulle monJeu;

        for (int i = 1; i <= 26; i++) {
            monTest = "" + (char) ('A' + i - 1);
            monJeu = new CoordonneesCelulle(monTest, 1);
            assertEquals("Valeur attendue:", monTest, monJeu.getxEnChaine());
        }

        for (int i = 0; i < monTableauDeChaine.length; i++) {
            monJeu = new CoordonneesCelulle(monTableauDeChaine[i], 1);

            assertEquals("Valeur attendue:", monTableauDeChaine[i], monJeu.getxEnChaine());
        }
    }

    @org.junit.Test
    public void testGetxEnChaineAvecInt() throws Exception {
        CoordonneesCelulle monJeu;
        for (int i = 1; i <= 26; i++) {
            monJeu = new CoordonneesCelulle(i, 1);
            assertEquals("Valeur attendue:", "" + (char) ('A' + i - 1), monJeu.getxEnChaine());
        }

        for (int i = 0; i < monTableauDeChaine.length; i++) {
            monJeu = new CoordonneesCelulle(monTableauDentier[i], 1);
            assertEquals("Valeur attendue:", monTableauDeChaine[i], monJeu.getxEnChaine());
        }
    }

    @org.junit.Test
    public void testGetXAvecString() throws Exception {
        String monTest;
        CoordonneesCelulle monJeu;
        for (int i = 0; i <= 25; i++) {
            monTest = "" + (char) ('A' + i);
            monJeu = new CoordonneesCelulle(monTest, 1);
            assertEquals("Valeur attendue:", i, monJeu.getX());
        }

        for (int i = 0; i < monTableauDeChaine.length; i++) {
            monJeu = new CoordonneesCelulle(monTableauDeChaine[i], 1);
            assertEquals("Valeur attendue:", monTableauDentier[i] - 1, monJeu.getX());
        }

    }

    @org.junit.Test
    public void testGetXAvecInt() throws Exception {
        CoordonneesCelulle monJeu;
        for (int i = 1; i <= 26; i++) {
            monJeu = new CoordonneesCelulle(i, 1);
            assertEquals("Valeur attendue:", i - 1, monJeu.getX());
        }

        for (int i = 0; i < monTableauDeChaine.length; i++) {
            monJeu = new CoordonneesCelulle(monTableauDentier[i], 1);
            assertEquals("Valeur attendue:", monTableauDentier[i] - 1, monJeu.getX());
        }
    }

    @org.junit.Test
    public void testIntegrite() throws Exception{
        CoordonneesCelulle curseur = new CoordonneesCelulle("A", 1);
        assertEquals("Valeur attendue sur X", "A", curseur.getxEnChaine());
        assertEquals("Valeur attendue sur X",0, curseur.getX());
        assertEquals("Valeur attendue sur Y",0, curseur.getY());
    }
}