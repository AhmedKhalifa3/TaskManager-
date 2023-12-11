package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the list of the completed Tasks
 */
public class CompletedTaskWindow {
    private JFrame window;
    private static CompletedTaskWindow instance = null;
    private MainWindow parentMainWindow; // Reference to the main window


    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */

    private CompletedTaskWindow(MainWindow parentMainWindow) {
        this.parentMainWindow = parentMainWindow;
        initialize();

    }

    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    public static CompletedTaskWindow getInstance(MainWindow parentMainWindow) {
        if (instance == null) {
            instance = new CompletedTaskWindow(parentMainWindow);
        }
        return instance;
    }

    /**
     * initializing the window with the required components
     */
    public void initialize(){
        window = new JFrame();

        window.setTitle("Deleted Tasks");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 300);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(new Color(59, 50, 100));

//        Button newButton = new Button("Delete");
//        window.add(newButton);


        window.add(new JScrollPane(parentMainWindow.completedTaskList), BorderLayout.CENTER);

        window.setVisible(true);

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Set instance to null when the window is closed
                instance = null;
            }
        });


    }


}
