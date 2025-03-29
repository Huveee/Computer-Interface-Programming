import java.io.*;
import java.util.Scanner;

public class ToDoListManager {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String taskFile = "todo.txt";

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();
            if (input.equals("exit")) break;

            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                switch (command) {
                    case "add":
                        try (FileWriter writer = new FileWriter(taskFile, true)) {
                            if (parts.length < 2) {
                                System.out.println("Error: Message required");
                                break;
                            }
                            writer.write(parts[1] + "\n");
                            System.out.println("Task added");
                        }
                        break;

                    case "view":
                        File file = new File(taskFile);
                        if (!file.exists() || file.length() == 0) {
                            System.out.println("No tasks in the list");
                            break; // Burada döngü devam eder
                        }
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            System.out.println("To-Do List:");
                            String line;
                            int count = 1; // Count for the number of tasks

                            while ((line = reader.readLine()) != null) {
                                System.out.println(String.valueOf(count) + ". " + line);
                                count++;
                            }
                        }
                        break;

                    default:
                        System.out.println("Error: Unknown command");
                }
            } catch (IOException e) {
                System.out.println("Error: Cannot write to file");
            }
        }
        scanner.close();
    }
}