/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-27. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-27. */

package source_code;

import java.util.ArrayList;
import java.util.List;

import helper.TimeAnalyzer;

public class StringParser {
    static private final String DEFAULT_BRIGHTNESS = "3";
    private int switchNumber;
    private String s;

    // Constructor
    public StringParser(String str) {
        switchNumber = Integer.parseInt(str.substring(0, str.indexOf(';')));
        s = str.substring(str.indexOf(';') + 1);
    }

    public List<ScheduleDetail> ParseString() {
        String currentToken;
        String currentTimePeriod;
        String currentBrightness;

        List<ScheduleDetail> result = new ArrayList<ScheduleDetail>();

        TimeAnalyzer t = new TimeAnalyzer();

        while (!s.isEmpty()) {
            currentToken = s.substring(0, s.indexOf(';'));
            s = s.substring(s.indexOf(';') + 1);

            if (currentToken.contains(",")) {
                currentTimePeriod = currentToken.substring(0, currentToken.indexOf(','));
                currentBrightness = currentToken.substring(currentToken.indexOf(',') + 1);
            } else {
                currentTimePeriod = currentToken;
                currentBrightness = DEFAULT_BRIGHTNESS;
            }

            if ((!currentTimePeriod.substring(0, currentTimePeriod.indexOf('-')).equals(t.GetLastEndTime()))
                    && (!currentTimePeriod.substring(0, currentTimePeriod.indexOf('-')).equals(t.LastEndTimePlus()))) {
                result.add(new ScheduleDetail(switchNumber, t.CreateBlankPeroid(currentTimePeriod), true));
            }

            result.add(new ScheduleDetail(switchNumber, currentTimePeriod, Integer.parseInt(currentBrightness)));

            t.ChangeLastEndTime(currentTimePeriod);
        }

        if (!t.GetLastEndTime().equals("23:59")) {
            result.add(new ScheduleDetail(switchNumber, t.LastTimePeriod(), true));
        }

        return result;
    }
}