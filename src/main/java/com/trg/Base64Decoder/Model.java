package com.trg.Base64Decoder;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Model {

	public String decodeBase64(String base64) {
		byte[] decoded = Base64.getMimeDecoder().decode(removeEncodedCharacters(base64, "&#13;" , " "));
		String returnValue = "";
		
		try {
			returnValue =  (new String(decoded, "UTF-8") + "\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return prettyPrintXML(returnValue);
	}
	
	private String removeEncodedCharacters(String str, String whatToRemove, String replacedWith) {
		String tidyStr = str.replace(whatToRemove, replacedWith);
				
		return tidyStr;
	}
	
	private String prettyPrintXML(String xml) {
		try {
			org.w3c.dom.Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder()
					.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				node.getParentNode().removeChild(node);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 4);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTFT-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StringWriter stringWriter = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			return stringWriter.toString();
		} catch(Exception e) {
			 throw new RuntimeException(e);
		}	
	}
}
