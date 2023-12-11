package test.model;

import static org.junit.jupiter.api.Assertions.*;

import main.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class TaskTest {

    @Test
    void taskInitialization_ShouldCreateTaskWithGivenParameters() {
        // Given
        String title = "Test Task";
        String description = "Description for test task";
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        String category = "Test Category";

        // When
        Task task = new Task(title, description, dueDate, category);

        // Then
        assertNotNull(task);
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(category, task.getCategory());
        assertFalse(task.isCompleted());
        assertNotNull(task.getDateOfCreation());
    }

    @Test
    void taskMarkAsCompleted_ShouldMarkTaskAsCompleted() {
        // Given
        Task task = new Task("Test", "Description", LocalDate.now(), "TestCategory");

        // When
        assertFalse(task.isCompleted());
        task.markAsCompleted();

        // Then
        assertTrue(task.isCompleted());
    }

    @Test
    void taskToString_ShouldReturnStringRepresentation() {
        // Given
        Task task = new Task("Test", "Description", LocalDate.of(2023, 12, 31), "TestCategory");

        // When
        String taskString = task.toString();

        // Then
        assertNotNull(taskString);
        assertTrue(taskString.contains("title='Test'"));
        assertTrue(taskString.contains("description='Description'"));
        assertTrue(taskString.contains("dueDate=2023-12-31"));
        assertTrue(taskString.contains("category='TestCategory'"));
    }
}
