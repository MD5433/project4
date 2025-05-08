
import jdk.jfr.Description;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private int priority;
    static Scanner myIn = new Scanner(System.in);
    static TaskCollection tasks = new TaskCollection();

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }

    public static void taskAdd() {
        try {
            System.out.print("Enter a title for the task: ");
            String title = myIn.nextLine();
            System.out.print("Enter a description for the task: ");
            String description = myIn.nextLine();

            int priority;
            while (true) {
                System.out.print("Enter priority for the task (0-5): ");
                priority = Integer.parseInt(myIn.nextLine());
                if (priority >= 0 && priority <= 5) break;
                System.out.println("Invalid priority. Please enter a number between 0 and 5.");
            }

            tasks.addTask(new Task(title, description, priority));
            System.out.println("Task added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, input for priority must be an integer");
        }
    }

    public static void taskRemove() {
        taskList();
        try {
            System.out.print("Enter task number to remove: ");
            int userInput = Integer.parseInt(myIn.nextLine()) - 1;
            tasks.removeTask(userInput);
            System.out.println("Task removed.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    public static void taskUpdate() {
        taskList();
        try {
            System.out.print("Enter task number to edit: ");
            int index = Integer.parseInt(myIn.nextLine()) - 1;
            Task task = tasks.getTask(index);

            System.out.print("Enter new title, or leave blank to keep: ");
            String title = myIn.nextLine();
            if (!title.isEmpty()) task.setTitle(title);

            System.out.print("Enter new description, or leave blank to keep: ");
            String description = myIn.nextLine();
            if (!description.isEmpty()) task.setDescription(description);

            System.out.print("Enter new priority (0-5), or leave blank to keep: ");
            String prioStr = myIn.nextLine();
            if (!prioStr.isEmpty()) {
                int newPriority = Integer.parseInt(prioStr);
                if (newPriority >= 0 && newPriority <= 5) task.setPriority(newPriority);
                else System.out.println("Invalid priority.");
            }

            System.out.println("Task updated.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    public static void taskList() {
        tasks.sortTasks();
        if (tasks.size() == 0) {
            System.out.println("No tasks available.");
        } else {
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i++ + ". " + task);
            }
        }
    }

    public static void taskByPriority() {
        try {
            System.out.print("Enter the priority to filter (0-5): ");
            int userPriority = Integer.parseInt(myIn.nextLine());
            if (userPriority >= 0 && userPriority <= 5) {
                for (Task task : tasks) {
                    if (task.getPriority() == userPriority) {
                        System.out.println(task);
                    }
                }
            } else {
                System.out.println("Priority must be between 0 and 5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, priority must be an integer.");
        }
    }

    @Override
    public int compareTo(Task o) {
        if (this.priority != o.priority) {
            return Integer.compare(o.priority, this.priority); // Higher priority first
        }
        return this.title.compareToIgnoreCase(o.title); // Alphabetical
    }
}
