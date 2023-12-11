package main.ui;

// https://stackoverflow.com/questions/13855184/drag-and-drop-custom-object-from-jlist-into-jlabel

import main.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Window class "the first window that shows up"
 */
public class MainWindow extends JFrame {
    public List<Task> singleIssueTaskList;
    public JList<ListItem> singleIssueList ;
    public DefaultListModel<ListItem> singleIssueModel;

    public List<Task> timedTaskList;
    public JList<ListItem> timedList ;
    public DefaultListModel<ListItem> timedModel;

    public List<Task> listTaskList;
    public JList<ListItem> listList ;
    public DefaultListModel<ListItem> listModel;

    public List<Task> deletedTaskClassesList;
    public JList<ListItem> deletedTaskList;
    public DefaultListModel<ListItem> deletedTaskModel;

    public List<Task> completedTaskClassesList;
    public JList<ListItem> completedTaskList;
    public DefaultListModel<ListItem> completedTaskModel;

    public List<Task> expiredTaskClassesList;
    public JList<ListItem> expiredTaskList;
    public DefaultListModel<ListItem> expiredTaskModel;

    JLabel trash = new JLabel();
    JLabel checkedLabel = new JLabel();







    private JFrame window;

    /**
     * initializes the Main Window the required components
     */
    public void initialize(){
        window = new JFrame();
        window.setTitle("Task Scheduler");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(new Color(59, 50, 100));

        ImageIcon image = new ImageIcon("src/main.ui/icon.png");
        window.setIconImage(image.getImage());

        // Adding Task panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(168, 135, 135));


        Label label1 = new Label("ADD Task");
        panel1.add(label1);


        Button button1 = new Button("Single Issue Task");
        SingleIssueButtonActionListeneer singleIssueButtonActionListeneer = new SingleIssueButtonActionListeneer();
        singleIssueButtonActionListeneer.addParentMainWindow(this);
        button1.addActionListener(singleIssueButtonActionListeneer);
        panel1.add(button1);

        Button button2 = new Button("Timed Task");
        TimedTaskButtonActionListener timedTaskButtonActionListener = new TimedTaskButtonActionListener();
        timedTaskButtonActionListener.addParentMainWindow(this);
        button2.addActionListener(timedTaskButtonActionListener);
        panel1.add(button2);

        Button button3 = new Button("List Task");
        ListTaskButtonActionListner listTaskButtonActionListner = new ListTaskButtonActionListner();
        listTaskButtonActionListner.addParentMainWindow(this);
        button3.addActionListener(listTaskButtonActionListner);
        panel1.add(button3);






        singleIssueTaskList = new ArrayList<Task>();
        singleIssueTaskList = readListFromFile("SingleIssueTask.tmp");
        singleIssueList = new JList<ListItem>();

        singleIssueList.setDragEnabled(true);
        singleIssueList.setTransferHandler(new ListTransferHandler());

        singleIssueModel = new DefaultListModel<ListItem>();

        for(Task task : singleIssueTaskList){
            String taskInfo = task.toString();
            singleIssueModel.addElement(new ListItem(taskInfo));
        }

        singleIssueList.setModel(singleIssueModel);






        timedTaskList = new ArrayList<Task>();
        timedTaskList = readListFromFile("TimedTask.tmp");
        timedList = new JList<ListItem>();

        timedList.setDragEnabled(true);
        timedList.setTransferHandler(new ListTransferHandler());

        timedModel = new DefaultListModel<ListItem>();

        for(Task task : timedTaskList){
            String taskInfo = task.toString();
            timedModel.addElement(new ListItem(taskInfo));
        }

        timedList.setModel(timedModel);





        listTaskList = new ArrayList<Task>();
        listTaskList = readListFromFile("ListTask.tmp");
        listList = new JList<ListItem>();

        listList.setDragEnabled(true);
        listList.setTransferHandler(new ListTransferHandler());

        listModel = new DefaultListModel<ListItem>();

        for(Task task : listTaskList){
            String taskInfo = task.toString();
            listModel.addElement(new ListItem(taskInfo));
        }

        listList.setModel(listModel);






        deletedTaskList = new JList<ListItem>();
        deletedTaskClassesList = new ArrayList<Task>();
        deletedTaskClassesList = readListFromFile("deletedTask.tmp");

        deletedTaskModel = new DefaultListModel<ListItem>();

        for(Task task : deletedTaskClassesList){
            String taskInfo = task.toString();
            System.out.println(taskInfo);
            deletedTaskModel.addElement(new ListItem(taskInfo));
        }

        deletedTaskList.setModel(deletedTaskModel);






        completedTaskList = new JList<ListItem>();
        completedTaskClassesList = new ArrayList<Task>();
        completedTaskClassesList = readListFromFile("completedTask.tmp");

        completedTaskModel = new DefaultListModel<ListItem>();

        for(Task task : completedTaskClassesList){
            String taskInfo = task.toString();
            System.out.println(taskInfo);
            completedTaskModel.addElement(new ListItem(taskInfo));
        }

        completedTaskList.setModel(completedTaskModel);







        expiredTaskList = new JList<ListItem>();
        expiredTaskClassesList = new ArrayList<Task>();
        expiredTaskClassesList = readListFromFile("expiredTask.tmp");

        expiredTaskModel = new DefaultListModel<ListItem>();



        LocalDateTime fiveDaysAgo = LocalDateTime.now().minusMinutes(2);


        fetchExpiredTask(singleIssueTaskList, singleIssueModel);
        fetchExpiredTask(timedTaskList, timedModel);
        fetchExpiredTask(listTaskList, listModel);


        for(Task task : expiredTaskClassesList){
            String taskInfo = task.toString();
            expiredTaskModel.addElement(new ListItem(taskInfo));
        }

        expiredTaskList.setModel(expiredTaskModel);







        //Tasks View panel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(new Color(59, 50, 100));
        Label label2 = new Label("TASKS");
        label2.setForeground(Color.WHITE);
        panel2.add(label2);



        //Deleted and expired panel
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(new Color(59, 70, 200));

        ImageIcon bin  = new ImageIcon("src/main/ui/Trashbin.jpeg");

        trash.setIcon(bin);
        trash.setHorizontalTextPosition(JLabel.CENTER);
        trash.setTransferHandler(new ListTransferHandler());

        ImageIcon checked  = new ImageIcon("src/main/ui/checked.png");
        checkedLabel.setIcon(checked);
        checkedLabel.setHorizontalTextPosition(JLabel.CENTER);
        checkedLabel.setTransferHandler(new ListTransferHandler());


        Button completedTasksButton = new Button("Completed Tasks");
        CompletedTaskButtonActionListner completedTasksButtonActionListener = new CompletedTaskButtonActionListner();
        completedTasksButtonActionListener.addParentMainWindow(this);
        completedTasksButton.addActionListener(completedTasksButtonActionListener);


        Button deletedTasksButton = new Button("Deleted Tasks");
        DeletedTaskButtonActionListner deletedTaskButtonActionListener = new DeletedTaskButtonActionListner();
        deletedTaskButtonActionListener.addParentMainWindow(this);
        deletedTasksButton.addActionListener(deletedTaskButtonActionListener);

        Button expiredTasksButton = new Button("Expired Tasks");
        ExpiredTasksButtonActionListner expiredTasksButtonActionListner  = new ExpiredTasksButtonActionListner();
        expiredTasksButtonActionListner.addParentMainWindow(this);
        expiredTasksButton.addActionListener(expiredTasksButtonActionListner);


        Label label3 = new Label("View");

        panel1.add(label3);

        panel3.add(trash);
        panel3.add(checkedLabel);
        panel1.add(completedTasksButton);
        panel1.add(deletedTasksButton);
        panel1.add(expiredTasksButton);
        panel3.setPreferredSize(new Dimension(150, 100));

        JPanel panel4 = new JPanel();
        panel4.add(new JScrollPane(singleIssueList));
        panel4.add(new JScrollPane(timedList));
        panel4.add(new JScrollPane(listList));






        window.add(panel1, BorderLayout.WEST);
        window.add(panel4, BorderLayout.CENTER);
//        window.add(new JScrollPane(timedList), BorderLayout.CENTER);

        window.add(panel3, BorderLayout.EAST);



        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeAllFrames();
            }
        });
    }

    /**
     * Closes all the opened frames once the main frame is closed
     */
    private void closeAllFrames() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame ) {
                window.dispose(); // Close all frames except the main window
            }
        }

        writeListToFile("SingleIssueTask.tmp", singleIssueTaskList);
        writeListToFile("TimedTask.tmp", timedTaskList);
        writeListToFile("ListTask.tmp", listTaskList);
        writeListToFile("deletedTask.tmp", deletedTaskClassesList);
        writeListToFile("completedTask.tmp", completedTaskClassesList);
        writeListToFile("expiredTask.tmp", expiredTaskClassesList);

    }

    /**
     * save the classes to the file by writing them down
     * @param fileName the file the data to be saved to
     * @param list list of tasks
     * @param <T> depending on task type
     */
    public static <T> void writeListToFile(String fileName, List<T> list) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
            System.out.println("List of objects has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieve the tasks saved in the file
     * @param fileName
     * @return returns a list of classes
     * @param <T>
     */
    public static <T> List<T> readListFromFile(String fileName) {
        List<T> list = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            System.out.println("File " + fileName + " is empty or does not exist.");
            return list; // Return an empty list
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            list = (List<T>) ois.readObject();
            System.out.println("List of objects has been read from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * update task list when adding a new task
     * @param taskInfo
     * @param model
     * @param list
     */

    public void updateTaskList(String taskInfo, DefaultListModel model, JList<ListItem> list){
        model.addElement(new ListItem(taskInfo));
        list.updateUI();
    }

    /**
     * do a regular check every time the application is started
     * filters all the tasks and move the expired tasks to the expired lis
     * I set the expiration date to be 7 days after creating the task
     * @param TaskList the list that will be filtered
     * @param model
     */
    public void fetchExpiredTask( List<Task> TaskList,  DefaultListModel<MainWindow.ListItem> model){

        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Task> tasksToRemove = new ArrayList<>();

        for (Task task : TaskList) {
            if (task.getDateOfCreation().isBefore(sevenDaysAgo)) {
                expiredTaskClassesList.add(task);
                tasksToRemove.add(task); // Store tasks to be removed
                for (int i = 0; i < model.size(); i++) {
                    if (task.toString().equals(model.getElementAt(i).toString())) {
                        model.removeElementAt(i);
                        break;
                    }
                }
            }
        }

// Remove the tasks outside the loop to avoid concurrent modification
        TaskList.removeAll(tasksToRemove);
    }


    /**
     * trasnferHandler class: this class is used to handle the transfer of a Transferable to and from Swing components
     */
    public class ListTransferHandler extends TransferHandler {
        /**
         * is called repeatedly during a drag and drop operation to allow the developer to configure properties of, and to return the acceptability of transfers
         * @param support the object containing the details of
         *        the transfer, not <code>null</code>.
         * @return the acceptability of transfers
         */
        @Override
        public boolean canImport(TransferSupport support) {
            return (support.getComponent() instanceof JLabel) && support.isDataFlavorSupported(MainWindow.ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
        }

        /**
         * Causes a transfer to occur from drag and drop operation
         * @param support the object containing the details of
         *        the transfer, not <code>null</code>.
         * @return
         */
        @Override
        public boolean importData(TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(MainWindow.ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
                    if (value instanceof ListItem) {
                        Component component = support.getComponent();
                        if (component instanceof JLabel && component == trash) {
                            ((JLabel)component).setText(((ListItem)value).getText());
                            Task taskToRemove = null;
                            boolean founded = false;
                            for(Task task : singleIssueTaskList){
                                String info = task.toString();
                                if(info.equals(value.toString())){
                                    taskToRemove = task;
                                    founded = true;
                                    singleIssueTaskList.remove(taskToRemove);
                                    writeListToFile("SingleIssueTask.tmp", singleIssueTaskList);
                                    singleIssueModel.removeElement(value);
                                    deletedTaskModel.addElement((ListItem) value);
                                    deletedTaskClassesList.add(taskToRemove);
                                    writeListToFile("deletedTask.tmp", deletedTaskClassesList);
                                    singleIssueList.updateUI();
                                    accept = true;
                                    break;
                                }
                            }

                            if(founded == false){
                                for(Task task : timedTaskList){
                                    String info = task.toString();
                                    if(info.equals(value.toString())){
                                        taskToRemove = task;
                                        founded = true;
                                        timedTaskList.remove(taskToRemove);
                                        writeListToFile("TimedTask.tmp", timedTaskList);
                                        timedModel.removeElement(value);
                                        deletedTaskModel.addElement((ListItem) value);
                                        deletedTaskClassesList.add(taskToRemove);
                                        writeListToFile("deletedTask.tmp", deletedTaskClassesList);
                                        timedList.updateUI();
                                        accept = true;
                                        break;
                                    }
                                }
                            }

                            if(founded == false){
                                for(Task task : listTaskList){
                                    String info = task.toString();
                                    if(info.equals(value.toString())){
                                        taskToRemove = task;
                                        listTaskList.remove(taskToRemove);
                                        writeListToFile("ListTask.tmp", listTaskList);
                                        listModel.removeElement(value);
                                        deletedTaskModel.addElement((ListItem) value);
                                        deletedTaskClassesList.add(taskToRemove);
                                        writeListToFile("deletedTask.tmp", deletedTaskClassesList);
                                        listList.updateUI();
                                        accept = true;
                                        break;
                                    }
                                }
                            }

                        }else if (component instanceof JLabel && component == checkedLabel) {
                            ((JLabel)component).setText(((ListItem)value).getText());
                            Task completedTask = null;
                            boolean founded = false;

                            for(Task task : singleIssueTaskList){
                                String info = task.toString();
                                if(info.equals(value.toString())){
                                    completedTask = task;
                                    completedTask.markAsCompleted();
                                    singleIssueTaskList.remove(completedTask);
                                    writeListToFile("SingleIssueTask.tmp", singleIssueTaskList);
                                    singleIssueModel.removeElement(value);
                                    completedTaskModel.addElement((ListItem) value);
                                    completedTaskClassesList.add(completedTask);
                                    writeListToFile("completedTask.tmp", completedTaskClassesList);
                                    singleIssueList.updateUI();
                                    accept = true;
                                    founded = true;
                                    break;
                                }
                            }

                            if(founded == false){
                                for(Task task : timedTaskList){
                                    String info = task.toString();
                                    if(info.equals(value.toString())){
                                        completedTask = task;
                                        completedTask.markAsCompleted();
                                        timedTaskList.remove(completedTask);
                                        writeListToFile("TimedTask.tmp", timedTaskList);
                                        timedModel.removeElement(value);
                                        completedTaskModel.addElement((ListItem) value);
                                        completedTaskClassesList.add(completedTask);
                                        writeListToFile("completedTask.tmp", completedTaskClassesList);
                                        timedList.updateUI();
                                        accept = true;
                                        founded = true;
                                        break;
                                    }
                                }
                            }

                            if(founded == false){
                                for(Task task : listTaskList){
                                    String info = task.toString();
                                    if(info.equals(value.toString())){
                                        completedTask = task;
                                        completedTask.markAsCompleted();
                                        listTaskList.remove(completedTask);
                                        writeListToFile("ListTask.tmp", listTaskList);
                                        listModel.removeElement(value);
                                        completedTaskModel.addElement((ListItem) value);
                                        completedTaskClassesList.add(completedTask);
                                        writeListToFile("completedTask.tmp", completedTaskClassesList);
                                        listList.updateUI();
                                        accept = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }

        /**
         *
         * @param c  the component holding the data to be transferred;
         *           provided to enable sharing of <code>TransferHandler</code>s
         * @return the type of transfer actions supported by the source
         */
        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        /**
         * creates a Transferable to use as the source for a data transfer
         * @param c  the component holding the data to be transferred;
         *              provided to enable sharing of <code>TransferHandler</code>s
         * @return
         */
        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = null;
            if (c instanceof JList) {
                @SuppressWarnings("unchecked")
                JList<MainWindow.ListItem> list = (JList<MainWindow.ListItem>) c;
                Object value = list.getSelectedValue();
                if (value instanceof MainWindow.ListItem) {
                    MainWindow.ListItem li = (MainWindow.ListItem) value;
                    t = new MainWindow.ListItemTransferable(li);
                }
            }
            return t;
        }

        /**
         * Invoked after data has been exported.
         * @param source the component that was the source of the data
         * @param data   The data that was transferred or possibly null
         *               if the action is <code>NONE</code>.
         * @param action the actual action that was performed
         */
        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            System.out.println("ExportDone" + data.toString());
        }
    }

    /**
     * Defines the interface for classes that can be used to provide data for a transfer operation.
     */
    public static class ListItemTransferable implements Transferable {
        public static final DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(MainWindow.ListItem.class, "java/ListItem");
        private MainWindow.ListItem listItem;

        public ListItemTransferable(MainWindow.ListItem listItem) {
            this.listItem = listItem;
        }

        /**
         * Transferable Returns an array of DataFlavor objects indicating the flavors the data can be provided in
         * @return
         */
        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{LIST_ITEM_DATA_FLAVOR};
        }

        /**
         * Transferable Returns whether or not the specified data flavor is supported for this object
         * @param flavor the requested flavor for the data
         * @return
         */
        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(LIST_ITEM_DATA_FLAVOR);
        }

        /**
         *Returns an object which represents the data to be transferred. The class of the object returned is defined by the representation class of the flavor.
         * @param flavor the requested flavor for the data
         * @return
         * @throws UnsupportedFlavorException
         * @throws IOException
         */
        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

            return listItem;

        }
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


