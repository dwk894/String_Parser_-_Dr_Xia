// No change in this file.

package helper;

public class TimeAnalyzer {
    private String lastEndTime;

    public TimeAnalyzer() {
        lastEndTime = "00:00";
    }

    public String GetLastEndTime() {
        return lastEndTime;
    }

    public String CreateBlankPeroid(String timePeriod) {
        if (!lastEndTime.equals("00:00")) {
            lastEndTime = LastEndTimePlus();
        }

        // Generate the start time.
        int _startHour = Integer.parseInt(lastEndTime.substring(0, lastEndTime.indexOf(':')));
        int _startMinute = Integer.parseInt(lastEndTime.substring(lastEndTime.indexOf(':') + 1));

        String _hourStart = _startHour < 10 ? '0' + Integer.toString(_startHour) : Integer.toString(_startHour);
        String _minuteStart = _startMinute < 10 ? '0' + Integer.toString(_startMinute) : Integer.toString(_startMinute);

        // Generate the end time.
        int _endHour = Integer.parseInt(timePeriod.substring(0, timePeriod.indexOf('-')).substring(0,
                timePeriod.substring(0, timePeriod.indexOf('-')).indexOf(':')));
        int _endMinute = Integer.parseInt(timePeriod.substring(0, timePeriod.indexOf('-'))
                .substring(timePeriod.substring(0, timePeriod.indexOf('-')).indexOf(':') + 1));

        // Generate "the previous minute" as the end time.
        if (_endMinute == 0) {
            _endHour--;
            _endMinute = 59;
        } else {
            _endMinute--;
        }

        String _hourEnd = _endHour < 10 ? '0' + Integer.toString(_endHour) : Integer.toString(_endHour);
        String _minuteEnd = _endMinute < 10 ? '0' + Integer.toString(_endMinute) : Integer.toString(_endMinute);

        lastEndTime = _hourEnd + ':' + _minuteEnd;

        return _hourStart + ':' + _minuteStart + '-' + _hourEnd + ':' + _minuteEnd;
    }

    // Change the lastEndTime.
    public void ChangeLastEndTime(String timePeriod) {
        lastEndTime = timePeriod.substring(timePeriod.indexOf('-') + 1);
    }

    // Generate the last time period of the day.
    public String LastTimePeriod() {
        String finalTime = "24:00-24:00"; // Generate the same format to re-use the function.
        return CreateBlankPeroid(finalTime);
    }

    // Increment the last time period by one minute.
    public String LastEndTimePlus() {
        int _hour = Integer.parseInt(lastEndTime.substring(0, lastEndTime.indexOf(':')));
        int _minute = Integer.parseInt(lastEndTime.substring(lastEndTime.indexOf(':') + 1));

        if (_minute == 59) {
            _hour++;
            _minute = 0;
        } else {
            _minute++;
        }

        String resultHour = _hour < 10 ? '0' + Integer.toString(_hour) : Integer.toString(_hour);
        String resultMinute = _minute < 10 ? '0' + Integer.toString(_minute) : Integer.toString(_minute);

        return resultHour + ":" + resultMinute;
    }
}