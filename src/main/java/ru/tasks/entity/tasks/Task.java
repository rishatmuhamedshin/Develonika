package ru.tasks.entity.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public abstract class Task {

    private Priority priority;
    private String name;
    private String description;
    private Status status;
    private final LocalDateTime createTime;
    private LocalDateTime dateControlPoint;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM);

    public Task(Priority priority, String name, String message) {
        this.priority = priority;
        this.name = name;
        this.description = message;
        setStatus(Status.NEW);
        createTime = LocalDateTime.now();
        dateControlPoint = LocalDateTime.now();
    }

    public String getStatus() {
        return status.getName();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateControlPoint() {
        return dateControlPoint;
    }


    public String getPriority() {
        return priority.getName();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateControlPoint(LocalDateTime dateControlPoint) {
        this.dateControlPoint = dateControlPoint;
    }

    @Override
    public String toString() {
        return  "\n\tПриоритет задачи: " + getPriority() +
                "\n\tНаименование задачи: " + getName() +
                "\n\tСтатус задачи: " + getStatus() +
                "\n\tДата создания: " + createTime.format(formatter) +
                "\n\tДата контрольной точки: " + dateControlPoint.format(formatter) +
                "\n\tОписание: " + getDescription();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (priority != task.priority) return false;
        if (!Objects.equals(name, task.name)) return false;
        if (!Objects.equals(description, task.description)) return false;
        if (status != task.status) return false;
        if (!Objects.equals(createTime, task.createTime)) return false;
        if (!Objects.equals(dateControlPoint, task.dateControlPoint))
            return false;
        return formatter.equals(task.formatter);
    }

    @Override
    public int hashCode() {
        int result = priority != null ? priority.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (dateControlPoint != null ? dateControlPoint.hashCode() : 0);
        result = 31 * result + formatter.hashCode();
        return result;
    }



}
