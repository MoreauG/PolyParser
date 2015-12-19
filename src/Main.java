import modele.Enseignement;
import modele.Maquette;
import modele.Professeur;
import modele.UniteEnseignement;
import parser.MaquetteEcriture;

public class Main {


    public static void main(String[] args) {

        Maquette maMaquette = new Maquette("DI3S5");
        maMaquette.setNombreCreneauParJour(4);
        maMaquette.setNombreJourDansUneSemaine(4.5);


        Professeur Lente = new Professeur("Christophe Lente", 1, 2, 3, 4, 0);
        Professeur Ahmed = new Professeur("Christophe Ahmed", 8, 8, 8, 8,0);
        Professeur Andre = new Professeur("Christophe Andre", 8, 8, 8, 0, 8);
        Professeur Philippe = new Professeur("Christophe Phlippe", 8, 8, 8, 4, 8);

        Enseignement stat = new Enseignement(0, "Statistiques", Lente);
     //   Enseignement proba = new Enseignement(0, "Probabilite", Lente);

       // proba.ajoutProfesseur(Philippe);
      //  proba.ajoutProfesseur(Andre);
        Enseignement Algebre = new Enseignement(0,"Algebre",Lente);
        Algebre.ajoutProfesseur(Philippe);


        Enseignement C = new Enseignement(0, "C", Andre);
        Enseignement java = new Enseignement(0, "Java", Andre);
        java.ajoutProfesseur(Philippe);


        UniteEnseignement Prog = new UniteEnseignement("Prog", 60, 5);


        UniteEnseignement Math = new UniteEnseignement("Math", 60, 5);
        Math.addEnseignement(stat);
    //    Math.addEnseignement(proba);
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
