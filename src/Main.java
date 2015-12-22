import modele.*;
import parser.MaquetteEcriture;

import java.util.GregorianCalendar;

public class Main {


    public static void main(String[] args) {

        Maquette maMaquette = new Maquette("DI3S5", 4.5, 4, 2, 2, 2);

        Temporalite maTempo = new Temporalite();
        maTempo.ajouterPeriode(new GregorianCalendar(2015, 8, 7).getTime(), new GregorianCalendar(2015, 9, 25).getTime());
        maTempo.ajouterPeriode(new GregorianCalendar(2015, 9, 26).getTime(), new GregorianCalendar(2015, 10, 1).getTime());
        maTempo.ajouterPeriode(new GregorianCalendar(2015, 10, 2).getTime(), new GregorianCalendar(2015, 11, 20).getTime());
        maTempo.ajouterPeriode(new GregorianCalendar(2015, 11, 21).getTime(), new GregorianCalendar(2016, 0, 3).getTime());
        maTempo.ajouterPeriode(new GregorianCalendar(2016, 0, 4).getTime(), new GregorianCalendar(2016, 0, 17).getTime());
        maMaquette.setPlanning(maTempo);


        UniteEnseignement Prog = new UniteEnseignement("Prog");
        UniteEnseignement Math = new UniteEnseignement("Math");

        Professeur Lente = new Professeur("Christophe Lente", 1, 2, 3, 4, 0);
        Professeur Ahmed = new Professeur("Christophe Ahmed", 8, 8, 8, 8, 0);
        Professeur Andre = new Professeur("Christophe Andre", 8, 8, 8, 0, 8);
        Professeur Philippe = new Professeur("Christophe Phlippe", 8, 8, 8, 4, 8);

        Enseignement stat = new Enseignement(0, "Statistiques", Lente);
        Enseignement Algebre = new Enseignement(0, "Algebre", Lente);
        Enseignement C = new Enseignement(0, "C", Andre);
        Enseignement java = new Enseignement(0, "Java", Andre);

        Algebre.ajoutProfesseur(Philippe);
        java.ajoutProfesseur(Philippe);

        Math.addEnseignement(stat);
        Math.addEnseignement(Algebre);
        Prog.addEnseignement(C);
        Prog.addEnseignement(java);

        maMaquette.addUniteEnseignement(Math);
        maMaquette.addUniteEnseignement(Prog);
        maMaquette.addUniteEnseignement(Math);

        MaquetteEcriture monEcriture = new MaquetteEcriture(maMaquette);

        monEcriture.ecrireMaquette();
    }

}
