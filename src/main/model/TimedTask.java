package main.model;

import java.time.LocalDate;

/**
 * Timed task class for creating Single-issue task objects
 */
public class TimedTask extends Task {
    private int durationMinutes;

    /**
     * timed class constructor
     * @param title
     * @param description
     * @param dueDate
     * @param durationMinutes
     */
    public TimedTask(String title, String description, LocalDate dueDate, int durationMinutes) {
        super(title, description, dueDate, "Timed Task");
        this.durationMinutes = durationMinutes;
    }

    /**
     * gets the duration of the task
     * @return
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * sets the duration of the task
     * @param durationMinutes
     */
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
