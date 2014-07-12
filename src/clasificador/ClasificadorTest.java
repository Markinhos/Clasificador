package clasificador;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClasificadorTest {

	@Test
	public void testClasificarHashtag() {
		String ttClasificado = Clasificador.clasificarTT("#100greatest");
		assertEquals("#100greatest, HASHTAGS", ttClasificado);
	}
	
	@Test
	public void testClasificarEventoDeportivo(){
		String ttClasificado = Clasificador.clasificarTT("AC Milan 1-2 FC Barcelona");
		assertEquals("AC Milan 1-2 FC Barcelona, EVENTO DEPORTIVO", ttClasificado);		
	}
	
	@Test
	public void testClasificarDBPedia(){
		String ttClasificado = Clasificador.clasificarTT("Aaron Sorkin");
		assertEquals("Aaron Sorkin, person, DBPEDIA", ttClasificado);		
	}

	@Test
	public void testClasificarIMDB(){
		String ttClasificado = Clasificador.clasificarTT("Addicted To Food");
		assertEquals("Addicted To Food, MOVIE, OMDB", ttClasificado);		
	}
}