package parser;

import modele.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilitaire.CoordonneesCelulle;
import utilitaire.EcritureCellule;
import utilitaire.EcriturePlageCellule;
import utilitaire.StyleCellule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by germa on 16/12/2015.
 */
public class MaquetteEcriture {

    private Maquette maMaquette;
    private Workbook monFichierExcel;
    private Sheet maFeuille;
    private EcritureCellule celluleUtile;
    private EcriturePlageCellule plageUtile;

    public MaquetteEcriture(Maquette maMaquette) {
        this.maMaquette = maMaquette;
        monFichierExcel = new XSSFWorkbook();
        maFeuille = monFichierExcel.createSheet(maMaquette.getNomMaquette());
        celluleUtile = new EcritureCellule(monFichierExcel, maFeuille);
        plageUtile = new EcriturePlageCellule(monFichierExcel, maFeuille);
    }

    private void ecrireNomMaquette() {
        CoordonneesCelulle mesCoordonnesEntete = new CoordonneesCelulle("A", 3);
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        celluleUtile.ecrireChaine(mesCoordonnesEntete, maMaquette.getNomMaquette(), monStyle);
    }

    private void ecrireInfoUpdate() {
        CoordonneesCelulle mesCoordonnesEntete = new CoordonneesCelulle("A", 4);
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SANS, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        //Ecriture de l'annee
     /*   if (maMaquette.getMaDateDeDebut().getYear() != maMaquette.getMaDateDeFin().getYear()) // je suis au 1er semestre
        {
            celluleUtile.ecrireChaine(mesCoordonnesEntete, (maMaquette.getMaDateDeDebut().getYear()) + "-" + (maMaquette.getMaDateDeDebut().getYear() + 1), monStyle);
        } else {
            celluleUtile.ecrireChaine(mesCoordonnesEntete, (maMaquette.getMaDateDeDebut().getYear() - 1) + "-" + (maMaquette.getMaDateDeDebut().getYear()), monStyle);
        }
*/
        //Ecriture de la date d'Update
        mesCoordonnesEntete.setY(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
        celluleUtile.ecrireChaine(mesCoordonnesEntete, "M.A.J le: " + formatter.format(Instant.now()), monStyle);

        //Ecriture de la formule aujourd'hui
        mesCoordonnesEntete = new CoordonneesCelulle("B", 3);
        celluleUtile.ecrireFormuleDonnantDate(mesCoordonnesEntete, "TODAY()", monStyle);

    }

    private void ecrireDisponibiliteEtudiant() {
        CoordonneesCelulle monJeudeBase = new CoordonneesCelulle("C", 5);
        celluleUtile.ecrireChaine(monJeudeBase, "Disponibilite / etudiant", new StyleCellule());

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("J", 5);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle("L", 5);


        int pas = coinInferieur.getX() - coinSuperieur.getX();

        for (int i = 0; i < 7; i++) {
            //Ecriture de la formule
            plageUtile.ecrireFormuleDonnantNombre(coinSuperieur, coinInferieur, coinSuperieur.getxEnChaine() + (coinSuperieur.getY() + 2) + "*" + 2, new StyleCellule());
            coinSuperieur.setX(coinInferieur.getX() + 2);
            coinInferieur.setX(coinSuperieur.getX() + pas + 1);
        }
    }

    private void ecrireCreneauDisponible() {
        CoordonneesCelulle monJeudeBase = new CoordonneesCelulle("C", 6);
        celluleUtile.ecrireChaine(monJeudeBase, "Creneaux disponibles", new StyleCellule());

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("J", 6);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle("L", 6);

        int pas = coinInferieur.getX() - coinSuperieur.getX();


        for (int i = 0; i < 7; i++) {
            plageUtile.ecrireNombre(coinSuperieur, coinInferieur, maMaquette.getMaxSemaine(), new StyleCellule());
            coinSuperieur.setX(coinInferieur.getX() + 2);
            coinInferieur.setX(coinSuperieur.getX() + pas + 1);
        }
    }

    private void ecrireCreneauUtilise() {

        CoordonneesCelulle mesCoordonnes = new CoordonneesCelulle("C", 7);
        celluleUtile.ecrireChaine(mesCoordonnes, "Crenaux Utilises", new StyleCellule());

    }

    private void ecrireSynthese() {
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_BLEU_GRIS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("C", 9);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle("C", 11);
        plageUtile.ecrireChaine(coinSuperieur, coinInferieur, "Synthese volume travail / etudiant (h)", monStyle);
    }

    private void ecrireNumeroSemaine() {
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_GRIS_CLAIR, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        CoordonneesCelulle mesCoordonnes = new CoordonneesCelulle("C", 13);
        celluleUtile.ecrireChaine(mesCoordonnes, "Numero semaine", monStyle);
    }

    private void ecrireDateSemaine() {
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_GRIS_FONCE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        CoordonneesCelulle mesCoordonnes = new CoordonneesCelulle("C", 14);
        celluleUtile.ecrireChaine(mesCoordonnes, "Date semaine", monStyle);
    }

    private void ecrireCartoucheModule() {
        StyleCellule monStyle = new StyleCellule(StyleCellule.BORDURE_DOUBLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_AVEC, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle mesCoordones = new CoordonneesCelulle("A", 17);
        celluleUtile.ecrireChaine(mesCoordones, "Enseignement", monStyle);

        mesCoordones.setX("B");
        celluleUtile.ecrireChaine(mesCoordones, "Enseignement", monStyle);

        mesCoordones.setX("C");
        celluleUtile.ecrireChaine(mesCoordones, "Intervenant", monStyle);

        mesCoordones.setX("D");
        celluleUtile.ecrireChaine(mesCoordones, "", monStyle);

        mesCoordones.setX("E");
        celluleUtile.ecrireChaine(mesCoordones, "CC", monStyle);

        mesCoordones.setX("F");
        celluleUtile.ecrireChaine(mesCoordones, "ET", monStyle);

        CoordonneesCelulle coinSuperieur = new CoordonneesCelulle("D", 16);
        CoordonneesCelulle coinInferieur = new CoordonneesCelulle("F", 16);
        plageUtile.ecrireChaine(coinSuperieur, coinInferieur, "Heures a placer", monStyle);

        coinSuperieur = new CoordonneesCelulle("G", 16);
        coinInferieur = new CoordonneesCelulle("G", 17);
        monStyle.setEcriture(StyleCellule.POLICE_ROUGE);
        plageUtile.ecrireChaine(coinSuperieur, coinInferieur, "Heures placees", monStyle);

    }

    private void ecrireCartoucheRepartition() {

    }

    private void ecrireModule() {
        CoordonneesCelulle moduleCurseur = new CoordonneesCelulle("A", 18);
        StyleCellule moduleStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_AVEC, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle enseignementCurseur = new CoordonneesCelulle("B", 18);
        StyleCellule enseignementStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle intervenantCurseur = new CoordonneesCelulle("C", 18);
        StyleCellule intervenantStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_VERTE);

        CoordonneesCelulle volumeCurseur = new CoordonneesCelulle("D", 18);
        StyleCellule volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_VERTE);
        StyleCellule heurePlace_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_ROUGE);
        StyleCellule cm_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_BLEU, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule td_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_VERT, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule tp_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_JAUNE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule controle_volumeStyle = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_ROUGE, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);
        StyleCellule styleVide = new StyleCellule(StyleCellule.BORDURE_SIMPLE, StyleCellule.FOND_SANS, StyleCellule.GRAS_SANS, StyleCellule.POLICE_NOIRE);

        CoordonneesCelulle temporaire;


        for (int icpt = 0; icpt < maMaquette.getListeUniteEnseignement().size(); icpt++) {
            UniteEnseignement monUE = maMaquette.getListeUniteEnseignement().get(icpt);

            temporaire = new CoordonneesCelulle("A", enseignementCurseur.getY() + monUE.getVariete());
            plageUtile.ecrireChaine(moduleCurseur, temporaire, monUE.getNomUnite(), moduleStyle);
            moduleCurseur = new CoordonneesCelulle("A", moduleCurseur.getY() + 1 + monUE.getVariete());


            for (int jcpt = 0; jcpt < monUE.getEnseignementList().size(); jcpt++) {
                Enseignement monEnseignement = monUE.getEnseignementList().get(jcpt);

                temporaire = new CoordonneesCelulle("B", enseignementCurseur.getY() + monEnseignement.getVariete());
                plageUtile.ecrireChaine(enseignementCurseur, temporaire, monEnseignement.getNomEnseignement(), enseignementStyle);
                enseignementCurseur = new CoordonneesCelulle("B", enseignementCurseur.getY() + 1 + monEnseignement.getVariete());


                for (int kcpt = 0; kcpt < monEnseignement.getReparitionProfesseur().size(); kcpt++) {
                    Professeur monProfesseur = monEnseignement.getReparitionProfesseur().get(kcpt);

                    temporaire = new CoordonneesCelulle("C", intervenantCurseur.getY() + monProfesseur.getVariete());
                    plageUtile.ecrireChaine(intervenantCurseur, temporaire, monProfesseur.getNom(), intervenantStyle);
                    intervenantCurseur = new CoordonneesCelulle("C", intervenantCurseur.getY() + 1 + monProfesseur.getVariete());

                    VolumeHorraire maRepartition = monProfesseur.getMaRepartition();

                    if (maRepartition.cmPresent()) {
                        celluleUtile.ecrireNombre(volumeCurseur, maRepartition.getCmVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "CM", cm_volumeStyle);


                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }
                    if (maRepartition.tdPresent()) {
                        celluleUtile.ecrireNombre(volumeCurseur, maRepartition.getTdVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "TD", td_volumeStyle);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }
                    if (maRepartition.tpPresent()) {
                        celluleUtile.ecrireNombre(volumeCurseur, maRepartition.getTpVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "TP", tp_volumeStyle);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }


                    if (maRepartition.ccPresent()) {

                        temporaire = new CoordonneesCelulle("D", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireNombre(temporaire, maRepartition.getCCVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", styleVide);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "CC", controle_volumeStyle);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);

                    }
                    if (maRepartition.etPresent()) {
                        temporaire = new CoordonneesCelulle("D", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("E", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "", volumeStyle);

                        temporaire = new CoordonneesCelulle("F", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireNombre(temporaire, maRepartition.getETVolume(), volumeStyle);

                        temporaire = new CoordonneesCelulle("H", volumeCurseur.getY() + 1);
                        celluleUtile.ecrireChaine(temporaire, "ET", controle_volumeStyle);

                        volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
                    }

                }


            }

            moduleCurseur = new CoordonneesCelulle("A", moduleCurseur.getY() + 2);
            enseignementCurseur = new CoordonneesCelulle("B", enseignementCurseur.getY() + 2);
            intervenantCurseur = new CoordonneesCelulle("C", intervenantCurseur.getY() + 2);
            volumeCurseur = new CoordonneesCelulle("D", volumeCurseur.getY() + 2);
        }

    }

    /**
     * fonction permettant l'ecriture d'une maquette complete
     */
    public void ecrireMaquette() {
        try {

            FileOutputStream fileOut = new FileOutputStream(maMaquette.getNomMaquette() + ".xlsx");

            ecrireNomMaquette();
            ecrireInfoUpdate();
            ecrireDisponibiliteEtudiant();
            ecrireCreneauDisponible();
            ecrireCreneauUtilise();
            ecrireSynthese();
            ecrireNumeroSemaine();
            ecrireDateSemaine();
            ecrireCartoucheModule();
            ecrireCartoucheRepartition();
            ecrireModule();
            finalise();
            monFichierExcel.write(fileOut);
            fileOut.close();

        } catch (IOException Error) {
            Error.printStackTrace();
        }
    }

    private void finalise() {
        for (int i = 0; i < 100; i++) {
            maFeuille.autoSizeColumn(i, true);
        }
    }
}
