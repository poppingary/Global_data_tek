import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Assignment3 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;
    private int nextTaskId = 1;

    public static void main(String[] args) {
        Assignment3 manager = new Assignment3();
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

    private class Task {
        private final int taskId;
        private String title;
        private String text;
        private String assignedTo;

        public Task(String title, String text, String assignedTo) {
            this.taskId = nextTaskId++;
            this.title = title;
            this.text = text;
            this.assignedTo = assignedTo;
        }

        public int getTaskId() {
            return taskId;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public String getAssignedTo() {
            return assignedTo;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setAssignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "taskId=" + taskId +
                    ", title='" + title + '\'' +
                    ", text='" + text + '\'' +
                    ", assignedTo='" + assignedTo + '\'' +
                    '}';
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

        System.out.print("Enter task title: ");
        String title = scanner.next().trim();;
        System.out.print("Enter task text: ");
        String text = scanner.next().trim();;
        System.out.print("Assign task to: ");
        String assignedTo = scanner.next().trim();;

        Task task = new Task(title, text, assignedTo);
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

        System.out.print("Enter new task title (leave blank to keep current): ");
        String title = scanner.next().trim();
        if (!title.isEmpty()) {
            tasks[index].setTitle(title);
        }

        System.out.print("Enter new task text (leave blank to keep current): ");
        String text = scanner.next().trim();
        if (!text.isEmpty()) {
            tasks[index].setText(text);
        }

        System.out.print("Assign to (leave blank to keep current): ");
        String assignedTo = scanner.next().trim();
        if (!assignedTo.isEmpty()) {
            tasks[index].setAssignedTo(assignedTo);
        }

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
        for (Task task : tasks) {
            if (task == null) {
                continue;
            }

            if (task.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(task);
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