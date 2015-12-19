package utilitaire;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * Created by germa on 30/10/2015.
 */
public class EcriturePlageCellule {
    Workbook monFichierExcel;
    Sheet maFeuille;
    EcritureCellule monUtilitaireEcriture;

    /**
     * Constructeur permettant la creation de l'utilitaire de gestion d'ecriture de plage de cellules
     *
     * @param monFichierExcel, Le fichier Excel sur lequel on va travailler
     * @param maFeuille,       La feuille utilisee au sein du fichier excel
     */
    public EcriturePlageCellule(Workbook monFichierExcel, Sheet maFeuille) {
        this.monFichierExcel = monFichierExcel;
        this.maFeuille = maFeuille;
        monUtilitaireEcriture = new EcritureCellule(monFichierExcel, maFeuille);
    }

    /**
     * fonction permettant l'ecriture d'une chaine de caracteres aux coordonnees indiquees
     *
     * @param coinSuperieur, la cellule la plus en haut a gauche de la plage
     * @param coinInferieur, la cellule le plus en bas a droite de la plage
     * @param contenu,       contenu a ecrire
     * @param monStyle,      style de la cellule
     */
    public void ecrireChaine(CoordonneesCelulle coinSuperieur, CoordonneesCelulle coinInferieur, String contenu, StyleCellule monStyle) {

        CellRangeAddress mesCellules = creerZone(coinSuperieur, coinInferieur);

        for (int i = mesCellules.getFirstColumn(); i <= mesCellules.getLastColumn(); i++) {
            for (int j = mesCellules.getFirstRow(); j <= mesCellules.getLastRow(); j++) {
                CoordonneesCelulle mesCoordonnesTemporaires = new CoordonneesCelulle(i + 1, j + 1);
                monUtilitaireEcriture.ecrireChaine(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        maFeuille.addMergedRegion(mesCellules);
        applicationStyle(monStyle, mesCellules);
    }

    /**
     * fonction permettant l'ecriture d'une chaine de caracteres aux coordonnees indiquees
     *
     * @param coinSuperieur, la cellule la plus en haut a gauche de la plage
     * @param coinInferieur, la cellule le plus en bas a droite de la plage
     * @param contenu,       contenu a ecrire
     * @param monStyle,      style de la cellule
     */
    public void ecrireNombre(CoordonneesCelulle coinSuperieur, CoordonneesCelulle coinInferieur, double contenu, StyleCellule monStyle) {

        CellRangeAddress mesCellules = creerZone(coinSuperieur, coinInferieur);

        for (int i = mesCellules.getFirstColumn(); i <= mesCellules.getLastColumn(); i++) {
            for (int j = mesCellules.getFirstRow(); j <= mesCellules.getLastRow(); j++) {
                CoordonneesCelulle mesCoordonnesTemporaires = new CoordonneesCelulle(i + 1, j + 1);
                monUtilitaireEcriture.ecrireNombre(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        applicationStyle(monStyle, mesCellules);
        maFeuille.addMergedRegion(mesCellules);
    }

    /**
     * fonction permettant l'ecriture d'une chaine de caracteres aux coordonnees indiquees
     *
     * @param coinSuperieur, la cellule la plus en haut a gauche de la plage
     * @param coinInferieur, la cellule le plus en bas a droite de la plage
     * @param contenu,       formule a ecrire
     * @param monStyle,      style de la cellule
     */
    public void ecrireFormuleDonnantNombre(CoordonneesCelulle coinSuperieur, CoordonneesCelulle coinInferieur, String contenu, StyleCellule monStyle) {

        CellRangeAddress mesCellules = creerZone(coinSuperieur, coinInferieur);

        for (int i = mesCellules.getFirstColumn(); i <= mesCellules.getLastColumn(); i++) {
            for (int j = mesCellules.getFirstRow(); j <= mesCellules.getLastRow(); j++) {
                CoordonneesCelulle mesCoordonnesTemporaires = new CoordonneesCelulle(i + 1, j + 1);
                monUtilitaireEcriture.ecrireFormuleDonnantNombre(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        maFeuille.addMergedRegion(mesCellules);
        applicationStyle(monStyle, mesCellules);
    }

    /**
     * fonction permettant l'ecriture d'une chaine de caracteres aux coordonnees indiquees
     *
     * @param coinSuperieur, la cellule la plus en haut a gauche de la plage
     * @param coinInferieur, la cellule le plus en bas a droite de la plage
     * @param contenu,       formule a ecrire
     * @param monStyle,      style de la cellule
     */
    public void ecrireFormuleDonnantDate(CoordonneesCelulle coinSuperieur, CoordonneesCelulle coinInferieur, String contenu, StyleCellule monStyle) {

        CellRangeAddress mesCellules = creerZone(coinSuperieur, coinInferieur);

        for (int i = mesCellules.getFirstColumn(); i <= mesCellules.getLastColumn(); i++) {
            for (int j = mesCellules.getFirstRow(); j <= mesCellules.getLastRow(); j++) {
                CoordonneesCelulle mesCoordonnesTemporaires = new CoordonneesCelulle(i + 1, j + 1);
                monUtilitaireEcriture.ecrireFormuleDonnantDate(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        maFeuille.addMergedRegion(mesCellules);
        applicationStyle(monStyle, mesCellules);
    }

    /**
     * fonction permettant l'application du style restant propre aux plage de cellules
     *
     * @param mesCellules, plage de cellule sur laquelle on va appliquer le style
     * @param monStyle,    style de la cellule
     */
    private void applicationStyle(StyleCellule monStyle, CellRangeAddress mesCellules) {
        applicationBordure(monStyle, mesCellules);
    }

    /**
     * fonction permettant l'application du type de bordure � la palge de cellule
     *
     * @param mesCellules, plage de cellule sur laquelle on va appliquer le style
     * @param monStyle,    style de la cellule
     */
    private void applicationBordure(StyleCellule monStyle, CellRangeAddress mesCellules) {

        switch (monStyle.getBordure()) {
            case StyleCellule.BORDURE_SANS:
                break;

            case StyleCellule.BORDURE_SIMPLE:
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mesCellules, maFeuille, monFichierExcel);
                break;

            case StyleCellule.BORDURE_DOUBLE:
                RegionUtil.setBorderBottom(CellStyle.BORDER_DOUBLE, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderTop(CellStyle.BORDER_DOUBLE, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderLeft(CellStyle.BORDER_DOUBLE, mesCellules, maFeuille, monFichierExcel);
                RegionUtil.setBorderRight(CellStyle.BORDER_DOUBLE, mesCellules, maFeuille, monFichierExcel);
                break;
        }
    }

    /**
     * fonction permettant la cr�ation d'une plage de cellule � partir de coordonn�s
     *
     * @param coinSuperieur, la cellule la plus en haut a gauche de la plage
     * @param coinInferieur, la cellule le plus en bas a droite de la plage
     * @return la plage de cellule correspondante
     */
    private CellRangeAddress creerZone(CoordonneesCelulle coinSuperieur, CoordonneesCelulle coinInferieur) {
        CellRangeAddress mesCellules = new CellRangeAddress(
                coinSuperieur.getY(), //first row
                coinInferieur.getY(), //last row
                coinSuperieur.getX(), //first column
                coinInferieur.getX()  //last column
        );
        return mesCellules;
    }
}