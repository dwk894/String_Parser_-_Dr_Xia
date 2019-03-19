/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

package source_code;

import java.util.regex.Pattern;

public class StringChecker {
    private String original;
    private static final int MAX_BRIGHTNESS = 10;  // Minimum is default to be zero.

    public StringChecker(String originalString) {
        original = originalString;

        // Remove all the whitespaces from the "original".
        Pattern whitespaces = Pattern.compile("\\s+");
        original = whitespaces.matcher(original).replaceAll("");

        // If "original" does not have ';' at the end, add a ';' at the end before further checking.
        if (original.charAt(original.length() - 1) != ';') { original += ';'; }

        // Change ",;" to ";" -> patterns like "08:00-09:00,;".
        original = original.replaceAll(",;", ";");

        // Remove all the non-digit characters at the beginning of the string.
        while ((!original.isEmpty()) && (original.charAt(0) < 48 || original.charAt(0) > 57)) { original = original.substring(1); }
    }

    public String GetFinalString() { return original; }

    private static boolean isAllDigits(String s) {
        /** Check if string "s" contains only digits.
            @return : true (all digits) or false (otherwise)
        */
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) { return false; }
        }
        return true;
    }

    private static int count(String s, char ch) {
        /** Count the number of occurences of the character in the string.
            @return : Integer (number of occurences)
        */
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) { result++; }
        }
        return result;
    }

    private static boolean isValidHour(String expression) {
        /** Check if the "expression" is a valid hour (0 to 24).
            @return : true (valid) or false (invalid)
        */
        if (!isAllDigits(expression)) { return false; }
        return Integer.parseInt(expression) >= 0 && Integer.parseInt(expression) <= 24;
    }

    private static boolean isValidMinute(String expression) {
        /** Check if the "expression" is a valid minute (0 to 60).
            @return : true (valid) or false (invalid)
        */
        if (!isAllDigits(expression)) { return false; }
        return Integer.parseInt(expression) >= 0 && Integer.parseInt(expression) <= 60;
    }

    private static boolean isValidTime(String expression) {
        /** Check if the "expression" is a valid time ("hh:mm" format).
            @return : true (valid) or false (invalid)
        */
        if (count(expression, ':') != 1) { return false; }
        if (!isValidHour(expression.split(":")[0])) { return false; }
        if (!isValidMinute(expression.split(":")[1])) { return false; }
        return true;
    }

    private static boolean isValidPeriod(String expression) {
        /** Check if the "expression" is a valid time period ("hh:mm-hh:mm" format).  The second time (after hyphen) must be later than the first time (before hyphen).
            @return : true (valid) or false (invalid)
        */
        if (count(expression, '-') != 1) { return false; }
        String first = expression.split("-")[0];
        String second = expression.split("-")[1];
        if (!isValidTime(first) || !isValidTime(second)) { return false; }
        
        // Check if "second" is later than "first".
        int firstHour = Integer.parseInt(first.substring(0, first.indexOf(':')));
        int secondHour = Integer.parseInt(second.substring(0, second.indexOf(':')));
        if (firstHour < secondHour) { return true; }
        else if (firstHour > secondHour) { return false; }
        else {
            int firstMinute = Integer.parseInt(first.substring(first.indexOf(':') + 1));
            int secondMinute = Integer.parseInt(second.substring(second.indexOf(':') + 1));
            return firstMinute < secondMinute;
        }
    }

    public boolean Check() {
        // Split the "original" into tokens.
        String[] tokens = original.split(";");

        // If the "original" string is empty, then it is invalid input.
        if (tokens.length == 0) {
            return false;
        }

        // Check tokens one by one.
        String previousEndHour = null, previousEndMinute = null;
        for (int i = 0; i < tokens.length; i++) {
            if (i == 0) {  // The first token is the switch number, which is a special token.
                if (!isAllDigits(tokens[i]) || (!tokens[i].equals("1") && !tokens[i].equals("2") && !tokens[i].equals("3"))) { return false; }
            } else {
                // The current token should not have two or more commas.
                if (count(tokens[i], ',') > 1) { return false; }

                if (count(tokens[i], ',') == 0) { tokens[i] += ",0";  /* "0" is just a dummy value. */ }

                // Split the current token into time period and brightness.
                String timePeriod = tokens[i].split(",")[0];
                String bright = tokens[i].split(",")[1];

                if (!isValidPeriod(timePeriod)) { return false; }

                if (!isAllDigits(bright) || Integer.parseInt(bright) < 0 || Integer.parseInt(bright) > MAX_BRIGHTNESS) { return false; }

                if (previousEndHour != null && previousEndMinute != null) {
                    // Check if the current start time is later than previous end time.
                    int previousHour = Integer.parseInt(previousEndHour);
                    int previousMinute = Integer.parseInt(previousEndMinute);
                    int currentHour = Integer.parseInt(timePeriod.substring(0, timePeriod.indexOf(':')));
                    int currentMinute = Integer.parseInt(timePeriod.substring(timePeriod.indexOf(':') + 1, timePeriod.indexOf('-')));
                    if (previousHour > currentHour) { return false; }
                    if (previousHour == currentHour && previousMinute >= currentMinute) { return false; }
                }

                // Update "previousEndHour" and "previousEndMinute".
                String currentEndTime = timePeriod.substring(timePeriod.indexOf('-') + 1);
                previousEndHour = currentEndTime.substring(0, currentEndTime.indexOf(':'));
                previousEndMinute = currentEndTime.substring(currentEndTime.indexOf(':') + 1);
            }
        }
        return true;
    }
}