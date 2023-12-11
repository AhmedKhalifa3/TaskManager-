package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Expired Tasks button action listener for the Expired tasks button in the main screen
 */
public class ExpiredTasksButtonActionListner implements ActionListener {

    private MainWindow parentMainWindow; // Reference to the main window

    /**
     * when clicked it calls ExpiredTaskWindow.getInstance which checks the # of instances of that class
     * if there is no opened window it starts a new one
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExpiredTaskWindow.getInstance(parentMainWindow);

    }

    /**
     * Adds Main Window as a parent window cuz it is needed when calling actionPerformed
     * @param a Main Window (Parent Window)
     */
    public void addParentMainWindow(MainWindow a){
        parentMainWindow = a;
    }
}
