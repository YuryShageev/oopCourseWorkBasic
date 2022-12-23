import Planner.Service;

import java.util.Scanner;



public class Main {



    public static void main(String[] args) {
        System.out.println("Hi");
        Service service = new Service();

        //Scanner

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                service.printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            service.fillInTask(scanner);
                            break;
                        case 2:
                            service.removeTask(scanner);

                            break;
                        case 3:
                            service.obtainTask(scanner);

                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }




}
