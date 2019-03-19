/** Created by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 2019-03-19. */

package test_code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import source_code.StringChecker;

public class Test {
    public static void main(String[] args) throws IOException {
        FileInputStream inputFile = new FileInputStream("testInputStrings.txt");
        Scanner scanner = new Scanner(inputFile);
        FileOutputStream outputFile = new FileOutputStream("testResult.txt");
        PrintWriter writer = new PrintWriter(outputFile);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            boolean result = new StringChecker(current).Check();
            writer.println(result);
        }
        writer.flush();
        writer.close();
        scanner.close();
        outputFile.close();
        inputFile.close();
    }
}