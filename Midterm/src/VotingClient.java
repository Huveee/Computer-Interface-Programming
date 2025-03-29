import java.io.*;
import java.net.*;

public class VotingClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Read and display voting options from the server
            String line;
            System.out.println("Connected to voting server.");
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Available voting options:")) {
                    System.out.println(line);
                    continue;
                }
                System.out.println(line);
            }

            // Prompt the user to enter their vote
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your vote: ");
            String vote = userInput.readLine();
            out.println(vote);

            // Read confirmation message from the server
            String response = in.readLine();
            System.out.println(response);

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}