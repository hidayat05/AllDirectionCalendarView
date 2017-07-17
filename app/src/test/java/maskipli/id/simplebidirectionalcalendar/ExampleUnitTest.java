package maskipli.id.simplebidirectionalcalendar;

import org.junit.Test;

import maskipli.id.simplebidirectionalcalendar.global.Year;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testRangeMonth() throws Exception {
        int bulan = 1;
        int tahun = 2030;

        //TODO kecuali tahun 1970 index parent jadi -1
        //


        int deffYear = tahun - Year.MIN;
        int indexTahun = deffYear - 1; // 46
        int indexBulan = 12 * deffYear + bulan - 1; // 570
        System.out.println(indexTahun);
        System.out.println(indexBulan);

        int parentIndex = (indexBulan / 12) - 1;
        System.out.println(parentIndex );
    }


    @Test
    public void testMonthPosition() throws Exception {
        int indexMnth = 0;
        System.out.println((indexMnth % 12) + 1);
    }

    @Test
    public void testYearPosition() throws Exception {
        int indexYear = 2;
        System.out.println(indexYear + Year.MIN);
    }

    @Test
    public void getPositionMonth() throws Exception {
        int month = 12;
        int year = 1970;
        int yearDiff = year - Year.MIN;
        System.out.println(12 * yearDiff + month - 1);
    }

    @Test
    public void getPositionYear() throws Exception {
        int year = 1970;
        int yearDiff = year - Year.MIN_INDEX_YEAR;
        System.out.println(yearDiff );
    }

    @Test
    public void sample() throws Exception {
        int maxMonths = Year.MAX * 12;
        int minMonths = Year.MIN * 12;
        System.out.println(maxMonths - minMonths);
    }


}

