package test.ui;

import main.ui.MainWindow;
import main.ui.TimedTaskWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleIssueButtonActionListeneerTest {


    private MainWindow parentMainWindow; // Reference to the main window

    @Test
    void actionPerformed() {
        assertNotNull(TimedTaskWindow.getInstance(parentMainWindow));
    }
}