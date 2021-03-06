package utilitaire;

import org.apache.poi.ss.usermodel.*;

/**
 * Classe representant un utilitaire facilitant la gestion de l'ecriture d'une cellule au sein d'un fichier excel
 */
public class EcritureCellule {
    Workbook fichierExcel;
    Sheet feuilleCalcul;
    Row colonne;

    /**
     * Constructeur permettant la creation de l'utilitaire de gestion d'ecriture de Cellule
     *
     * @param fichierExcel, Le fichier Excel sur lequel on va travailler
     * @param feuilleCalcul,       La feuille utilisee au sein du fichier excel
     */
    public EcritureCellule(Workbook fichierExcel, Sheet feuilleCalcul) {
        this.fichierExcel = fichierExcel;
        this.feuilleCalcul = feuilleCalcul;
    }

    /**
     * fonction permettant l'ecriture d'une chaine de caracteres aux coordonnees indiquees
     *
     * @param mesCoordonnees, la ou l'on souhaite ecrire
     * @param monContenu,     ce que l'on souhaite ecrire
     * @param monStyle,       style de la cellule
     */
    public void ecrireChaine(CoordonneesCelulle mesCoordonnees, String monContenu, StyleCellule monStyle) {
        gestionColonne(mesCoordonnees);
        Cell maCellule = colonne.createCell(mesCoordonnees.getX());
        maCellule.setCellType(Cell.CELL_TYPE_STRING);
        maCellule.setCellValue(monContenu);
        applicationStyle(monStyle, maCellule);
    }

    /**
     * fonction permettant l'ecriture d'un nombre aux coordonnees indiquees
     *
     * @param mesCoordonnees, la ou l'on souhaite ecrire
     * @param monContenu,     ce que l'on souhaite ecrire
     * @param monStyle,       style de la cellule
     */
    public void ecrireNombre(CoordonneesCelulle mesCoordonnees, double monContenu, StyleCellule monStyle) {
        gestionColonne(mesCoordonnees);
        Cell maCellule = colonne.createCell(mesCoordonnees.getX());
        maCellule.setCellType(Cell.CELL_TYPE_NUMERIC);
        maCellule.setCellValue(monContenu);
        applicationStyle(monStyle, maCellule);
    }

    /**
     * fonction permettant l'ecriture d'une formule donnant un nombre
     * necessaire du fait que les nombres et les formules soient formatees differement
     *
     * @param mesCoordonnees, la ou l'on souhaite ecrire
     * @param maFormule,      la formule a inserer
     * @param monStyle,       style de la cellule
     */
    public void ecrireFormuleDonnantNombre(CoordonneesCelulle mesCoordonnees, String maFormule, StyleCellule monStyle) {

        gestionColonne(mesCoordonnees);
        Cell maCellule = colonne.createCell(mesCoordonnees.getX());
        maCellule.setCellType(Cell.CELL_TYPE_FORMULA);
        maCellule.setCellFormula(maFormule);
        applicationStyle(monStyle, maCellule);
    }

    /**
     * fonction permettant l'ecriture d'une formule donnant une date
     * necessaire du fait que les dates soient formatees specialement
     *
     * @param mesCoordonnees, la ou l'on souhaite ecrire
     * @param maFormule,      la formule a inserer
     * @param monStyle,       style de la cellule
     */
    public void ecrireFormuleDonnantDate(CoordonneesCelulle mesCoordonnees, String maFormule, StyleCellule monStyle) {

        gestionColonne(mesCoordonnees);
        Cell maCellule = colonne.createCell(mesCoordonnees.getX());
        maCellule.setCellType(Cell.CELL_TYPE_NUMERIC);
        applicationStyle(monStyle, maCellule);
        CellStyle monStyleDeCellule = maCellule.getCellStyle();
        monStyleDeCellule.setDataFormat(fichierExcel.getCreationHelper().createDataFormat().getFormat("d/m/yy"));
        maCellule.setCellFormula(maFormule);
    }

    /**
     * fonction permettant de savoir s'il faut recuperer une colonne ou en creer une nouvelle
     *
     * @param mesCoordonnees, les coordoonees a tester
     */
    private void gestionColonne(CoordonneesCelulle mesCoordonnees) {
        if (feuilleCalcul.getRow(mesCoordonnees.getY()) != null) {
            colonne = feuilleCalcul.getRow(mesCoordonnees.getY());
        } else {
            colonne = feuilleCalcul.createRow(mesCoordonnees.getY());
        }
    }

