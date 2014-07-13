package main;

import clasificador.Clasificador;
import IO.Reader;

public class Main {
	public static void main(String args[]){
		Reader r = new Reader("Listado_TT.txt");
		String l = "";
		while((l = r.readLine()) != null){
			String result = Clasificador.clasificarTT(l);
			System.out.println(result);
		}
	}
}
