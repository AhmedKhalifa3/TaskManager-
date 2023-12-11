package test.model;

import static org.junit.jupiter.api.Assertions.*;



import main.model.TimedTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TimedTaskTest {

    @Test
    void timedTaskInitialization_ShouldCreateTimedTaskWithGivenParameters() {
        // Given
        String title = "Test Timed Task";
        String description = "Description for test timed task";
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        int durationMinutes = 60;

        // When
        TimedTask timedTask = new TimedTask(title, description, dueDate, durationMinutes);

        // Then
        assertNotNull(timedTask);
        assertEquals(title, timedTask.getTitle());
        assertEquals(description, timedTask.getDescription());
        assertEquals(dueDate, timedTask.getDueDate());
        assertEquals("Timed Task", timedTask.getCategory());
        assertEquals(durationMinutes, timedTask.getDurationMinutes());
        assertFalse(timedTask.isCompleted());
        assertNotNull(timedTask.getDateOfCreation());
    }

    @Test
    void timedTaskDuration_ShouldSetAndGetDurationMinutes() {
        // Given
        TimedTask timedTask = new TimedTask("Test Timed Task", "Description",
                LocalDate.of(2023, 12, 31), 60);

        // When
        assertEquals(60, timedTask.getDurationMinutes());
        timedTask.setDurationMinutes(90);

        // Then
        assertEquals(90, timedTask.getDurationMinutes());
    }
}
