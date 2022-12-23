package Planner;

import java.util.Scanner;

public interface Methods{

    ConstantInfo setRepetition(Scanner scanner);

    String inputTask(Scanner scanner);

    String inputDescription(Scanner scanner);

    String chooseType(Scanner scanner);

    void printTypeMenu();
    void printMenu();
}