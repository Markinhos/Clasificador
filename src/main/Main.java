package main;
import java.io.File;

import IO.Reader;

public class Main {
	public static void main(String args[]){
		Reader r = new Reader("Listado_TT.txt");
		String l = "";
		while((l = r.readLine()) != null){
			System.out.println(l);
		}
	}
}
