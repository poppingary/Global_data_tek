import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Assignment2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_TASKS = 100;
    private final String[] tasks = new String[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        Assignment2 manager = new Assignment2();
        manager.start();
    }

    public void start() {
        System.out.println("Welcome to Todo Manager!");

        while (true) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        listTask();
                        break;
                    case 2:
                        addTask();
                        break;
                    case 3:
                        updateTask();
                        break;
                    case 4:
                        deleteTask();
                        break;
                    case 5:
                        searchTask();
                        break;
                    case 0:
                        System.out.println("Thank you for using Todo Manager!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nTodoManager Menu:");
        System.out.println("1. List Tasks");
        System.out.println("2. Add Task");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Search Tasks");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void listTask() {
        if (taskCount == 0) {
            System.out.println("No tasks to list.");
            return;
        }

        Arrays.stream(tasks)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    private void addTask() {
        if (taskCount == MAX_TASKS) {
            System.out.println("Task list is full. Cannot add more tasks.");
            return;
        }

        System.out.print("Enter new task: ");
        String task = scanner.next();
        tasks[taskCount++] = task;
        System.out.println("Task added successfully.");
    }

    private void updateTask() {
        if (taskCount == 0) {
            System.out.println("No tasks to update.");
            return;
        }

        System.out.print("Enter the index of the task to update (1-" + taskCount + "): ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= taskCount) {
            System.out.println("Invalid task index.");
            return;
        }

        System.out.print("Enter the new task description: ");
        String newTask = scanner.next();
        tasks[index] = newTask;
        System.out.println("Task updated successfully.");
    }

    private void deleteTask() {
        if (taskCount == 0) {
            System.out.println("No tasks to delete.");
            return;
        }

        System.out.print("Enter the index of the task to delete (1-" + taskCount + "): ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= taskCount) {
            System.out.println("Invalid task index.");
            return;
        }

        // Shift remaining tasks to fill the gap
        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
        System.out.println("Task deleted successfully.");
    }

    private void searchTask() {
        System.out.print("Enter a search term: ");
        String searchTerm = scanner.next();

        boolean found = false;
        for (String task : tasks) {
            if (task == null) {
                continue;
            }

            if (task.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("- " + task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found matching that term.");
            return;
        }

        System.out.println("Found a task matching that term.");
    }
}