import java.util.Scanner;

public class Application {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager m_TaskManager = new TaskManager();

    private void Menu(){
        System.out.println("Please select what do you want to do.");
        System.out.println("1) Create Task.");
        System.out.println("2) Delete Task.");
        System.out.println("3) Edit Task.");
        System.out.println("4) Show All Tasks.");
        System.out.println("5) Save Information.");
        System.out.println("6) Exit");
    }
    public void RunApp(){
        Menu();

        FileManager.LoadFromFile(m_TaskManager.getTasksCollection());

        String input = "";
        while(!input.equals("6")){
            System.out.print("Type command here: ");
            input = scanner.nextLine();

            if(input.equals("1")){
                //Create Task
                System.out.println("Type title of the task:");
                String title = scanner.nextLine();
                System.out.println("Type description of the task:");
                String description = scanner.nextLine();
                m_TaskManager.CreateTask(title, description);

            }else if(input.equals("2")){
                //Delete Task
                System.out.println("Type task to delete:");
                String taskDeleted = scanner.nextLine();
                m_TaskManager.DeleteTask(taskDeleted);

            }else if(input.equals("3")){
                //Edit Task
                System.out.println("Type task name to edit:");
                String taskName = scanner.nextLine();
                System.out.println("Type description of the task:");
                String description = scanner.nextLine();
                System.out.println("Type status of the task is done: yes/no");
                String statusInput = scanner.nextLine();

                boolean status = false;
                if(statusInput.equals("yes")){
                    status = true;
                }

                m_TaskManager.EditTask(taskName, description, status);

            }else if(input.equals("4")){
                //Show All
                m_TaskManager.ShowAllTasks();

            }else if(input.equals("5")){
                //Save Information
                FileManager.WriteToFile(m_TaskManager.getTasksCollection());

            }
        }
    }
}
