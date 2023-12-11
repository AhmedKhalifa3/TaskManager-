
package main.ui;

import main.model.ListTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Displays the list of the List Tasks
 */
public class ListTaskWindow {

    private static ListTaskWindow instance = null;
    private JFrame window;
    private MainWindow parentMainWindow; // Reference to the main window


    /**
     * private constructor to prevent creating instance of the class from outside
     * that helps to ensure that there is only one instance of that class exists at a time
     * @param parentMainWindow main Window
     */

    private ListTaskWindow(MainWindow parentMainWindow) {
        this.parentMainWindow = parentMainWindow;
        initialize();

    }

    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    public static ListTaskWindow getInstance(MainWindow parentMainWindow) {
        if (instance == null) {
            instance = new ListTaskWindow(parentMainWindow);
        }
        return instance;
    }

    /**
     * initializing the window with the required components
     */
    public void initialize(){

        window = new JFrame();

        window.setTitle("Timed Task");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(225, 400);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.setBackground(Color.LIGHT_GRAY);



        JLabel label1 = new JLabel("Add Single Issue Task");
        label1.setHorizontalAlignment(2);


        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Title:");
        JTextField title = new JTextField(20);
        titlePanel.add(titleLabel);
        titlePanel.add(title);


        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("Due date? :");
        JTextField date = new JTextField( 20);
        datePanel.add(dateLabel);
        datePanel.add(date);

        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descLabel = new JLabel("Description:");
        JTextField description = new JTextField( 20);
        descriptionPanel.add(descLabel);
        descriptionPanel.add(description);

        JPanel subTAsksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel subTasksLabel = new JLabel("Subtasks:");
        JTextField subTasksTextField = new JTextField( 20);
        subTAsksPanel.add(subTasksLabel);
        subTAsksPanel.add(subTasksTextField);

        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskTitle = title.getText();
                LocalDate taskDate;
                try {

                    // Attempt to parse the date string to LocalDate
                    taskDate = LocalDate.parse(date.getText());

                } catch (DateTimeParseException e1) {
                    // Handle the case when the date string is not in a valid format
                    System.out.println("Invalid date format: " + date.getText());
                    JOptionPane.showMessageDialog(window,
                            "Invalid date format: " + date.getText(),
                            "Invalid Date",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                    // Additional handling or error message display can be added here
                }

                if (taskDate.isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(window,
                            "Please enter a date greater than or equal to the current date.",
                            "Invalid Date",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Stop further processing if the date is invalid
                }

                System.out.println("Valid date: " + taskDate);
                String taskDesc = description.getText();
                String subTasks = subTasksTextField.getText();

                ListTask newTask = new ListTask(taskTitle, taskDesc, taskDate, subTasks);
                parentMainWindow.listTaskList.add(newTask);
                parentMainWindow.updateTaskList(newTask.toString(), parentMainWindow.listModel, parentMainWindow.listList);


                title.setText("");
                date.setText("");
                description.setText("");
                subTasksTextField.setText("");




                JOptionPane.showMessageDialog(window,
                        "Title: " + taskTitle + "\nIn: " + taskDate + " Days" + "\nDescription: " + taskDesc,
                        "User Input",
                        JOptionPane.INFORMATION_MESSAGE);



            }
        });

        panel.add(label1);
        panel.add(titlePanel);
        panel.add(datePanel);
        panel.add(descriptionPanel);
        panel.add(subTAsksPanel);
        panel.add(button);

        window.setVisible(true);



        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Set instance to null when the window is closed
                instance = null;
            }
        });




        window.add(panel, BorderLayout.CENTER);

    }
}
