package test.ui;

import main.model.SingleIssueTask;
import main.model.TimedTask;
import main.ui.MainWindow;
import main.ui.SingleTaskWindow;
import main.ui.TimedTaskWindow;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TimedTaskWindowTest {
    private static TimedTaskWindow instance = null;
    private JFrame window;
    private MainWindow parentMainWindow;
    @Test
    void initialize() {
        parentMainWindow = new MainWindow();
        parentMainWindow.initialize();
        JTextField description = new JTextField(20);
        JTextField date = new JTextField(20);
        JTextField title = new JTextField(20);
        JTextField durationTextField = new JTextField( 20);


        // Set valid values to text fields
        description.setText("descrip");
        date.setText("2023-12-31"); // A valid date in the future
        title.setText("tit");
        durationTextField.setText("11");

        String taskTitle = title.getText();
        LocalDate taskDate;
        try {

            // Attempt to parse the date string to LocalDate
            taskDate = LocalDate.parse(date.getText());

        } catch (DateTimeParseException e1) {
            // Handle the case when the date string is not in a valid format
            System.out.println("Invalid date format: " + date.getText());
            return;
            // Additional handling or error message display can be added here
        }

        if (taskDate.isBefore(LocalDate.now())) {
            return; // Stop further processing if the date is invalid
        }
        String taskDesc = description.getText();

        String taskDuration = durationTextField.getText();
        int durationInMin;
        try {
            durationInMin = Integer.parseInt(taskDuration);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(window,
                    "Invalid duration format: " + durationTextField.getText(),
                    "Invalid duration",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (durationInMin <= 0) {
            JOptionPane.showMessageDialog(window,
                    "Please enter an integer greater than 0.",
                    "Invalid Duration",
                    JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing if the date is invalid
        }


        TimedTask newTask = new TimedTask(taskTitle, taskDesc, taskDate, durationInMin);
        parentMainWindow.timedTaskList.add(newTask);

        // Assert task is not null
        assertNotNull(newTask);

        // Assert the size of the list after adding the task
        assertEquals(1, parentMainWindow.timedTaskList.size());

        // Assert the properties of the added task
        assertEquals(taskTitle, newTask.getTitle());
        assertEquals(taskDesc, newTask.getDescription());
        assertEquals(taskDate, newTask.getDueDate());
    }
}