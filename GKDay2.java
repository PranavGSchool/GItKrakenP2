public class GKDay2{
	public static void main(String[]args){

		final int TOTAL_THREADS = 1000;
		final long MAX_NUMBER = 1000000000L;

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Long>> futures = new ArrayList<>();


}