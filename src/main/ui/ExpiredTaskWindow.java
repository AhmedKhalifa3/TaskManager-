package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the list of the deleted Tasks
 */
public class ExpiredTaskWindow {
    private JFrame window;
    private static ExpiredTaskWindow instance = null;
    private MainWindow parentMainWindow; // Reference to the main window


    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    private ExpiredTaskWindow(MainWindow parentMainWindow) {
        this.parentMainWindow = parentMainWindow;
        initialize();

    }

    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    public static ExpiredTaskWindow getInstance(MainWindow parentMainWindow) {
        if (instance == null) {
            instance = new ExpiredTaskWindow(parentMainWindow);
        }
        return instance;
    }

    /**
     * initializing the window with the required components
     */
    public void initialize(){
        window = new JFrame();

        window.setTitle("Expired Tasks");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 300);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(new Color(59, 50, 100));


        window.add(new JScrollPane(parentMainWindow.expiredTaskList), BorderLayout.CENTER);

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
