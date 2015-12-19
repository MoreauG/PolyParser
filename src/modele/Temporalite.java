package modele;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by germa on 17/12/2015.
 */
public class Temporalite {

    private ArrayList<Periode> enseignementList = new ArrayList();

    public void ajouterPeriode(Date debut, Date fin) {
        if (enseignementList.size() % 2 == 0) {
            enseignementList.add(new Periode(false, debut, fin));
        } else {
            enseignementList.add(new Periode(true, debut, fin));
        }
    }

    public ArrayList<Periode> getEnseignementList() {
        return enseignementList;
    }
}
