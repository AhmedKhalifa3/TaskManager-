package main.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * the parent class of the model classes, Task Class to create task instances
 */
public class Task implements Serializable {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String category;

    private LocalDateTime dateOfCreation;

    private boolean isCompleted;


    /**
     * Task Constructor
     * @param title
     * @param description
     * @param dueDate
     * @param category
     */
    public Task(String title, String description, LocalDate dueDate, String category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.dateOfCreation = LocalDateTime.now();
        this.isCompleted = false;
    }

    /**
     * gets the title of the task
     * @return the title
     */
    // Getters and Setters for the attributes
    public String getTitle() {
        return title;
    }

    /**
     * set the title of the task
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the due Date of the task
     * @return the due Date of the task
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * set the date
     * @param dueDate
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * gets the category
     * @return the category of the task
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * gets the date of creation of the task
     * @return date of creation of the task
     */
    public LocalDateTime getDateOfCreation(){
        return dateOfCreation;
    }

    /**
     * returning whether the task is completed or not
     * @return isCompleted
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * marking the task as completed. to be done when the task is draged and dropped to the checked tasks icon
     */
    public void markAsCompleted() {
        isCompleted = true;
    }


    /**
     * converts the task into a string format to add it to the model and view it
     * @return the task in string expression
     */
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", category='" + category + '\'' +
                '}';
    }

}
