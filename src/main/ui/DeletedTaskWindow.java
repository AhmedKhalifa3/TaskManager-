package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the list of the deleted Tasks
 */
public class DeletedTaskWindow {
    private JFrame window;
    private JList<ListItem> deletedTaskList;
    DefaultListModel<ListItem> deletedTaskModel;
    private static DeletedTaskWindow instance = null;
    private MainWindow parentMainWindow; // Reference to the main window


    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    private DeletedTaskWindow(MainWindow parentMainWindow) {
        this.parentMainWindow = parentMainWindow;
        initialize();

    }

    /**
     * creating instance of the window after making sure there is no other instances
     * @param parentMainWindow main Window
     * @return
     */
    public static DeletedTaskWindow getInstance(MainWindow parentMainWindow) {
        if (instance == null) {
            instance = new DeletedTaskWindow(parentMainWindow);
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


        deletedTaskList = new JList<ListItem>();

        deletedTaskModel = new DefaultListModel<ListItem>();
        deletedTaskModel.addElement(new ListItem("ahmed"));

        deletedTaskList.setModel(deletedTaskModel);


        window.add(new JScrollPane(parentMainWindow.deletedTaskList), BorderLayout.CENTER);

        window.setVisible(true);

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Set instance to null when the window is closed
                instance = null;
            }
        });


    }



    /**
     * List Item class to be used in a defining the class text in the model.
     */
    public static class ListItem {

        private String text;

        public ListItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return getText();
        }
    }
}
