package clasificador;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClasificadorTest {

	@Test
	public void testClasificarFeeling() {
		Clasificador cl = new Clasificador("WE LOVE MEL FRONCKOWIAK");
		String ttClasificado = cl.clasificarTT();
		assertEquals("WE LOVE MEL FRONCKOWIAK, Feelings, LEXICAL", ttClasificado);
	}
	
	@Test
	public void testClasificarEventoDeportivo(){
		Clasificador cl = new Clasificador("AC Milan 1-2 FC Barcelona");
		String ttClasificado = cl.clasificarTT();
		assertEquals("AC Milan 1-2 FC Barcelona, Sport Event, LEXICAL", ttClasificado);		
	}
	
	@Test
	public void testClasificarDBPedia(){
		Clasificador cl = new Clasificador("Aaron Sorkin");
		String ttClasificado = cl.clasificarTT();
		assertEquals("Aaron Sorkin, person, DBPEDIA", ttClasificado);		
	}

	@Test
	public void testClasificarIMDB(){
		Clasificador cl = new Clasificador("Addicted To Food");
		String ttClasificado = cl.clasificarTT();
		assertEquals("Addicted To Food, Film, OMDB", ttClasificado);		
	}
}