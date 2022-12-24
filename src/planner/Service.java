package planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Service implements Methods {
    private final Map<Integer, Task> tasks;


    public Service() {
        tasks = new HashMap<>();
    }

    //The method collector
    public void fillInTask(Scanner scanner) throws ServiceCheckException {
        try {
            String taskName = inputTask(scanner);
            String description = inputDescription(scanner);
            String type = chooseType(scanner);
            ConstantInfo repetition = setRepetition(scanner);
            LocalDate localDate = inputDate(scanner);
            Task task = new Task(taskName, description, type, repetition, localDate);
            tasks.put(task.getId(), task);
            System.out.println(task);
//        if (tasks.containsValue(task)) {
//            System.out.println("Задача добавлена");
//        } else {
//            throw new RuntimeException("Задача не добавлена");
//        }
        } catch (UnsupportedOperationException e) {
            System.out.println("Что-то не заполнили или заполнили не правильно");
        }
    }

    //Методы для первого пункта меню - Добавить задачу


    public static String inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        if (taskName == null || taskName.isBlank() || taskName.isEmpty()) {
            throw new UnsupportedOperationException("Не ввели название");
        } else {
            return taskName;
        }
    }


    public static String inputDescription(Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        if (description == null || description.isBlank() || description.isEmpty()) {
            throw new UnsupportedOperationException("Не ввели название");
        } else {
            return description;
        }
    }

    public static String chooseType(Scanner scanner) {
        String taskType = "Задача";
        String personalTask = "Личная задача";
        String jobTask = "Рабочая задача";


        printTypeMenu();
        System.out.println("Выберите тип задачи, личная или рабочая: ");
        int menu = scanner.nextInt();
        if (menu == 1 || menu == 2) {
            switch (menu) {
                case 1:
                    taskType = personalTask;
                    break;
                case 2:
                    taskType = jobTask;
                    break;
            }
        } else {
            throw new UnsupportedOperationException("Укажите 1 или 2");
        }


        return taskType;
    }

    public static ConstantInfo setRepetition(Scanner scanner) {
        ConstantInfo constantValue = ConstantInfo.SINGLE;

        System.out.println("Введите частоту повторения:\n SINGLE, \n DAILY, \n WEEKLY, \n MONTHLY, \n ANNUALLY");
        ConstantInfo constantInfo = ConstantInfo.valueOf(scanner.next());
        if (constantInfo != null || constantInfo == ConstantInfo.SINGLE || constantInfo == ConstantInfo.DAILY ||
                constantInfo == ConstantInfo.WEEKLY || constantInfo == ConstantInfo.MONTHLY || constantInfo == ConstantInfo.ANNUALLY) {
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
        } else {
            throw new UnsupportedOperationException("Внимательно введите частоту из списка: " +
                    "\n SINGLE, \n DAILY, \n WEEKLY, \n MONTHLY, \n ANNUALLY");
        }

        return constantValue;
    }

    public static LocalDate inputDate(Scanner scanner) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Введите дату события в формате дд.мм.гггг: ");
        scanner.nextLine();
        String dateLine = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateLine, dateTimeFormatter);
        return date;
    }

    //Menus
    public static void printTypeMenu() {
        System.out.println(1 + ". Личная; " + "\n" + 2 + ". Рабочая;");
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

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Введите дату события в формате дд.мм.гггг: ");
        scanner.nextLine();
        String dateLine = scanner.nextLine();
        LocalDate indicatedDate = LocalDate.parse(dateLine,dateTimeFormatter);
        for (Map.Entry<Integer, Task> looper : tasks.entrySet()) {
            Task currentTask = looper.getValue();
            LocalDate taskDate = understandRepetition(currentTask, indicatedDate);
            currentTask.setLocalDate(taskDate);
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

