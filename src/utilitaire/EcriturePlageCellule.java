package utilitaire;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * Classe representant un utilitaire facilitant la gestion de l'ecriture d'une plage de cellules au sein d'un fichier excel
 */
public class EcriturePlageCellule {
    Workbook fichierExcel;
    Sheet feuilleCalcul;
    EcritureCellule utilitaireCellule;

    /**
     * Constructeur permettant la creation de l'utilitaire de gestion d'ecriture de plage de cellules
     *
     * @param fichierExcel, Le fichier Excel sur lequel on va travailler
     * @param feuilleCalcul,       La feuille utilisee au sein du fichier excel
     */
    public EcriturePlageCellule(Workbook fichierExcel, Sheet feuilleCalcul) {
        this.fichierExcel = fichierExcel;
        this.feuilleCalcul = feuilleCalcul;
        utilitaireCellule = new EcritureCellule(fichierExcel, feuilleCalcul);
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
                utilitaireCellule.ecrireChaine(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        feuilleCalcul.addMergedRegion(mesCellules);
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
                utilitaireCellule.ecrireNombre(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        applicationStyle(monStyle, mesCellules);
        feuilleCalcul.addMergedRegion(mesCellules);
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
                utilitaireCellule.ecrireFormuleDonnantNombre(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        feuilleCalcul.addMergedRegion(mesCellules);
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
                utilitaireCellule.ecrireFormuleDonnantDate(mesCoordonnesTemporaires, contenu, monStyle);
            }
        }

        feuilleCalcul.addMergedRegion(mesCellules);
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
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mesCellules, feuilleCalcul, fichierExcel);
                break;

            case StyleCellule.BORDURE_DOUBLE:
                RegionUtil.setBorderBottom(CellStyle.BORDER_DOUBLE, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderTop(CellStyle.BORDER_DOUBLE, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderLeft(CellStyle.BORDER_DOUBLE, mesCellules, feuilleCalcul, fichierExcel);
                RegionUtil.setBorderRight(CellStyle.BORDER_DOUBLE, mesCellules, feuilleCalcul, fichierExcel);
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