    /**
     * fonction permettant l'application d'un style de cellule e une cellule donnee
     *
     * @param monStyle,  style de la cellule
     * @param maCellule, la cellule sur laquelle sera appliquee le style
     */
    private void applicationStyle(StyleCellule monStyle, Cell maCellule) {

        CellStyle monStyleDeCellule = fichierExcel.createCellStyle();
        Font maPolice = fichierExcel.createFont();
        monStyleDeCellule.setFont(maPolice);
        monStyleDeCellule.setWrapText(true);
        applicationBordure(monStyle, maCellule, monStyleDeCellule);
        applicationFond(monStyle, maCellule, monStyleDeCellule);

        applicationGras(monStyle, maCellule, monStyleDeCellule, maPolice);
        applicationCouleurEcriture(monStyle, maCellule, monStyleDeCellule, maPolice);

        monStyleDeCellule.setAlignment(CellStyle.ALIGN_CENTER);
        monStyleDeCellule.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        maCellule.setCellStyle(monStyleDeCellule);
    }

    /**
     * fonction permettant l'application d'un type de bordure e une cellule
     *
     * @param monStyle,          style de la cellule
     * @param maCellule,         la cellule sur laquelle sera appliquee le style
     * @param monStyleDeCellule, Style de cellule en cours d'ecriture
     */
    private void applicationBordure(StyleCellule monStyle, Cell maCellule, CellStyle monStyleDeCellule) {
        switch (monStyle.getBordure()) {
            case StyleCellule.BORDURE_SANS:
                break;

            case StyleCellule.BORDURE_SIMPLE:
                monStyleDeCellule.setBorderBottom(CellStyle.BORDER_THIN);
                monStyleDeCellule.setBorderTop(CellStyle.BORDER_THIN);
                monStyleDeCellule.setBorderLeft(CellStyle.BORDER_THIN);
                monStyleDeCellule.setBorderRight(CellStyle.BORDER_THIN);
                break;

            case StyleCellule.BORDURE_DOUBLE:
                monStyleDeCellule.setBorderBottom(CellStyle.BORDER_DOUBLE);
                monStyleDeCellule.setBorderTop(CellStyle.BORDER_DOUBLE);
                monStyleDeCellule.setBorderLeft(CellStyle.BORDER_DOUBLE);
                monStyleDeCellule.setBorderRight(CellStyle.BORDER_DOUBLE);
                break;
        }

    }

    /**
     * fonction permettant l'application d'une couleur de fond e une cellule
     *
     * @param monStyle,          style de la cellule
     * @param maCellule,         la cellule sur laquelle sera appliquee le style
     * @param monStyleDeCellule, Style de cellule en cours d'ecriture
     */
    private void applicationFond(StyleCellule monStyle, Cell maCellule, CellStyle monStyleDeCellule) {
        switch (monStyle.getFond()) {
            case StyleCellule.FOND_SANS:
                break;

            case StyleCellule.FOND_BEIGE:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_BLEU_GRIS:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_BLEU:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_GRIS_CLAIR:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_GRIS_FONCE:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_JAUNE:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_ROUGE:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.RED.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_TURQUOISE:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case StyleCellule.FOND_VERT:
                monStyleDeCellule.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
                monStyleDeCellule.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
        }
    }

    /**
     * fonction permettant l'application d'un type de police: gras, normal, italique...
     *
     * @param monStyle,          style de la cellule
     * @param maCellule,         la cellule sur laquelle sera appliquee le style
     * @param monStyleDeCellule, Style de cellule en cours d'ecriture
     * @param maPolice,          police en cours d'ecriture
     */
    private void applicationGras(StyleCellule monStyle, Cell maCellule, CellStyle monStyleDeCellule, Font maPolice) {

        switch (monStyle.getGras()) {
            case StyleCellule.GRAS_SANS:
                break;

            case StyleCellule.GRAS_AVEC:
                maPolice.setBold(true);
                break;
        }
    }

    /**
     * fonction permettant l'application d'une couleur a une police
     *
     * @param monStyle,          style de la cellule
     * @param maCellule,         la cellule sur laquelle sera appliquee le style
     * @param monStyleDeCellule, Style de cellule en cours d'ecriture
     * @param maPolice,          police en cours d'ecriture
     */
    private void applicationCouleurEcriture(StyleCellule monStyle, Cell maCellule, CellStyle monStyleDeCellule, Font maPolice) {

        switch (monStyle.getCouleurPolice()) {
            case StyleCellule.POLICE_NOIRE:
                break;

            case StyleCellule.POLICE_ROUGE:
                maPolice.setColor(IndexedColors.RED.getIndex());
                break;

            case StyleCellule.POLICE_VERTE:
                maPolice.setColor(IndexedColors.GREEN.getIndex());
                break;

        }

    }
}
