package org.thread;

import java.util.concurrent.ForkJoinPool;

import org.perf4j.StopWatch;

public class ForkJoinWorker {
	public static void main(String[] args) {
		  int processors = Runtime.getRuntime().availableProcessors();
		  System.out.println("No of processors: " + processors);
		  int n = 10;
		  StopWatch stopWatch = new StopWatch();  
		  FibonacciProblem bigProblem = new FibonacciProblem(n);
		  FibonacciTask task = new FibonacciTask(bigProblem);
		  ForkJoinPool pool = new ForkJoinPool(processors);
		  pool.invoke(task);
		  long result = task.result;
		  System.out.println("Computed Result: " + result);
		  stopWatch.stop();
		  System.out.println("Elapsed Time: " + stopWatch.getElapsedTime());
	
		 }

}
