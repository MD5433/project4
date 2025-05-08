// Main.java
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final String FILENAME = "tasks.json";
    static final Scanner myIn = new Scanner(System.in);
    static final TaskCollection tasks = new TaskCollection();

    public static void main(String[] args) {
        try {
            tasks.loadTask(FILENAME);
        } catch (IOException e) {
            System.out.println("No existing task file found. Starting with an empty list.");
        }

        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(myIn.nextLine());
                switch (choice) {
                    case 1: addTask(); break;
                    case 2: removeTask(); break;
                    case 3: listTasks(); break;
                    case 4: listByPriority(); break;
                    case 0:
                        try {
                            tasks.saveTask(FILENAME);
                            System.out.println("Tasks saved successfully.");
                        } catch (IOException e) {
                            System.out.println("Error saving tasks.");
                        }
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static void displayMenu() {
        System.out.println("\nTask Manager Menu:");
        System.out.println("1. Add a task");
        System.out.println("2. Remove a task");
        System.out.println("3. List all tasks");
        System.out.println("4. List tasks by priority");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    static void addTask() {
        System.out.print("Enter title: ");
        String title = myIn.nextLine();
        System.out.print("Enter description: ");
        String description = myIn.nextLine();
        System.out.print("Enter priority (1-5): ");
        int priority = Integer.parseInt(myIn.nextLine());
        tasks.addTask(new Task(title, description, priority));
    }

    static void removeTask() {
        listTasks();
        System.out.print("Enter task number to remove: ");
        int index = Integer.parseInt(myIn.nextLine()) - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.removeTask(index);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks to display.");
            return;
        }
        int i = 1;
        for (Task task : tasks) {
            System.out.printf("Task #%d:\n%s\n", i++, task);
        }
    }

    static void listByPriority() {
        TaskCollection sorted = new TaskCollection();
        for (Task t : tasks) {
            sorted.addTask(t);
        }
        sorted.sortTasks();
        int i = 1;
        for (Task task : sorted) {
            System.out.printf("Task #%d:\n%s\n", i++, task);
        }
    }
}
