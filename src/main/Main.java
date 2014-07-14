package main;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

import clasificador.Clasificador;
import IO.Reader;

public class Main {
	public static void main(String args[]){
		Reader r = new Reader("Listado_TT.txt");
		String l = "";
		
		long startTime = System.currentTimeMillis();
        //List <Future<String>> results = executor.
        List<Clasificador> listTrendingTopics = new Vector<Clasificador>();
		while((l = r.readLine()) != null){
			listTrendingTopics.add(new Clasificador(l));
			System.out.println("Adding classifier for tt " + l);
			//String result = Clasificador.clasificarTT(l);
			//System.out.println(result);
		}
		try {
			ExecutorService executor = Executors.newFixedThreadPool(10);
			List <Future<String>> results = executor.invokeAll(listTrendingTopics);
	        executor.shutdown();
	        
	        System.out.println("Printing results");
	        for (Future<String> result : results) {
	            System.out.println(result.get());
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finishTime = System.currentTimeMillis();
		
		long elapsedTime = finishTime - startTime;

		double seconds = (double)elapsedTime / 1000.0;
		System.out.println("It took " + seconds);
		
	}
}
