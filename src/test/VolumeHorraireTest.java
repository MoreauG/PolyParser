package test;

import groovy.util.GroovyTestCase;
import junit.framework.TestCase;
import modele.VolumeHorraire;
import org.junit.Test;

/**
 * Created by germa on 16/12/2015.
 */
public class VolumeHorraireTest extends GroovyTestCase {
        @Test
        public void testVariete() throws Exception {
        VolumeHorraire monVolume = new VolumeHorraire(2, 2, 2, 2, 2);
        TestCase.assertEquals("Valeur attendue:", 5, monVolume.getVariete());

        monVolume = new VolumeHorraire(2, 0, 1, 1, 2);
        TestCase.assertEquals("Valeur attendue:", 4, monVolume.getVariete());

        monVolume = new VolumeHorraire(2, 0, 0, 1, 2);
        TestCase.assertEquals("Valeur attendue:", 3, monVolume.getVariete());

        monVolume = new VolumeHorraire(2, 0, 0, 0, 2);
        TestCase.assertEquals("Valeur attendue:", 2, monVolume.getVariete());

        monVolume = new VolumeHorraire(0, 0, 0, 0, 2);
        TestCase.assertEquals("Valeur attendue:", 1, monVolume.getVariete());
    }

        @Test
        public void testPresence() throws Exception {

        VolumeHorraire monVolume = new VolumeHorraire(2, 2, 2, 2, 2);

        boolean myboolean = true;
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.cmPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.tdPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.tpPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.ccPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.etPresent());

        monVolume = new VolumeHorraire(0, 0, 0, 0, 0);
        myboolean = false;
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.cmPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.tdPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.tpPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.ccPresent());
        TestCase.assertEquals("Valeur attendue:", myboolean, monVolume.etPresent());
    }

}
