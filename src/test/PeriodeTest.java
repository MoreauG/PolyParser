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
    @Test
    public void testIsVacance() {

    }

    @Test
    public void testGetNombreJour() {

        Periode maPeriode;

        date1 = new GregorianCalendar(2001, 1, 31, 23, 59).getTime();
        date2 = new GregorianCalendar(2015, 12, 17, 20, 30).getTime();

        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 5433, maPeriode.getNombreJour());

        date1 = new GregorianCalendar(2015, 1, 31, 23, 59).getTime();
        date2 = new GregorianCalendar(2015, 12, 17, 20, 30).getTime();

        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 320, maPeriode.getNombreJour());

        date1 = new GregorianCalendar(2015, 9, 15, 23, 59).getTime();
        date2 = new GregorianCalendar(2015, 11, 10, 20, 30).getTime();

        maPeriode = new Periode(false, date1, date2);
        TestCase.assertEquals("Valeur attendue:", 56, maPeriode.getNombreJour());
    }

    @Test
    public void testGetNombreSemaine() {

    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    private Date date1;
    private Date date2;
}
