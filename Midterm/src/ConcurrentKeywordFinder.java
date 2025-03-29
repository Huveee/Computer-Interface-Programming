import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentKeywordFinder {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ConcurrentKeywordFinder <keyword> <file1> <file2> ...");
            return;
        }

        String keyword = args[0];
        List<String> foundFiles = new ArrayList<>(); //Keep the found files in the arraylist
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 1; i < args.length; i++) {
            String fileName = args[i]; //getting the filename
            KeywordFinderTask task = new KeywordFinderTask(fileName, keyword, foundFiles); //Implementing the class
            executor.execute(task);
        }

        executor.shutdown();  //shut down the executor
        while (!executor.isTerminated()) {
            // Wait for all tasks to complete
        }

        if (foundFiles.isEmpty()) { //If the keyword was not found
            System.out.println("Keyword not found in any of the specified files.");
        } else {  //If the keyword was found
            System.out.println("Keyword found in the following files:");
            for (String file : foundFiles) {
                System.out.println(file);
            }
        }
    }
}