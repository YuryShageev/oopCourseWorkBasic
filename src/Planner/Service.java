package Planner;

import java.time.LocalDate;
import java.util.*;

public class Service {
    private final Map<Integer, Task> tasks;
    private ConstantInfo repetitionForDate;

    public Service() {
        tasks = new HashMap<>();
    }

    //The method collector
    public void fillInTask(Scanner scanner) {
        String taskName = inputTask(scanner);
        String description = inputDescription(scanner);
        String type = chooseType(scanner);
        ConstantInfo repetition = setRepetition(scanner);
        LocalDate localDate = inputDate(scanner);
        Task task = new Task(taskName, description, type, repetition, localDate);
        tasks.put(task.getId(), task);
        System.out.println(task);
        if (tasks.containsValue(task)) {
            System.out.println("Задача добавлена");
        } else {
            throw new RuntimeException("Задача не добавлена");
        }
    }

    //Методы для первого пункта меню - Добавить задачу


    public static String inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        return taskName;
    }


    public static String inputDescription(Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        return description;
    }

    public static String chooseType(Scanner scanner) {
        String taskType = "Задача";
        String personalTask = "Личная задача";
        String jobTask = "Рабочая задача";


        printTypeMenu();
        System.out.println("Выберите тип задачи, личная или рабочая: ");

        int menu = scanner.nextInt();

        switch (menu) {
            case 1:
                taskType = personalTask;
                break;
            case 2:
                taskType = jobTask;
                break;
        }

        return taskType;
    }

    public static ConstantInfo setRepetition(Scanner scanner) {
        ConstantInfo constantValue = ConstantInfo.SINGLE;

        System.out.println("Введите частоту повторения:\n SINGLE, \n DAILY, \n WEEKLY, \n MONTHLY, \n ANNUALLY");
        ConstantInfo constantInfo = ConstantInfo.valueOf(scanner.next());
        switch (constantInfo) {
            case SINGLE:
                constantValue = ConstantInfo.SINGLE;
                break;
            case DAILY:
                constantValue = ConstantInfo.DAILY;
                break;
            case WEEKLY:
                constantValue = ConstantInfo.WEEKLY;
                break;
            case MONTHLY:
                constantValue = ConstantInfo.MONTHLY;
                break;
            case ANNUALLY:
                constantValue = ConstantInfo.ANNUALLY;
                break;
        }

        return constantValue;
    }

    public static LocalDate inputDate(Scanner scanner) {
        System.out.println("Введите год события: ");
        int year = scanner.nextInt();
        System.out.println("Введите номер месяца события: ");
        int month = scanner.nextInt();
        System.out.println("Введите дату события: ");
        int day = scanner.nextInt();
        System.out.println();
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate;
    }

    //Menus
    public static void printTypeMenu() {
        System.out.println(1 + ". Личная; " + "\n" + 2 + ". Рабочая;" +
                "\n" + 3 + ". Надоело кнопать");
    }

    public static void printMenu() {
        System.out.println(
                1 + ". Добавить задачу " + "\n" +
                        2 + ". Удалить задачу  " + "\n" +
                        3 + ". Получить задачу на указанный день " + "\n" +
                        0 + ". Выход"
        );
    }

    //Методы для получения задачи
    public void obtainTask(Scanner scanner) {
        System.out.println("Введите год события: ");
        int year = scanner.nextInt();
        System.out.println("Введите номер месяца события: ");
        int month = scanner.nextInt();
        System.out.println("Введите дату события: ");
        int day = scanner.nextInt();
        LocalDate indicatedDate = LocalDate.of(year, month, day);
        for (Map.Entry<Integer, Task> looper : tasks.entrySet()) {
            Task currentTask = looper.getValue();
            LocalDate taskDate = understandRepetition(currentTask, indicatedDate);
            currentTask.setLocalDate(taskDate);
//            if (currentTask.getLocalDate().equals(indicatedDate)) {
//                currentTask;
//            } else {
//                System.out.println("В этот день задач нет");
//            }
            System.out.println(currentTask);
        }
    }

    public static LocalDate understandRepetition(Task task, LocalDate date) {
        LocalDate gettingDate = LocalDate.now();
        if (task.getRepetition().equals(ConstantInfo.DAILY)) {
            if (task.getLocalDate().isBefore(date)) {
                gettingDate = task.getLocalDate().plusDays(1);
            } else if (task.getLocalDate().isAfter(date)) {
                gettingDate = task.getLocalDate().minusDays(1);
            } else if (task.getLocalDate().equals(date)) {
                gettingDate = date;
            }
        }

        if (task.getRepetition().equals(ConstantInfo.WEEKLY)) {
            if (task.getLocalDate().isBefore(date)) {
                gettingDate = task.getLocalDate().plusDays(7);
            } else if (task.getLocalDate().isAfter(date)) {
                gettingDate = task.getLocalDate().minusDays(7);
            } else if (task.getLocalDate().equals(date)) {
                gettingDate = date;
            }
        }

        if (task.getRepetition().equals(ConstantInfo.MONTHLY)) {
            if (task.getLocalDate().isBefore(date)) {
                gettingDate = task.getLocalDate().plusMonths(1);
            } else if (task.getLocalDate().isAfter(date)) {
                gettingDate = task.getLocalDate().minusMonths(1);
            } else if (task.getLocalDate().equals(date)) {
                gettingDate = date;
            }
        }

        if (task.getRepetition().equals(ConstantInfo.ANNUALLY)) {
            if (task.getLocalDate().isBefore(date)) {
                gettingDate = task.getLocalDate().plusYears(1);
            } else if (task.getLocalDate().isAfter(date)) {
                gettingDate = task.getLocalDate().minusYears(1);
            } else if (task.getLocalDate().equals(date)) {
                gettingDate = date;
            }
        }

        if (task.getRepetition().equals(ConstantInfo.SINGLE)) {
            gettingDate = date;
        }

        return gettingDate;
    }

//    public Task obtainTask(LocalDate localDate) {
//        System.out.println("Введите дату: ");
//        int digit = 1;
//        for (Map.Entry<Integer, Task> looper : tasks.entrySet()) {
//            Task currentTask = looper.getValue();
//            LocalDate taskDate = currentTask.getLocalDate();
//            if (localDate.equals(taskDate)) {
//                return currentTask;
//            }
//
//        }
//        return null;
//    }


    //Методы удаления задачи

    public void removeTask(Scanner scanner) {
        System.out.println("Введите номер задачи");
        Integer removeId = scanner.nextInt();
        for (Map.Entry<Integer, Task> looper : tasks.entrySet()) {
            if (Objects.equals(looper.getKey(), removeId)) {
                tasks.remove(looper.getKey());
            } else {
                System.out.println("Нечего удалять");
            }
        }
    }
}

