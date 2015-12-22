package modele;


import java.util.ArrayList;

/**
 * Classe permettant la collection des maquettes
 */
public class Scolarite {
    private ArrayList<Maquette> listeMaquette = new ArrayList();

    /**
     * fonction permettant d'ajouter une maquette a ma collection
     *
     * @param ajout, la maquette a ajouter
     */
    public void ajouterMaquette(Maquette ajout) {
        listeMaquette.add(ajout);
    }

    /**
     * fonction permettant d'obtenir la liste des maquettes
     *
     * @return listeMaquette, la liste des maquettes
     */
    public ArrayList<Maquette> getListeMaquette() {
        return listeMaquette;
    }
}
