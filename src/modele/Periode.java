package modele;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Periode {

    boolean vacance;

    DateTime debut; //lundi debut
    DateTime fin; //vendredi fin

    public Periode(boolean vacance, Date debut, Date fin) {
        this.vacance = vacance;
        this.debut = new DateTime(debut);
        this.fin = new DateTime(fin);
    }

    public boolean isVacance() {
        return vacance;
    }

    public long getNombreSemaineScolaire() {
        return Weeks.weeksBetween(debut, fin).getWeeks() + 1;
    }

    public int getNumeroSemaineDebut() {

        return debut.weekOfWeekyear().get();
    }

    public String getDebutenChaine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/YYYY");
        return dateFormat.format(debut.toDate());
    }

    public int getNumeroSemaineFin() {
        return fin.weekOfWeekyear().get();
    }

    public DateTime getDebut() {
        return debut;
    }

    public DateTime getFin() {
        return fin;
    }

}
