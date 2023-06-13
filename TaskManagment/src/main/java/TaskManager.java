import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<Map<String, String>, Boolean> m_TasksCollection = new HashMap<>();

    public Map<Map<String, String>, Boolean> getTasksCollection(){
        return m_TasksCollection;
    }

    public void CreateTask(String title, String description) {
        if(title.isEmpty() || description.isEmpty()){
            System.out.print("Error, Inputs are empty to be able to create task.");
            return;
        }

        Map<String, String> innerMap = new HashMap<>();
        innerMap.put(title, description);
        m_TasksCollection.put(innerMap, false);
    }

    public void DeleteTask(String taskName) {
        for (Map.Entry<Map<String, String>, Boolean> entry : m_TasksCollection.entrySet()) {
            Map<String, String> innerMap = entry.getKey();

            if (innerMap.containsKey(taskName)) {
                m_TasksCollection.remove(innerMap);
                entry.setValue(true);
                break;
            }
        }
    }

    public void EditTask(String taskName, String newDescription, boolean status) {
        if(taskName.isEmpty() && newDescription.isEmpty()){
            System.out.print("Error, Inputs are empty to be able to edit task.");
            return;
        }

        for (Map.Entry<Map<String, String>, Boolean> entry : m_TasksCollection.entrySet()) {
            Map<String, String> innerMap = entry.getKey();
            if (innerMap.containsKey(taskName)) {
                innerMap.put(taskName, newDescription);
                entry.setValue(status);
            }
        }
    }

    public void ShowAllTasks() {
        if(m_TasksCollection.isEmpty()){
            System.out.print("Error, Task Collection is empty, please fill it up.");
        }

        for (Map.Entry<Map<String, String>, Boolean> entry : m_TasksCollection.entrySet()) {
            Map<String, String> innerMap = entry.getKey();
            Boolean value = entry.getValue();

            if(!entry.getValue()){
                for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                    System.out.println("Title: " + innerEntry.getKey() + ", Description: " + innerEntry.getValue() + ", Status: " + value);
                }
            }
        }
        System.out.println();
    }
}
