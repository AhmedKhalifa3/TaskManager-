package test.model;

import static org.junit.jupiter.api.Assertions.*;
import main.model.ListTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class ListTaskTest {

    @Test
    void listTaskInitialization_ShouldCreateListTaskWithGivenParameters() {
        // Given
        String title = "Test List Task";
        String description = "Description for test list task";
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        String subTasks = "Subtask1, Subtask2, Subtask3";

        // When
        ListTask listTask = new ListTask(title, description, dueDate, subTasks);

        // Then
        assertNotNull(listTask);
        assertEquals(title, listTask.getTitle());
        assertEquals(description, listTask.getDescription());
        assertEquals(dueDate, listTask.getDueDate());
        assertEquals("List Task", listTask.getCategory());
        assertFalse(listTask.isCompleted());
        assertNotNull(listTask.getDateOfCreation());
    }
}
