package planner;

import java.util.Scanner;

public interface Methods{

    void fillInTask(Scanner scanner) throws ServiceCheckException;

    void obtainTask(Scanner scanner);

    void removeTask(Scanner scanner);
}