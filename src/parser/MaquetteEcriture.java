package parser;

import modele.*;
import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import utilitaire.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * classe permettant d'ecrire une feuille de calcul a l'aide d'une maquette au sein d'un fichier Excel
 */
public class MaquetteEcriture {

    private Maquette maquetteComplete;
    private Workbook fichier;
    private Sheet feuille;
    private EcritureCellule utilitaireCellule;
    private EcriturePlageCellule utilitairePlageCellule;
    private CarreCoordoonees matrice = new CarreCoordoonees();

    private final int PAS = 3;
    private final int CM = 0;
    private final int TD = 1;
    private final int TP = 2;
    private final int CONTROLE = 3;

    /**
     * Unique constructeur, instancie notre objet
     *
     * @param maquetteComplete, le modele rempli
     * @param fichierExcel,     le fichier au sein duquel notre feuille va etre cree
     * @throws IllegalArgumentException si les parametres ne sont pas correctement instancie
     */
    public MaquetteEcriture(Maquette maquetteComplete, Workbook fichierExcel) throws IllegalArgumentException {

        if (fichierExcel == null) {
            throw new IllegalArgumentException("le fichier excel n'est pas correctement instancie");
        }

        verifierIntegrite(maquetteComplete);
        this.maquetteComplete = maquetteComplete;
        fichier = fichierExcel;
        feuille = fichier.createSheet(maquetteComplete.getNom());
        utilitaireCellule = new EcritureCellule(fichier, feuille);
        utilitairePlageCellule = new EcriturePlageCellule(fichier, feuille);
    }

    /**
     * fonction ecrivant le nom de la maquette en haut de la feuille de calcul
     */
    private void ecrireNomMaquette() {
        CoordonneesCelulle mesCoordonnesEntete = new CoordonneesCelulle("A", 3);
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        utilitaireCellule.ecrireChaine(mesCoordonnesEntete, maquetteComplete.getNom(), monStyle);
    }

