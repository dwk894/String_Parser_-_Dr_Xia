/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-27. */

package test_code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import source_code.ScheduleDetail;
import source_code.StringChecker;
import source_code.StringParser;

public class Test {
    public static void main(String[] args) throws IOException {
        FileInputStream inputFile = new FileInputStream("testInputStrings.txt");
        Scanner scanner = new Scanner(inputFile);
        FileOutputStream outputFile = new FileOutputStream("testResult.txt");
        PrintWriter writer = new PrintWriter(outputFile);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            StringChecker currentChecker = new StringChecker(current);
            boolean result = currentChecker.Check();
            writer.println(result);
            if (result) {
                List<ScheduleDetail> res = new StringParser(currentChecker.GetFinalString()).ParseString();
                for (int j = 0; j < res.size(); j++) {
                    res.get(j).Output();
                }
            }
        }
        writer.flush();
        writer.close();
        scanner.close();
        outputFile.close();
        inputFile.close();
    }
}