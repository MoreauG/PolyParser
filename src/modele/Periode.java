package modele;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by germa on 17/12/2015.
 */
public class Periode {
    boolean vacance;
    Date debut; //1er lundi
    Date fin;

    public Periode(boolean vacance, Date debut, Date fin) {
        this.vacance = vacance;
        this.debut = debut;
        this.fin = fin;
    }

    public boolean isVacance() {
        return vacance;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public int getNombreJour() {
        long tempsFin = fin.getTime();
        long tempsDebut = debut.getTime();

        return (int) ((tempsFin - tempsDebut) / (1000 * 60 * 60 * 24))+1;
    }

    public int getNombreSemaine() {
        return getNombreJour() / 7;
    }
}
