import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    public static void WriteToFile(Map<Map<String, String>, Boolean> tasksCollection) {
        try {
            FileWriter fileWriter = new FileWriter("TaskList.txt");

            StringBuilder data = new StringBuilder();
            for (Map.Entry<Map<String, String>, Boolean> entry : tasksCollection.entrySet()) {
                Map<String, String> innerMap = entry.getKey();
                Boolean value = entry.getValue();

                if(!entry.getValue()){
                    for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                        data.append("Title: ").append(innerEntry.getKey()).append(", Description: ").append(innerEntry.getValue()).append(", TaskIsDone: ").append(value);
                        data.append("\n");
                    }
                }
            }

            fileWriter.write(data.toString());
            fileWriter.close();
            System.out.println("Data written to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void LoadFromFile(Map<Map<String, String>, Boolean> tasksCollection){

        try (BufferedReader reader = new BufferedReader(new FileReader("TaskList.txt"))) {

            String line = "";
            while ((line = reader.readLine()) != null) {
                Map<String, String> innerMap = new HashMap<>();

                String[] tokens = line.split(", ");
                String title = tokens[0].substring(tokens[0].indexOf(":") + 2);
                String description = tokens[1].substring(tokens[1].indexOf(":") + 2);
                boolean isDone = Boolean.parseBoolean(tokens[2].substring(tokens[2].indexOf(":") + 2));

                innerMap.put(title, description);
                tasksCollection.put(innerMap, isDone);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
