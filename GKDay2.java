public class GKDay2{
	public static void main(String[]args){

		final int TOTAL_THREADS = 1000;
		final int MAX_NUMBER = 1000000;

		Thread[] threads = new Thread[TOTAL_THREADS];

		// Each thread stores its own count
		int[] counts = new int[TOTAL_THREADS];

		// Create 1000 threads
		for (int i = 0; i < TOTAL_THREADS; i++) {
			final int threadNumber = i;
			threads[i] = new Thread(() -> {
			// Determine this thread's range
				int start = (threadNumber * MAX_NUMBER/ TOTAL_THREADS) + 1;

				int end = ((threadNumber + 1) * MAX_NUMBER/ TOTAL_THREADS);

				int count = 0;

				// Count numbers in this thread's range
				for (int number = start; number <= end; number++) {
					count++;
				}

				// Store this thread's result
				counts[threadNumber] = count;
			});

		// Start the thread
			threads[i].start();
		}

		// Wait for all 1000 threads to finish
		for (int i = 0; i < TOTAL_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted!");
				return;
			}
		}

		// Add up the results from all threads
		int total = 0;

		for (int i = 0; i < TOTAL_THREADS; i++) {
			total += counts[i];
		}

		// Display final result
        System.out.println("Total count: " + total);




	}
}