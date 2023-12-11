package main.controller;

import main.ui.MainWindow;

import javax.swing.*;

/**
 * Controller class to start the application
 */

public class Launcher {
    /**
     * starts an instance of the main window
     * @param args
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.initialize();
            }
        });
    }
}
