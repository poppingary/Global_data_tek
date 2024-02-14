import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Assignment1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Assignment1 manager = new Assignment1();
        manager.start();
    }

    public void start() {
        System.out.println("Welcome to Todo Manager!");

        // User story 1: Print name
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");

        // User story 2: List tasks
        List<String> tasks = new ArrayList<>();
        System.out.println("Enter at least 5 tasks for the day:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Task " + (i + 1) + ": ");
            String task = scanner.nextLine();
            tasks.add(task);
        }
        System.out.println("Your tasks for the day:");
        for (String task : tasks) {
            System.out.println("- " + task);
        }

        // User story 3: Order tasks
        System.out.println("How would you like to order your tasks?");
        System.out.println("1. Increasing alphabetical order");
        System.out.println("2. Decreasing alphabetical order");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Collections.sort(tasks);
                break;
            case 2:
                tasks.sort(Collections.reverseOrder());
                break;
            default:
                System.out.println("Invalid choice.");
        }
        System.out.println("Ordered tasks:");
        for (String task : tasks) {
            System.out.println("- " + task);
        }

        // User story 4: Find repeated tasks
        Map<String, Integer> taskFrequencies = new HashMap<>();
        for (String task : tasks) {
            taskFrequencies.put(task, taskFrequencies.getOrDefault(task, 0) + 1);
        }
        Set<String> repeatedTasks = new HashSet<>();
        for (Map.Entry<String, Integer> entry : taskFrequencies.entrySet()) {
            if (entry.getValue() > 1) {
                repeatedTasks.add(entry.getKey());
            }
        }
        if (repeatedTasks.isEmpty()) {
            System.out.println("There are no repeated tasks.");
        } else {
            System.out.println("Repeated tasks:");
            for (String task : repeatedTasks) {
                System.out.println("- " + task + " (repeated " + taskFrequencies.get(task) + " times)");
            }
        }

        System.out.println("Thank you for using Todo Manager!");
    }
}
