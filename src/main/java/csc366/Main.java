package csc366;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        for (;;) {
            String input = scanner.nextLine();
            Optional<BlockAction> action = parser.parse(input);
        }
    }
}
