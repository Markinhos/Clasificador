package clasificador.xmlParser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class XmlParserTest {

	@Test
	public void testGetLabel() {
		XmlParser xmlParse = new XmlParser();
		String label = null;
		try {
			String s = this.readFile("test.xml");
			label = xmlParse.getLabelFromDBPediaXMLString(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(label, "person");
	}

	private String readFile( String file ) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    reader.close();
	    return stringBuilder.toString();
	}

}
