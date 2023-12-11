package main.model;

import java.time.LocalDate;

/**
 * Single issue task class for creating Single-issue task objects
 */

public class SingleIssueTask extends Task {

    /**
     * class constructor
     * @param title
     * @param description
     * @param dueDate
     */
    public SingleIssueTask(String title, String description, LocalDate dueDate) {
        super(title, description, dueDate, "Single Issue");
    }

    /**
     * writes the task into string format
     * @return
     */
    @Override
    public String toString() {
        return "Task{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", dueDate=" + getDueDate() +
                ", category= Single Issue" +
                '}';
    }

}
