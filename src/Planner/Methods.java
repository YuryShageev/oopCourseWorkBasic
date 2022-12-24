package Planner;

import java.util.Scanner;

public interface Methods{

    public void fillInTask(Scanner scanner) throws ServiceCheckException;

    public void obtainTask(Scanner scanner);

    public void removeTask(Scanner scanner);
}