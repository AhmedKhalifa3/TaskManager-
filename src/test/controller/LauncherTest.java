package test.controller;

import main.ui.MainWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {

    @Test
    void mainWindowCreated(){
        var mainWindow = new MainWindow();
        assertNotNull(mainWindow);
    }
}