package Planner;

import java.time.LocalDate;

public class ServiceCheckException extends Exception {
    public void UnsupportedOperationException() {

    }

    public void DateTimeException(LocalDate localDate) {
        System.out.println("Что-то не так с датой!");
    }
}
