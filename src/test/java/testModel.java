import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.trg.Base64Decoder.Model;

public class testModel {

	private Model m;
	
	@Before
	public void setUp() throws Exception {
		m = new Model();
	}
	
	@Test
	public void testDecodeWithoutPrettyPrint() throws XPathExpressionException, IOException, ParserConfigurationException, TransformerException, SAXException {
		//View v = new View();
		String decodedValue = m.decodeBase64("VGhpcyBpcyBhIHRlc3QsIHBsZWFzZSBpZ25vcmU=", false);
		assertEquals("This is a test, please ignore\n", decodedValue);
		
		// Base64 with encoded linebreak
		decodedValue = m.decodeBase64("VGhpcyBpcyBhIHRlc3QsIHBsZWFzZSBpZ25vcmU=&#13;", false);
		assertEquals("This is a test, please ignore\n", decodedValue);
		
		// Base64 with linebreak and each row starts with empty space
		decodedValue = m.decodeBase64("VGhpcyBpcyBhIHRlc3QsIHBsZWFzZSBpZ25vcmUxDQpUaGlzIGlzIGEgdGVzdCwgcGxlYXNlIGln\n bm9yZTINClRoaXMgaXMgYSB0ZXN0LCBwbGVhc2UgaWdub3JlMw0KVGhpcyBpcyBhIHRlc3QsIHBs\n ZWFzZSBpZ25vcmU0", false);
		assertEquals("This is a test, please ignore1\r\nThis is a test, please ignore2\r\nThis is a test, please ignore3\r\nThis is a test, please ignore4\n", decodedValue);
	}
	
	@Test
	public void testDecodeWithPrettyPrint() throws XPathExpressionException, IOException, ParserConfigurationException, TransformerException, SAXException {
		String decodedValue = m.decodeBase64("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+DQo8VGVzdFhNTD4NCgk8YWRkcmVzc2VzIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPg0KCQk8YWRkcmVzcz4NCgkJCTxuYW1lPkpvZSBUZXN0ZXI8L25hbWU+DQoJCQk8c3RyZWV0PkJha2VyIHN0cmVldCA1PC9zdHJlZXQ+DQoJCTwvYWRkcmVzcz4NCgk8L2FkZHJlc3Nlcz4NCjwvVGVzdFhNTD4=", true);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<TestXML>\r\n    <addresses xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n        <address>\r\n            <name>Joe Tester</name>\r\n            <street>Baker street 5</street>\r\n        </address>\r\n    </addresses>\r\n</TestXML>\r\n", decodedValue);
	}
	
	@Test 
	public void testEncode() throws UnsupportedEncodingException {
		String encodedValue = m.encode2Base64("This is a test string, ignore please");
		assertEquals("VGhpcyBpcyBhIHRlc3Qgc3RyaW5nLCBpZ25vcmUgcGxlYXNl", encodedValue);
	}

}
