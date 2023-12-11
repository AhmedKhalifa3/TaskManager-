package test.model;

import static org.junit.jupiter.api.Assertions.*;


import main.model.SingleIssueTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SingleIssueTaskTest {

    @Test
    void singleIssueTaskInitialization_ShouldCreateSingleIssueTaskWithGivenParameters() {
        // Given
        String title = "Test Single Issue";
        String description = "Description for test single issue";
        LocalDate dueDate = LocalDate.of(2023, 12, 31);

        // When
        SingleIssueTask singleIssueTask = new SingleIssueTask(title, description, dueDate);

        // Then
        assertNotNull(singleIssueTask);
        assertEquals(title, singleIssueTask.getTitle());
        assertEquals(description, singleIssueTask.getDescription());
        assertEquals(dueDate, singleIssueTask.getDueDate());
        assertEquals("Single Issue", singleIssueTask.getCategory());
        assertFalse(singleIssueTask.isCompleted());
        assertNotNull(singleIssueTask.getDateOfCreation());
    }

    @Test
    void singleIssueTaskToString_ShouldReturnStringRepresentation() {
        // Given
        SingleIssueTask singleIssueTask = new SingleIssueTask("Test Single Issue", "Description",
                LocalDate.of(2023, 12, 31));

        // When
        String taskString = singleIssueTask.toString();

        // Then
        assertNotNull(taskString);
        assertTrue(taskString.contains("title='Test Single Issue'"));
        assertTrue(taskString.contains("description='Description'"));
        assertTrue(taskString.contains("dueDate=2023-12-31"));
        assertTrue(taskString.contains("category= Single Issue"));
    }
}
