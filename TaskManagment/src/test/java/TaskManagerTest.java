import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testCreateTask() {
        taskManager.CreateTask("Task1", "Description1");
        assertEquals(1, taskManager.getTasksCollection().size());
        assertTrue(taskManager.getTasksCollection().containsValue(false));
    }

    @Test
    public void testDeleteTask() {
        taskManager.CreateTask("Task1", "Description1");
        taskManager.DeleteTask("Task1");
        assertEquals(0, taskManager.getTasksCollection().size());
    }

    @Test
    public void testEditEmptyName() {
        taskManager.CreateTask("Task1", "Description1");
        taskManager.EditTask("", "", true);
        assertNotEquals("Task1", "");
    }

    @Test
    public void testEditEmptyDescription() {
        taskManager.CreateTask("Task1", "Description1");
        taskManager.EditTask("Task1", "", true);
        assertNotEquals("newInformation", "");
    }

    @Test
    public void testEditTask() {
        taskManager.CreateTask("Task1", "Description1");
        taskManager.EditTask("Task1", "New Description", true);
        Map<Map<String, String>, Boolean> tasksCollection = taskManager.getTasksCollection();
        assertEquals("New Description", tasksCollection.keySet().iterator().next().get("Task1"));
        assertTrue(tasksCollection.values().iterator().next());
    }

    @Test
    public void testShowAllTasks() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskManager.CreateTask("Task1", "Description1");
        taskManager.ShowAllTasks();
        String expectedOutput = "Title: Task1, Description: Description1, Status: false";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

}