package modele;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe permettant de definir une temporalitee
 * Une temporalitee est une liste de Periode.
 * Cette classe est utile pour modeliser l'enchainement de periode de vacances et de cours au sein d'un semestre
 */
public class Temporalite {

    private ArrayList<Periode> periodeListe = new ArrayList();

    /**
     * fonction permettant l'ajout d'une periode au sein de la temporalite
     * C'est dans cette fonction qu'est determine si la periode est une periode de vacance ou non.
     * Ceci est fait en fonction de l'ordre d'ajout.
     * On suppose ici que les periodes de cours et de vacances sont alternnes et que la premiere periode est une periode de cours.
     *
     * @param debut, la date de debut de la periode
     * @param fin,   la date de fin de la periode
     */
    public void ajouterPeriode(Date debut, Date fin) {
        if (periodeListe.size() % 2 == 0) {
            periodeListe.add(new Periode(false, debut, fin));
        } else {
            periodeListe.add(new Periode(true, debut, fin));
        }
    }

    /**
     * fonction permettant d'obtenir la liste des periodes constituant notre Temporalite
     *
     * @return periodeListe, la liste des periodes constituant la temporalite
     */
    public ArrayList<Periode> getPeriodeListe() {
        return periodeListe;
    }

    /**
     * fonction retournant l'annee de la date de debut de notre temporalite
     * On suppose ici que l'acces se fait avec au moins une periode
     *
     * @return anneeDebut, l'annee sous forme d'entier de la date de debut
     */
    public int getAnneeDebut() {
        return periodeListe.get(0).getDebut().year().get();
    }

    /**
     * fonction retournant l'annee de la date de fin de notre temporalite
     * On suppose ici que l'acces se fait avec au moins une periode
     *
     * @return anneeDebut, l'annee sous forme d'entier de la date de fin
     */
    public int getAnneeFin() {
        return periodeListe.get(periodeListe.size() - 1).getDebut().year().get();
    }
}
