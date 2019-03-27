/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-27. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-27. */

package source_code;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.IOException;

public class ScheduleDetail {
    private static final DateTimeFormatter format = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(':')
            .appendMinuteOfHour(2).toFormatter();

    private int switchNumber;
    private DateTime startTime;
    private DateTime endTime;
    private String switchString;
    private int brightness;
    private static int lastBrightness = 0;

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public String getSwitchString() {
        return switchString;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getSwitchNumber() {
        return switchNumber;
    }

    public void setStartTime(DateTime st) {
        startTime = st;
    }

    public void setEndTime(DateTime en) {
        endTime = en;
    }

    public void setSwitchString(String sw) {
        switchString = sw;
    }

    public void setBrightness(int br) {
        brightness = br;
    }

    public void setSwitchNumber(int num) {
        switchNumber = num;
    }

    // Constructors

    // If blankPeroid is true, then set switchString to "0,0,0"
    // First version: no brightness pre-set.
    public ScheduleDetail(int switchNum, String timePeriod, boolean blankPeroid) {
        switchNumber = switchNum;
        String timeStart = timePeriod.substring(0, timePeriod.indexOf('-'));
        String timeEnd = timePeriod.substring(timePeriod.indexOf('-') + 1);

        startTime = DateTime.parse(timeStart, format);
        endTime = DateTime.parse(timeEnd, format);

        // Make change here 03/27/19 -> From "0, 0, 0" to "0".
        if (blankPeroid) {
            switchString = "0";
            brightness = lastBrightness;
        }
    }

    // Second version: brightness pre-set.
    public ScheduleDetail(int switchNum, String timePeriod, int br) {
        switchNumber = switchNum;
        String timeStart = timePeriod.substring(0, timePeriod.indexOf('-'));
        String timeEnd = timePeriod.substring(timePeriod.indexOf('-') + 1);

        DateTimeFormatter format = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(':')
                .appendMinuteOfHour(2).toFormatter();

        startTime = DateTime.parse(timeStart, format);
        endTime = DateTime.parse(timeEnd, format);
        // Make change here 1/20/19
        switchString = br == 0 ? "2" : "1"; // It has no way to be blank period if brightness is set.
        brightness = br;
        lastBrightness = brightness;
    }

    // Output
    public void Output() throws IOException {
        System.out.println("switchNumber: " + String.valueOf(switchNumber));
        System.out.println("startTime: " + startTime.toString(format));
        System.out.println("endTime: " + endTime.toString(format));
        System.out.println("switchString: " + switchString);
        System.out.println("brightness: " + brightness);
        System.out.println();
    }
}