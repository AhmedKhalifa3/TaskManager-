package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Single issue button action listener for Single issue button in the main screen
 */
public class SingleIssueButtonActionListeneer implements ActionListener {

    private MainWindow parentMainWindow; // Reference to the main window

    /**
     * creates an instance of SingleTaskWindow
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SingleTaskWindow.getInstance(parentMainWindow);

    }

    /**
     * adding main Window as parent, so it can be used for creating the instance of the SingleTaskWindow
     * @param a main Window
     */
    public void addParentMainWindow(MainWindow a){
        parentMainWindow = a;
    }
}
