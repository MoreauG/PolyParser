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

    public ArrayList<Periode> getPeriodeList() {
        return enseignementList;
    }

    public int getNombreSemaineTotal() {
        int total = 0;
        for (int icpt = 0; icpt < enseignementList.size(); icpt++) {
            total += enseignementList.get(icpt).getNombreSemaineScolaire();
        }
        return total;
    }
}
