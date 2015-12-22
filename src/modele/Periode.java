package modele;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe modelisant une periode.
 * Une periode est definie par deux dates: une de debut, une de fin.
 * A titre d'exemple, un semestre est un enchainement de periode de cours et de vacances.
 * Il est important de noter qu'ici une periode DOIT commencer par un lundi et se terminer par un dimanche
 */
public class Periode {

    boolean vacance;
    DateTime debut; //lundi debut
    DateTime fin; //vendredi fin

    /**
     * Constructeur unique, permettant de definir une periode a l'aide des attributs qui lui sont propre
     *
     * @param vacance, booleen permettant de specifier si la periode est une periode de cours ou de vacance
     * @param debut,   date de debut de la periode (rappel: doit etre un lundi)
     * @param fin,     date de fin de la periode (rappel: doit etre un dimanche)
     */
    public Periode(boolean vacance, Date debut, Date fin) {
        this.vacance = vacance;
        this.debut = new DateTime(debut);
        this.fin = new DateTime(fin);
    }

    /**
     * fonction permettant d'obtenir la valeur du booleen vacance
     *
     * @return true si c'est une periode de vacance, false sinon
     */
    public boolean getVacance() {
        return vacance;
    }

    /**
     * fonction permettant d'obtenir le nombre de semaine de cours au sein d'une periode
     *
     * @return nbSemaineScolaire, le nombre de semaine de cours
     */
    public long getNombreSemaineScolaire() {
        return Weeks.weeksBetween(debut, fin).getWeeks() + 1;
    }

    /**
     * fonction permettant d'obtenir le numero de la semaine de la date de debut
     *
     * @return semaineDebut, le numero de la semaine
     */
    public int getNumeroSemaineDebut() {

        return debut.weekOfWeekyear().get();
    }

    /**
     * fonction permettant d'obtenir la date de debut, formatee en chaine de caracteres
     *
     * @return dateEnChaine, la date de debut sous la fomre d/m/yyyy
     * par exemple: 1/9/2015 pour le 1er septembre 2015
     */
    public String getDebutenChaine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/YYYY");
        return dateFormat.format(debut.toDate());
    }

    /**
     * fonction permettant d'obtenir la date de Debut d'une periode
     *
     * @return debut, la date de debut
     */
    public DateTime getDebut() {
        return debut;
    }

    /**
     * fonction permettant d'obtenir la date de Fin d'une periode
     *
     * @return debut, la date de fin
     */
    public DateTime getFin() {
        return fin;
    }

}
