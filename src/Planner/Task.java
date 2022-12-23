package Diary;

import java.time.LocalDate;

import java.util.Objects;

public class Task {

    private String heading;
    private String description;
    private String type;
    private ConstantInfo repetition;
    private LocalDate localDate;

    private static int counter;
    private final Integer id;

    public Task(String heading, String description, String type, ConstantInfo repetition, LocalDate localDate) {

        this.heading = validateLines(heading);
        this.description = validateLines(description);
        this.type = validateLines(type);
        this.repetition = repetition;
        this.localDate = localDate;
        this.id = ++counter;
    }


    //Getters and Setters


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        if (heading == null && heading.isBlank() && heading.isEmpty()) {
            throw new UnsupportedOperationException("Не все данные заполнены");
        } else {
            this.heading = heading;
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null && description.isBlank() && description.isEmpty()) {
            throw new UnsupportedOperationException("Не все данные заполнены");
        } else {
            this.description = description;
        }

    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        if (localDate != null) {
            this.localDate = localDate;
        } else {
            throw new RuntimeException("Время не указано");
        }
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null && !type.isBlank() && !type.isEmpty()) {
            throw new UnsupportedOperationException("Не все данные заполнены");
        } else {
            this.type = type;
        }

    }

    public ConstantInfo getRepetition() {
        return repetition;
    }

    public void setRepetition(ConstantInfo repetition) {
        if (repetition == null) {
            throw new UnsupportedOperationException("Не все данные заполнены");
        } else {
            this.repetition = repetition;
        }

    }

    //Validators

    public String validateLines(String value) {
        if (value == null && value.isBlank() && value.isEmpty()) {
            throw new UnsupportedOperationException("Не все данные заполнены");
        } else {
            return value;
        }
    }




    @Override
    public String toString() {
        return  id + " Задача: " + heading + ", для этого нужно: " + description + ", тип задачи: " +
                type + ", как часто повторяется: " + repetition.getRepeatIndex() +
                ", дата создания задачи: " + localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return  heading.equals(task.heading) && type.equals(task.type) && repetition == task.repetition && id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repetition, id, heading);
    }
}