    /**
     * Fonction permettant d'ecire les informations d'Update et la date actuelle
     */
    private void ecrireInfoUpdate() {
        CoordonneesCelulle mesCoordonnesEntete = new CoordonneesCelulle("A", 4);
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        if (maquetteComplete.getPlanning().getAnneeDebut() != maquetteComplete.getPlanning().getAnneeFin()) // je suis au 1er semestre
        {
            utilitaireCellule.ecrireChaine(mesCoordonnesEntete, maquetteComplete.getPlanning().getAnneeDebut() + "-" + (maquetteComplete.getPlanning().getAnneeDebut() + 1), monStyle);
        } else {
            utilitaireCellule.ecrireChaine(mesCoordonnesEntete, (maquetteComplete.getPlanning().getAnneeDebut() - 1) + "-" + maquetteComplete.getPlanning().getAnneeDebut(), monStyle);
        }

        mesCoordonnesEntete.setY(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
        utilitaireCellule.ecrireChaine(mesCoordonnesEntete, "M.A.J le: " + formatter.format(Instant.now()), monStyle);

        mesCoordonnesEntete = new CoordonneesCelulle("B", 3);
        utilitaireCellule.ecrireFormuleDonnantDate(mesCoordonnesEntete, "TODAY()", monStyle);

    }

    /**
     * fonction permettant d'ecrire les disponibilites de l'etudiant au sein de la feuille de calcul
     */
    private void ecrireDisponibiliteEtudiant() {

        utilitaireCellule.ecrireChaine(new CoordonneesCelulle("C", 5), "Disponibilite / etudiant", new StyleCellule());

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("J", 5);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle(coinSuperieur.getX() + PAS, coinSuperieur.getY() + 1);
        String formule;

        for (Periode actuelle : maListe) {

            if (!actuelle.getVacance()) {
                formule = coinSuperieur.getxEnChaine() + (coinSuperieur.getY() + 2) + "*" + 2;
                utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieur, coinInferieur, formule, new StyleCellule());
            }
            coinSuperieur.setX(coinInferieur.getX() + 2);
            coinInferieur.setX(coinSuperieur.getX() + PAS);

            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {
                if (!actuelle.getVacance()) {
                    formule = coinSuperieur.getxEnChaine() + (coinSuperieur.getY() + 2) + "*" + 2;
                    utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieur, coinInferieur, formule, new StyleCellule());

                }
                coinSuperieur.setX(coinInferieur.getX() + 2);
                coinInferieur.setX(coinSuperieur.getX() + PAS);
            }

        }
    }

    /**
     * fonction permettant d'ecrire les creneaux disponibles au sein de la feuille de calcul
     */
    private void ecrireCreneauDisponible() {

        utilitaireCellule.ecrireChaine(new CoordonneesCelulle("C", 6), "Creneaux Disponibles", new StyleCellule());

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("J", 6);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle(coinSuperieur.getX() + PAS, coinSuperieur.getY() + 1);


        for (Periode actuelle : maListe) {
            if (!actuelle.getVacance()) {

                utilitairePlageCellule.ecrireNombre(coinSuperieur, coinInferieur, maquetteComplete.getNombreCreneauDispoSemaine(), new StyleCellule());
            }
            coinSuperieur.setX(coinInferieur.getX() + 2);
            coinInferieur.setX(coinSuperieur.getX() + PAS);

            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {
                if (!actuelle.getVacance()) {

                    utilitairePlageCellule.ecrireNombre(coinSuperieur, coinInferieur, maquetteComplete.getNombreCreneauDispoSemaine(), new StyleCellule());

                }
                coinSuperieur.setX(coinInferieur.getX() + 2);
                coinInferieur.setX(coinSuperieur.getX() + PAS);
            }

        }
    }

    /**
     * fonction permettant d'ecrire les creneaux deja utilise au sein de la feuille de calcul
     */
    private void ecrireCreneauUtilise() {

        CoordonneesCelulle partieStatique = new CoordonneesCelulle("C", 7);
        utilitaireCellule.ecrireChaine(partieStatique, "Crenaux Utilises", new StyleCellule());
        String formule;

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("J", 7);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle(coinSuperieur.getX() + PAS, coinSuperieur.getY() + 1);
        CoordonneesCelulle curseurFormule = new CoordonneesCelulle("J", 10);

        for (Periode actuelle : maListe) {
            if (!actuelle.getVacance()) {
                formule = "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)+";
                curseurFormule.setX(curseurFormule.getX() + 2);
                formule += "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)+";
                curseurFormule.setX(curseurFormule.getX() + 2);
                formule += "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)";
                utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieur, coinInferieur, formule, new StyleCellule());
                curseurFormule.setX(curseurFormule.getX() + 2);
            } else {
                curseurFormule.setX(curseurFormule.getX() + PAS+1);
            }
            coinSuperieur.setX(coinInferieur.getX() + 2);
            coinInferieur.setX(coinSuperieur.getX() + PAS);

            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {
                if (!actuelle.getVacance()) {
                    formule = "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)+";
                    curseurFormule.setX(curseurFormule.getX() + 2);
                    formule += "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)+";
                    curseurFormule.setX(curseurFormule.getX() + 2);
                    formule += "ROUNDUP(" + curseurFormule.getxEnChaine() + "" + (curseurFormule.getY() + 1) + "/" + maquetteComplete.getDureCreneau() + ",0)";
                    curseurFormule.setX(curseurFormule.getX() + 2);
                    utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieur, coinInferieur, formule, new StyleCellule());
                } else {
                    curseurFormule.setX(curseurFormule.getX() + PAS+1);
                }
                coinSuperieur.setX(coinInferieur.getX() + 2);
                coinInferieur.setX(coinSuperieur.getX() + PAS);

            }

        }
    }

    /**
     * fonction permettant d'ecrire la synthese volume/travail de l'etudiant
     */
    private void ecrireSynthese() {
        StyleCellule enTeteStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_BLEU_GRIS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule cmStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_BLEU, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tdStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_VERT, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tpStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_JAUNE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule defautStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle coinSuperieurSomme = new CoordonneesCelulle("C", 9);
        CoordonneesCelulle coinInferieurSomme = new CoordonneesCelulle("C", 11);
        String sousSommeFormule;
        String sommmeColonne;
        CoordonneesCelulle curseurTypeCours = new CoordonneesCelulle("J", 9);
        CoordonneesCelulle curseurVolumeCours = new CoordonneesCelulle("J", 10);

        utilitairePlageCellule.ecrireChaine(coinSuperieurSomme, coinInferieurSomme, "Synthese volume travail / etudiant (h)", enTeteStyle);

        coinSuperieurSomme = new CoordonneesCelulle("J", 11);
        coinInferieurSomme = new CoordonneesCelulle(coinSuperieurSomme.getX() + PAS, coinSuperieurSomme.getY() + 1);

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        for (Periode actuelle : maListe) {

            if (!actuelle.getVacance()) {
                utilitaireCellule.ecrireChaine(curseurTypeCours, "CM", cmStyle);
                curseurTypeCours.setX(curseurTypeCours.getX() + 2);
                utilitaireCellule.ecrireChaine(curseurTypeCours, "TD", tdStyle);
                curseurTypeCours.setX(curseurTypeCours.getX() + 2);
                utilitaireCellule.ecrireChaine(curseurTypeCours, "TP", tpStyle);
                curseurTypeCours.setX(curseurTypeCours.getX() + 2);

                sousSommeFormule = "SUM(" + curseurVolumeCours.getxEnChaine() + (curseurVolumeCours.getY() + 1) + ":";

                sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                sousSommeFormule += curseurVolumeCours.getxEnChaine() + (curseurVolumeCours.getY() + 1) + ")";
                utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurSomme, coinInferieurSomme, sousSommeFormule, defautStyle);
                coinSuperieurSomme = new CoordonneesCelulle(coinSuperieurSomme.getX() + PAS + 1, coinSuperieurSomme.getY() + 1);
                coinInferieurSomme = new CoordonneesCelulle(coinInferieurSomme.getX() + PAS + 1, coinInferieurSomme.getY() + 1);


            } else {
                curseurVolumeCours.setX(curseurVolumeCours.getX() + 4);
                curseurTypeCours.setX(curseurTypeCours.getX() + 4);
                coinSuperieurSomme = new CoordonneesCelulle(coinSuperieurSomme.getX() + PAS + 1, coinSuperieurSomme.getY() + 1);
                coinInferieurSomme = new CoordonneesCelulle(coinInferieurSomme.getX() + PAS + 1, coinInferieurSomme.getY() + 1);

            }


            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {
                if (!actuelle.getVacance()) {
                    sousSommeFormule = "SUM(" + curseurVolumeCours.getxEnChaine() + (curseurVolumeCours.getY() + 1) + ":";

                    sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                    utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                    curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                    sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                    utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                    curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                    sommmeColonne = "SUM(" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinHautGauche().getY() + 1) + ":" + curseurVolumeCours.getxEnChaine() + (matrice.getCoinBasGauche().getY() + 1) + ")";
                    sousSommeFormule += curseurVolumeCours.getxEnChaine() + (curseurVolumeCours.getY() + 1) + ")";
                    utilitaireCellule.ecrireFormuleDonnantNombre(curseurVolumeCours, sommmeColonne, defautStyle);
                    curseurVolumeCours.setX(curseurVolumeCours.getX() + 2);

                    utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurSomme, coinInferieurSomme, sousSommeFormule, defautStyle);
                    coinSuperieurSomme = new CoordonneesCelulle(coinSuperieurSomme.getX() + PAS + 1, coinSuperieurSomme.getY() + 1);
                    coinInferieurSomme = new CoordonneesCelulle(coinInferieurSomme.getX() + PAS + 1, coinInferieurSomme.getY() + 1);


                    utilitaireCellule.ecrireChaine(curseurTypeCours, "CM", cmStyle);
                    curseurTypeCours.setX(curseurTypeCours.getX() + 2);
                    utilitaireCellule.ecrireChaine(curseurTypeCours, "TD", tdStyle);
                    curseurTypeCours.setX(curseurTypeCours.getX() + 2);
                    utilitaireCellule.ecrireChaine(curseurTypeCours, "TP", tpStyle);
                    curseurTypeCours.setX(curseurTypeCours.getX() + 2);

                } else {
                    curseurTypeCours.setX(curseurTypeCours.getX() + 4);
                    curseurVolumeCours.setX(curseurVolumeCours.getX() + 4);
                    coinSuperieurSomme = new CoordonneesCelulle(coinSuperieurSomme.getX() + PAS + 1, coinSuperieurSomme.getY() + 1);
                    coinInferieurSomme = new CoordonneesCelulle(coinInferieurSomme.getX() + PAS + 1, coinInferieurSomme.getY() + 1);

                }


            }

        }

    }

    /**
     * fonction permettant d'ecrire les dates ainsi que les numeros de semaine au sein de la feuille de calcul
     */
    private void ecrireDates() {
        CoordonneesCelulle coinSuperieurNumero = new CoordonneesCelulle("J", 13);
        CoordonneesCelulle coinInferieurNumero = new CoordonneesCelulle(coinSuperieurNumero.getX() + PAS, coinSuperieurNumero.getY() + 1);

        CoordonneesCelulle coinSuperieurDate = new CoordonneesCelulle("J", 14);
        CoordonneesCelulle coinInferieurDate = new CoordonneesCelulle(coinSuperieurDate.getX() + PAS, coinSuperieurDate.getY() + 1);

        StyleCellule styleNumero = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_GRIS_CLAIR, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule styleDate = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_GRIS_FONCE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule vacance = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_ROUGE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        utilitaireCellule.ecrireChaine(new CoordonneesCelulle("C", 13), "Numero semaine", styleNumero);
        utilitaireCellule.ecrireChaine(new CoordonneesCelulle("C", 14), "Date semaine", styleDate);

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();


        for (Periode actuelle : maListe) {
            String formuleNumeroSemaine = "WEEKNUM(" + coinSuperieurDate.getxEnChaine() + "" + (coinSuperieurDate.getY() + 1) + ")";

            if (actuelle.getVacance()) {
                utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurNumero, coinInferieurNumero, formuleNumeroSemaine, vacance);
                utilitairePlageCellule.ecrireChaine(coinSuperieurDate, coinInferieurDate, actuelle.getDebutenChaine(), vacance);
            } else {
                utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurNumero, coinInferieurNumero, formuleNumeroSemaine, styleNumero);
                utilitairePlageCellule.ecrireChaine(coinSuperieurDate, coinInferieurDate, actuelle.getDebutenChaine(), styleDate);
            }
            coinSuperieurNumero = new CoordonneesCelulle(coinSuperieurNumero.getX() + PAS + 1, coinSuperieurNumero.getY() + 1);
            coinInferieurNumero = new CoordonneesCelulle(coinInferieurNumero.getX() + PAS + 1, coinInferieurNumero.getY() + 1);

            coinSuperieurDate = new CoordonneesCelulle(coinSuperieurDate.getX() + PAS + 1, coinSuperieurDate.getY() + 1);
            coinInferieurDate = new CoordonneesCelulle(coinInferieurDate.getX() + PAS + 1, coinInferieurDate.getY() + 1);


            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {
                CoordonneesCelulle temporaire = new CoordonneesCelulle(coinSuperieurDate.getX(), coinSuperieurDate.getY() + 1);
                String formuleDate = temporaire.getxEnChaine() + "" + (temporaire.getY() + 1) + "+7";
                formuleNumeroSemaine = "WEEKNUM(" + coinSuperieurDate.getxEnChaine() + "" + (coinSuperieurDate.getY() + 1) + ")";


                if (actuelle.getVacance()) {
                    utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurNumero, coinInferieurNumero, formuleNumeroSemaine, vacance);
                    utilitairePlageCellule.ecrireFormuleDonnantDate(coinSuperieurDate, coinInferieurDate, formuleDate, vacance);
                } else {
                    utilitairePlageCellule.ecrireFormuleDonnantNombre(coinSuperieurNumero, coinInferieurNumero, formuleNumeroSemaine, styleNumero);
                    utilitairePlageCellule.ecrireFormuleDonnantDate(coinSuperieurDate, coinInferieurDate, formuleDate, styleDate);
                }

                coinSuperieurNumero = new CoordonneesCelulle(coinSuperieurNumero.getX() + PAS + 1, coinSuperieurNumero.getY() + 1);
                coinInferieurNumero = new CoordonneesCelulle(coinInferieurNumero.getX() + PAS + 1, coinInferieurNumero.getY() + 1);

                coinSuperieurDate = new CoordonneesCelulle(coinSuperieurDate.getX() + PAS + 1, coinSuperieurDate.getY() + 1);
                coinInferieurDate = new CoordonneesCelulle(coinInferieurDate.getX() + PAS + 1, coinInferieurDate.getY() + 1);


            }
        }

        matrice.setCoinHautDroit(new CoordonneesCelulle(coinSuperieurDate.getX(), 18));
    }

    /**
     * fonction permettant d'ecrire la partie statique de la feuille concernant les modules
     */
    private void ecrireCartoucheModule() {
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_AVEC, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle mesCoordones = new CoordonneesCelulle("A", 17);
        utilitaireCellule.ecrireChaine(mesCoordones, "Enseignement", monStyle);

        mesCoordones.setX("B");
        utilitaireCellule.ecrireChaine(mesCoordones, "Enseignement", monStyle);

        mesCoordones.setX("C");
        utilitaireCellule.ecrireChaine(mesCoordones, "Intervenant", monStyle);

        mesCoordones.setX("D");
        utilitaireCellule.ecrireChaine(mesCoordones, "", monStyle);

        mesCoordones.setX("E");
        utilitaireCellule.ecrireChaine(mesCoordones, "CC", monStyle);

        mesCoordones.setX("F");
        utilitaireCellule.ecrireChaine(mesCoordones, "ET", monStyle);

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("D", 16);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle("F", 16);
        utilitairePlageCellule.ecrireChaine(coinSuperieur, coinInferieur, "Heures a placer", monStyle);

        coinSuperieur = new CoordonneesCelulle("G", 16);
        coinInferieur = new CoordonneesCelulle("G", 17);
        monStyle.setCouleurPolice(StyleCellule.POLICE_ROUGE);
        utilitairePlageCellule.ecrireChaine(coinSuperieur, coinInferieur, "Heures placees", monStyle);

    }

    /**
     * fonction permettant d'ecrire la repartition des CM/TD/TP en haut de la matrice de rendu
     */
    private void ecrireCartoucheRepartition() {

        CoordonneesCelulle curseur = new CoordonneesCelulle("J", 16);
        StyleCellule cmStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_BLEU, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tdStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_VERT, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tpStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_JAUNE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        for (Periode actuelle : maListe) {
            if (!actuelle.getVacance()) {
                utilitaireCellule.ecrireChaine(curseur, "CM", cmStyle);
                curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
                utilitaireCellule.ecrireChaine(curseur, "TD", tdStyle);
                curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
                utilitaireCellule.ecrireChaine(curseur, "TP", tpStyle);
                curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
            } else {
                curseur = new CoordonneesCelulle(curseur.getX() + 4, curseur.getY() + 1);
            }

            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {

                if (!actuelle.getVacance()) {
                    utilitaireCellule.ecrireChaine(curseur, "CM", cmStyle);
                    curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
                    utilitaireCellule.ecrireChaine(curseur, "TD", tdStyle);
                    curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
                    utilitaireCellule.ecrireChaine(curseur, "TP", tpStyle);
                    curseur = new CoordonneesCelulle(curseur.getX() + 2, curseur.getY() + 1);
                } else {
                    curseur = new CoordonneesCelulle(curseur.getX() + 4, curseur.getY() + 1);
                }


            }
        }
    }

    /**
     * fonction chargee d'ecrire dynamiquement les modules, les intervenants, les heures de CC/ET/CM/TD/TP, les heures places
     */
    private void ecrireModule() {
        CoordonneesCelulle moduleCurseur = new CoordonneesCelulle("A", 18);
        StyleCellule moduleStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_AVEC, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle enseignementCurseur = new CoordonneesCelulle("B", 18);
        StyleCellule enseignementStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle intervenantCurseur = new CoordonneesCelulle("C", 18);
        StyleCellule intervenantStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_VERTE);

        CoordonneesCelulle volumeCurseur = new CoordonneesCelulle("D", 18);
        StyleCellule volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_VERTE);
        StyleCellule cm_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_BLEU, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule td_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_VERT, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tp_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_JAUNE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule controle_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_ROUGE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule styleVide = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule styleHeurePlacees = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_ROUGE);

        CoordonneesCelulle temporaire;
        String sommeLigne;

        for (int icpt = 0; icpt < maquetteComplete.getListeUniteEnseignement().size(); icpt++) {
            UniteEnseignement monUE = maquetteComplete.getListeUniteEnseignement().get(icpt);

            temporaire = new CoordonneesCelulle("A", enseignementCurseur.getY() + monUE.getVariete());
            utilitairePlageCellule.ecrireChaine(moduleCurseur, temporaire, monUE.getNomUnite(), moduleStyle);
            moduleCurseur = new CoordonneesCelulle("A", moduleCurseur.getY() + 1 + monUE.getVariete());


            for (int jcpt = 0; jcpt < monUE.getEnseignementList().size(); jcpt++) {
                Enseignement monEnseignement = monUE.getEnseignementList().get(jcpt);

                temporaire = new CoordonneesCelulle("B", enseignementCurseur.getY() + monEnseignement.getVariete());
                utilitairePlageCellule.ecrireChaine(enseignementCurseur, temporaire, monEnseignement.getNomEnseignement(), enseignementStyle);
                enseignementCurseur = new CoordonneesCelulle("B", enseignementCurseur.getY() + 1 + monEnseignement.getVariete());


                for (int kcpt = 0; kcpt < monEnseignement.getListeProfesseur().size(); kcpt++) {
                    Professeur monProfesseur = monEnseignement.getListeProfesseur().get(kcpt);

                    temporaire = new CoordonneesCelulle("C", intervenantCurseur.getY() + monProfesseur.getVariete());
                    utilitairePlageCellule.ecrireChaine(intervenantCurseur, temporaire, monProfesseur.getNom(), intervenantStyle);
                    intervenantCurseur = new CoordonneesCelulle("C", intervenantCurseur.getY() + 1 + monProfesseur.getVariete());

                    VolumeHorraire maRepartition = monProfesseur.getRepartition();

                    if (maRepartition.cmPresent()) {
                        utilitaireCellule.ecrireNombre(volumeCurseur, maRepartition.getCmVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "CM", cm_volumeStyle);

                        sommeLigne = "SUM(" + matrice.getCoinHautGauche().getxEnChaine() + (volumeCurseur.getY() + 1) + ":" + matrice.getCoinHautDroit().getxEnChaine() + (volumeCurseur.getY() + 1) + ")";
                        temporaire = new CoordonneesCelulle("G", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireFormuleDonnantNombre(temporaire, sommeLigne, styleHeurePlacees);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }
                    if (maRepartition.tdPresent()) {
                        utilitaireCellule.ecrireNombre(volumeCurseur, maRepartition.getTdVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "TD", td_volumeStyle);

                        sommeLigne = "SUM(" + matrice.getCoinHautGauche().getxEnChaine() + (volumeCurseur.getY() + 1) + ":" + matrice.getCoinHautDroit().getxEnChaine() + (volumeCurseur.getY() + 1) + ")";
                        temporaire = new CoordonneesCelulle("G", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireFormuleDonnantNombre(temporaire, sommeLigne, styleHeurePlacees);
                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);


                    }
                    if (maRepartition.tpPresent()) {
                        utilitaireCellule.ecrireNombre(volumeCurseur, maRepartition.getTpVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "TP", tp_volumeStyle);

                        sommeLigne = "SUM(" + matrice.getCoinHautGauche().getxEnChaine() + (volumeCurseur.getY() + 1) + ":" + matrice.getCoinHautDroit().getxEnChaine() + (volumeCurseur.getY() + 1) + ")";
                        temporaire = new CoordonneesCelulle("G", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireFormuleDonnantNombre(temporaire, sommeLigne, styleHeurePlacees);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }


                    if (maRepartition.ccPresent()) {

                        temporaire = new CoordonneesCelulle("D", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireNombre(temporaire, maRepartition.getCcVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", styleVide);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "CC", controle_volumeStyle);

                        sommeLigne = "SUM(" + matrice.getCoinHautGauche().getxEnChaine() + (volumeCurseur.getY() + 1) + ":" + matrice.getCoinHautDroit().getxEnChaine() + (volumeCurseur.getY() + 1) + ")";
                        temporaire = new CoordonneesCelulle("G", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireFormuleDonnantNombre(temporaire, sommeLigne, styleHeurePlacees);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);

                    }
                    if (maRepartition.etPresent()) {
                        temporaire = new CoordonneesCelulle("D", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireNombre(temporaire, maRepartition.getEtVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireChaine(temporaire, "ET", controle_volumeStyle);

                        sommeLigne = "SUM(" + matrice.getCoinHautGauche().getxEnChaine() + (volumeCurseur.getY() + 1) + ":" + matrice.getCoinHautDroit().getxEnChaine() + (volumeCurseur.getY() + 1) + ")";
                        temporaire = new CoordonneesCelulle("G", volumeCurseur.getY() + 1);
                        utilitaireCellule.ecrireFormuleDonnantNombre(temporaire, sommeLigne, styleHeurePlacees);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }

                }


            }

            moduleCurseur = new CoordonneesCelulle("A", moduleCurseur.getY() + 2);
            enseignementCurseur = new CoordonneesCelulle("B", enseignementCurseur.getY() + 2);
            intervenantCurseur = new CoordonneesCelulle("C", intervenantCurseur.getY() + 2);
            volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
        }

        matrice.setCoinBasGauche(new CoordonneesCelulle("J", volumeCurseur.getY() - 1));
    }

    /**
     * fonction chargee d'ecrire la matrice de rendu au sein de notre feuille
     */
    private void ecrireMatrice() {
        CoordonneesCelulle curseur = matrice.getCoinHautGauche();

        for (int icpt = 0; icpt < maquetteComplete.getListeUniteEnseignement().size(); icpt++) {
            UniteEnseignement monUE = maquetteComplete.getListeUniteEnseignement().get(icpt);

            for (int jcpt = 0; jcpt < monUE.getEnseignementList().size(); jcpt++) {
                Enseignement monEnseignement = monUE.getEnseignementList().get(jcpt);

                for (int kcpt = 0; kcpt < monEnseignement.getListeProfesseur().size(); kcpt++) {
                    Professeur monProfesseur = monEnseignement.getListeProfesseur().get(kcpt);
                    VolumeHorraire maRepartition = monProfesseur.getRepartition();

                    if (maRepartition.cmPresent()) {
                        ecrireLigneMatrice(curseur, CM);
                        curseur.setY(curseur.getY() + 2);
                    }
                    if (maRepartition.tdPresent()) {
                        ecrireLigneMatrice(curseur, TD);
                        curseur.setY(curseur.getY() + 2);
                    }
                    if (maRepartition.tpPresent()) {
                        ecrireLigneMatrice(curseur, TP);
                        curseur.setY(curseur.getY() + 2);
                    }
                    if (maRepartition.etPresent()) {
                        ecrireLigneMatrice(curseur, CONTROLE);
                        curseur.setY(curseur.getY() + 2);
                    }
                    if (maRepartition.ccPresent()) {
                        ecrireLigneMatrice(curseur, CONTROLE);
                        curseur.setY(curseur.getY() + 2);
                    }

                }

            }
            curseur.setY(curseur.getY() + 2);
        }
    }

    /**
     * fonction permettant d'ecrire une ligne de notre matrice de rendu, en fonction du type de cours (CM,TD,TP,CC/ET) de reference
     *
     * @param refLigne,    jeux de coordonnes de notre ligne
     * @param typeDeCours, type de cours de reference (CF constante)
     */
    private void ecrireLigneMatrice(CoordonneesCelulle refLigne, int typeDeCours) {

        StyleCellule styleRef;
        StyleCellule vacance = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_ROUGE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle curseur = new CoordonneesCelulle(refLigne.getX() + 1, refLigne.getY() + 1);

        switch (typeDeCours) {
            case CM:
                styleRef = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_BLEU, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
                break;
            case TD:
                styleRef = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_VERT, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
                break;
            case TP:
                styleRef = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_JAUNE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
                break;
            default:
                styleRef = new StyleCellule();
        }


        ArrayList<Periode> maListe = maquetteComplete.getPlanning().getPeriodeListe();

        for (Periode actuelle : maListe) {
            if (!actuelle.getVacance()) {
                switch (typeDeCours) {
                    case CM:
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        break;
                    case TD:
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        break;
                    case TP:
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                        break;
                    default:
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                        curseur.setX(curseur.getX() + 2);
                        utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                }
                curseur.setX(curseur.getX() + 2);
            } else {
                utilitaireCellule.ecrireChaine(curseur, "", vacance);
                curseur.setX(curseur.getX() + 2);
                utilitaireCellule.ecrireChaine(curseur, "", vacance);
                curseur.setX(curseur.getX() + 2);
                utilitaireCellule.ecrireChaine(curseur, "", vacance);
                curseur.setX(curseur.getX() + 2);
            }

            for (int jcpt = 0; jcpt < actuelle.getNombreSemaineScolaire() - 1; jcpt++) {

                if (!actuelle.getVacance()) {
                    switch (typeDeCours) {
                        case CM:
                            utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            break;
                        case TD:
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            break;
                        case TP:
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", styleRef);
                            break;
                        default:
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                            curseur.setX(curseur.getX() + 2);
                            utilitaireCellule.ecrireChaine(curseur, "", new StyleCellule());
                    }
                } else {
                    utilitaireCellule.ecrireChaine(curseur, "", vacance);
                    curseur.setX(curseur.getX() + 2);
                    utilitaireCellule.ecrireChaine(curseur, "", vacance);
                    curseur.setX(curseur.getX() + 2);
                    utilitaireCellule.ecrireChaine(curseur, "", vacance);
                }

                curseur.setX(curseur.getX() + 2);
            }


        }
    }

    /**
     * fonction permettant d'ecrire l'integralite de notre maquette au sein du fichier excel
     */
    public void ecrireMaquette() {

        matrice.setCoinHautGauche(new CoordonneesCelulle("J", 18));

        ecrireNomMaquette();
        ecrireInfoUpdate();
        ecrireDisponibiliteEtudiant();
        ecrireCreneauDisponible();
        ecrireCreneauUtilise();
        ecrireDates();
        ecrireCartoucheModule();
        ecrireCartoucheRepartition();
        ecrireModule();
        ecrireSynthese();
        ecrireMatrice();

        finalise();

    }

    /**
     * fonction chargee de dimensioner automatiquement les largeurs de colonnes au sein de la feuille de calcul
     */
    private void finalise() {
        for (int i = 0; i < matrice.getCoinHautDroit().getX() + 1; i++) {
            feuille.autoSizeColumn(i, true);
        }
    }

    /**
     * fonction permettant de verifier l'integrite de la maquette
     *
     * @param maquetteComplete, la maquette dont on test l'integrite
     * @throws IllegalArgumentException si un seul des constituants n'est pas correct
     */
    private void verifierIntegrite(Maquette maquetteComplete) throws IllegalArgumentException {

        /**
         * Verification de l'objet en lui meme
         */
        if (maquetteComplete == null) {

            throw new IllegalArgumentException("la maquette n'est pas convenablement instancie");
        }

        /**
         * Verification sur le nom
         */
        if (maquetteComplete.getNom() == null) {
            throw new IllegalArgumentException("la maquette ne possede pas de nom");
        }

        /**
         * Verification du planning
         */
        if (maquetteComplete.getPlanning() == null) {

            throw new IllegalArgumentException("la maquette ne possede pas de planning");
        } else {
            if (maquetteComplete.getPlanning().getAnneeDebut() == 0 || maquetteComplete.getPlanning().getAnneeFin() == 0) {
                throw new IllegalArgumentException("les dates ne sont pas instanciees convenablement au sein de notre planning");
            }

            if (maquetteComplete.getPlanning().getPeriodeListe() == null) {
                throw new IllegalArgumentException("la liste de periode est vide");
            } else {
                ArrayList<Periode> periodeListe = maquetteComplete.getPlanning().getPeriodeListe();

                for (Periode aPeriodeListe : periodeListe) {
                    if (aPeriodeListe.getDebut() == null || aPeriodeListe.getFin() == null) {
                        throw new IllegalArgumentException("date de fin ou de debut manquante au sein de la maquette");
                    }

                }
            }
        }

        /**
         * Verification des modules
         */
        if (maquetteComplete.getListeUniteEnseignement() == null) {
            throw new IllegalArgumentException("la maquette ne possede pas de module");
        } else {
            ArrayList<UniteEnseignement> UniteEnseignementListe = maquetteComplete.getListeUniteEnseignement();

            for (UniteEnseignement curseurUE : UniteEnseignementListe) {
                if (curseurUE.getNomUnite() == null) {
                    throw new IllegalArgumentException("nom d'UE manquant");
                }

                if (curseurUE.getEnseignementList() == null) {
                    throw new IllegalArgumentException("liste de modules non initialisee");
                } else {
                    ArrayList<Enseignement> enseignementListe = curseurUE.getEnseignementList();

                    for (Enseignement curseurEnseignement : enseignementListe) {

                        if (curseurEnseignement.getNomEnseignement() == null) {
                            throw new IllegalArgumentException("nom d'enseignement manquant");
                        }

                        if (curseurEnseignement.getListeProfesseur() == null) {
                            throw new IllegalArgumentException("liste de modules non initialisee au sein d'un enseignement");
                        } else {
                            ArrayList<Professeur> professeurListe = curseurEnseignement.getListeProfesseur();

                            for (Professeur professeurCurseur : professeurListe) {
                                {
                                    if (professeurCurseur.getVariete() == 0) {
                                        throw new IllegalArgumentException("professeur sans cours dans un enseignement");
                                    }
                                }
                            }
                        }


                    }
                }

            }
        }

        if (maquetteComplete.getNombreCreneauDispoSemaine() == 0) {
            throw new IllegalArgumentException("les creneaux n'ont pas etes instancies correctement");
        }


    }

}
