package clasificador;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClasificadorTest {

	@Test
	public void testClasificarHashtag() {
		Clasificador cl = new Clasificador("#100greatest");
		String ttClasificado = cl.clasificarTT();
		assertEquals("#100greatest, HASHTAGS", ttClasificado);
	}
	
	@Test
	public void testClasificarEventoDeportivo(){
		Clasificador cl = new Clasificador("#100greatest");
		String ttClasificado = cl.clasificarTT();
		assertEquals("AC Milan 1-2 FC Barcelona, EVENTO DEPORTIVO", ttClasificado);		
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
		assertEquals("Addicted To Food, MOVIE, OMDB", ttClasificado);		
	}
}