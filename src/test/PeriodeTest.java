package test;

import groovy.util.GroovyTestCase;
import junit.framework.TestCase;
import modele.Periode;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by germa on 17/12/2015.
 */
public class PeriodeTest extends GroovyTestCase {

    private Date date1;
    private Date date2;
    Periode maPeriode;


    @Test
    public void testGetNombreSemaine() {

        date1 = new GregorianCalendar(2015, 8, 7).getTime();
        date2 = new GregorianCalendar(2015, 8, 13).getTime();
        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 1, maPeriode.getNombreSemaineScolaire());

        date2 = new GregorianCalendar(2016, 0, 10).getTime();
        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 18, maPeriode.getNombreSemaineScolaire());

        date1=new GregorianCalendar(2015, 11, 21).getTime();
        date2=new GregorianCalendar(2016, 0, 3).getTime();
        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 2, maPeriode.getNombreSemaineScolaire());

        date1=new GregorianCalendar(2015, 10, 2).getTime();
        date2=new GregorianCalendar(2015, 11, 20).getTime();
        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 7, maPeriode.getNombreSemaineScolaire());
    }

    public void testGetNumeroSemaineDebut() throws Exception {

        date1 = new GregorianCalendar(2015, 0, 1).getTime();
        maPeriode = new Periode(false, date1, date1);
        TestCase.assertEquals("Valeur attendue:", 1, maPeriode.getNumeroSemaineDebut());

        date1 = new GregorianCalendar(2015, 8, 7).getTime();
        maPeriode = new Periode(false, date1, date1);
        TestCase.assertEquals("Valeur attendue:", 37, maPeriode.getNumeroSemaineDebut());


    }
}
