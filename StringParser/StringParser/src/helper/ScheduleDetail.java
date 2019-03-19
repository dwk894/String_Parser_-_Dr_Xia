/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

package helper;

import org.joda.time.DateTime;

// This class is just for the test purpose.
public class ScheduleDetail {
    private int switchNumber;  // Values can be 1, 2 or 3.
    private DateTime startTime, endTime;
    String switchString;
    private int brightness;
    public int getSwitchNumber() { return switchNumber; }
    public void setSwitchNumber(int num) { switchNumber = num; }
    public DateTime getStartTime() { return startTime; }
    public void setStartTime(DateTime start) { startTime = start; }
    public DateTime getEndTime() { return endTime; }
    public void setEndTime(DateTime end) { endTime = end; }
    public String getSwitchString() { return switchString; }
    public void setSwitchString(String switchStr) { switchString = switchStr; }
    public int getBrightness() { return brightness; }
    public void setBrightness(int bright) { brightness = bright; }
}