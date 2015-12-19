package test;

import groovy.util.GroovyTestCase;
import junit.framework.TestCase;
import modele.Enseignement;
import modele.Professeur;
import org.junit.Test;

/**
 * Created by germa on 17/12/2015.
 */
public class EnseignementTest extends GroovyTestCase {
        @Test
        public void testGetVariete() {

        Professeur prof_1 = new Professeur("prof_1", 10, 2, 4, 2, 0);
        Professeur prof_2 = new Professeur("prof_2", 0, 0, 3, 0, 2);
        Professeur prof_3 = new Professeur("prof_3", 1, 1, 0, 0, 2);

        Enseignement stat = new Enseignement(0, "Statistiques", prof_1);
        TestCase.assertEquals("Valeur attendue:", 4, stat.getVariete());

        stat.ajoutProfesseur(prof_2);
        TestCase.assertEquals("Valeur attendue:", 6, stat.getVariete());

        stat.ajoutProfesseur(prof_3);
        TestCase.assertEquals("Valeur attendue:", 9, stat.getVariete());

    }

}
