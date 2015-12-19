package modele;

import java.util.ArrayList;

/**
 * Created by germa on 16/12/2015.
 */
public class UniteEnseignement {

    private String nomUnite;
    private int poidsUnite;
    private int ects;
    private ArrayList<Enseignement> enseignementList = new ArrayList();


    public UniteEnseignement(String nomUnite, int poidsUnite, int ects) {
        this.nomUnite = nomUnite;
        this.poidsUnite = poidsUnite;
        this.ects = ects;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public ArrayList<Enseignement> getEnseignementList() {
        return enseignementList;
    }

    public void addEnseignement(Enseignement courant) {
        enseignementList.add(courant);
    }

    public int getVariete() {
        int total = 0;
        for (int icpt = 0; icpt < enseignementList.size(); icpt++) {
            total += enseignementList.get(icpt).getVariete();
        }
        return total;
    }

}
