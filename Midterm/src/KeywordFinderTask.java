import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class KeywordFinderTask implements Runnable {
    private final String fileName; //Implementing the attributes of the class
    private final String keyword;
    private final List<String> foundFiles;

    public KeywordFinderTask(String fileName, String keyword, List<String> foundFiles) { //Create the constructor for the class
        this.fileName = fileName;
        this.keyword = keyword.toLowerCase(); // Case-insensitive search
        this.foundFiles = foundFiles;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) { //Reading the files
            String line;
            while ((line = reader.readLine()) != null) { //Control the line in the file if it is empty
                if (line.toLowerCase().contains(keyword)) { //Lowercase the characters of the keyword
                    synchronized (foundFiles) {
                        foundFiles.add(fileName);
                    }
                    break; // Stop searching this file if the keyword is found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName + ": " + e.getMessage());
        }
    }
}