package main.model;


import java.time.LocalDate;

/**
 * List task class for creating Single-issue task objects
 */
public class ListTask extends Task {
    private String subTasks;


    /**
     * constructor for the class
     * @param title
     * @param description
     * @param dueDate
     * @param subTasks
     */
    public ListTask(String title, String description, LocalDate dueDate, String subTasks) {
        super(title, description, dueDate, "List Task");
        this.subTasks = subTasks;
    }

}
