package maskipli.id.simplebidirectionalcalendar.util;

import maskipli.id.simplebidirectionalcalendar.global.Year;

/**
 * @author nurhidayat
 * @since 7/17/17.
 */

public class Utils {

    /**
     * getMonth from child position(month position)
     *
     * @param position
     * @return
     */
    public static int positionToMonth(int position) {
        return (position % 12) + 1;
    }


    /**
     * getMonth to position from data year & month
     *
     * @param month
     * @param year
     * @return
     */

    public static int monthToPosition(int month, int year) {
        int yearDiff = year - Year.MIN;
        return (12 * yearDiff + month - 1);
    }

    /**
     * getYear to position from indexMonth
     * @param indexMonth
     * @return
     */
    public static int positionMonthToYear(int indexMonth) {
        return indexMonth / 12;
    }


    /**
     * year to position index
     * @param year
     * @return
     */
    public static int yearToPosition(int year) {
        return (year - Year.MIN_INDEX_YEAR) - 1;
    }

    public static int positionToYear(int position) {
        return position + Year.MIN;
    }
}
