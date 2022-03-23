package subway.domain;

import java.util.Scanner;

public class User {
    private String input;

    public String userInput(Scanner scanner){
        input = scanner.nextLine();
        return input;
    }
}
