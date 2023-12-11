package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Complete Task button action listener for the completed tasks button in the main screen
 */
public class CompletedTaskButtonActionListner implements ActionListener {

    private MainWindow parentMainWindow; // Reference to the main window

    /**
     * when clicked it calls CompletedTaskWindow.getInstance which checks the # of instances of that class
     * if there is no opened window it starts a new one
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CompletedTaskWindow.getInstance(parentMainWindow);

    }

    /**
     * Adds Main Window as a parent window cuz it is needed when calling actionPerformed
     * @param a Main Window (Parent Window)
     */
    public void addParentMainWindow(MainWindow a){
        parentMainWindow = a;
    }
}
