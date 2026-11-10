
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class GKDay2 {

    public static void main(String[] args) throws Exception {

        final int TOTAL_TASKS = 1000;
        final long MAX_NUMBER = 1000000000L;

        // Create a thread pool with 10 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Store the Futures returned by submitted tasks
        List<Future<Long>> futures = new ArrayList<>();

        // Submit 1000 counting tasks
        for (int i = 0; i < TOTAL_TASKS; i++) {

            final int taskNumber = i;

            // Each Callable calculates its own range
            Callable<Long> task = () -> {

                long start = ((long) taskNumber * MAX_NUMBER / TOTAL_TASKS) + 1;

                long end = ((long) (taskNumber + 1) * MAX_NUMBER / TOTAL_TASKS);

                long count = 0;

                // Count the numbers in this range
                for (long number = start; number <= end; number++) {
                    count++;
                }

                return count;
            };

            // Submit the task and save its Future
            Future<Long> future = executor.submit(task);

            futures.add(future);
        }

        // Stop accepting new tasks
        executor.shutdown();

        // Collect the results from all Futures
        long total = 0;

        for (Future<Long> future : futures) {
            total += future.get();
        }

        // Display the final result
        System.out.println("Total count: " + total);
    }
}
