package parser;

import modele.Scolarite;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Classe permettant d'ecrire notre fichier excel de sortie contenant la liste des maquettes formatees
 */
public class FichierEcriture {
    private Workbook fichierExcel;
    private Scolarite modeleRempli;
    private final String NOM_FICHIER_SORTI = "PolytechPlanning";

    /**
     * Constructeur unique, prend en parametre la liste des maquettes (sous forme d'une Scolarite)
     *
     * @param modeleRempli, le modele comprenant la liste des maquettes remplies
     */
    public FichierEcriture(Scolarite modeleRempli) {
        this.modeleRempli = modeleRempli;
        fichierExcel = new XSSFWorkbook();
    }

    /**
     * fonction permettant d'ecrire notre fichier de sortie
     *
     * @throws IOException si le fichier ne s'est pas ouvert correctement
     */
    public void ecrireFichier() throws IOException {

        FileOutputStream fileOut = new FileOutputStream(NOM_FICHIER_SORTI + ".xlsx");

        for (int icpt = 0; icpt < modeleRempli.getListeMaquette().size(); icpt++) {
            new MaquetteEcriture(modeleRempli.getListeMaquette().get(icpt), fichierExcel).ecrireMaquette();
        }
        fichierExcel.write(fileOut);
        fileOut.close();
    }

}
