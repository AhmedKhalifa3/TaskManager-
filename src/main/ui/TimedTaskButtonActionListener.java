package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Timed Task button action listener for Timed Task button in the main screen
 */
public class TimedTaskButtonActionListener implements ActionListener {
    private MainWindow parentMainWindow; // Reference to the main window

    /**
     * creates an instance of SingleTaskWindow
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TimedTaskWindow.getInstance(parentMainWindow);

    }

    /**
     * adding main Window as parent, so it can be used for creating the instance of the SingleTaskWindow
     * @param a main Window
     */
    public void addParentMainWindow(MainWindow a){
        parentMainWindow = a;
    }
}
