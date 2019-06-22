package org.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

    public class DateFormatTest {
	private static final ThreadLocal<DateFormat> df= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
        return new SimpleDateFormat("yyyyMMdd");
        }
        };

        public Date convert(String source)throws ParseException{
        Date d = df.get().parse(source);
        return d;
        }
	  
	      public static void main(String[] args) {
		  final DateFormatTest t = new DateFormatTest();
		  Callable<Date> task = new Callable<Date>(){
		      public Date call() throws Exception {
		          return t.convert("20170420");
		      }
		  };
		   
		  //lets try 2 threads only
		  ExecutorService exec = Executors.newFixedThreadPool(2);
		  List<Future<Date>> results = new ArrayList<Future<Date>>();
		   
		  //perform 5 date conversions
		  for(int i = 0 ; i < 5 ; i++){
		      results.add(exec.submit(task));
		  }
		  exec.shutdown();
		   
		  //look at the results
		  for(Future<Date> result : results){
		      try {
				System.out.println(result.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

		
	}
	  }
