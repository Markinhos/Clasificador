package clasificador;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClasificadorTest {

	@Test
	public void testClasificarHashtag() {
		String ttClasificado = Clasificador.clasificarTT("#100greatest");
		assertEquals(ttClasificado, "#100greatest, HASHTAGS");
	}
	
	@Test
	public void testClasificarEventoDeportivo(){
		String ttClasificado = Clasificador.clasificarTT("AC Milan 1-2 FC Barcelona");
		assertEquals(ttClasificado, "AC Milan 1-2 FC Barcelona, EVENTO DEPORTIVO");		
	}
	
	@Test
	public void testClasificarDBPedia(){
		String ttClasificado = Clasificador.clasificarTT("Aaron Sorkin");
		assertEquals(ttClasificado, "Aaron Sorkin, agent, DBPEDIA");		
	}

	@Test
	public void testClasificarIMDB(){
		String ttClasificado = Clasificador.clasificarTT("Addicted To Food");
		assertEquals(ttClasificado, "Addicted To Food, title, IMDB");		
	}
}