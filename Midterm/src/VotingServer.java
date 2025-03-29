import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class VotingServer {
    private static final int PORT = 9000;
    private static final String[] OPTIONS = {"Option A", "Option B", "Option C"};  //Implementing the possible options
    private static Map<String, Integer> voteCounts = new HashMap<>(); //Map to store the counts of the votes

    public static void main(String[] args) {
        // Initialize vote counts
        for (String option : OPTIONS) {
            voteCounts.put(option, 0);
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Voting server started on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Send voting options to the client
                    out.println("Available voting options:");
                    for (String option : OPTIONS) {
                        out.println(option);
                    }

                    // Read the client's vote
                    String vote = in.readLine();
                    if (voteCounts.containsKey(vote)) {
                        // Increment the vote count for the selected option
                        voteCounts.put(vote, voteCounts.get(vote) + 1);
                        out.println("Vote cast successfully for " + vote);
                    } else {
                        out.println("Error: Invalid option. Please vote for one of the available options.");
                    }

                    // Display current vote counts
                    System.out.println("Current vote counts:");
                    for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}