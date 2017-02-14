package com.trg.Base64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Model {

	// Add another method for decoding string? If xml one fails, use second one in controller?

	public String decodeBase64(String base64, boolean usePrettyPrint) throws IOException, ParserConfigurationException, XPathExpressionException, TransformerException, SAXException {
		byte[] decoded = Base64.getMimeDecoder().decode(removeEncodedCharacters(base64, "&#13;" , " "));
		String returnValue = "";
		
		returnValue =  (new String(decoded, "UTF-8") + "\n");
		
		if(usePrettyPrint) {
			returnValue = prettyPrintXML(returnValue);
		}
		return returnValue;
	}
	
	private String removeEncodedCharacters(String str, String whatToRemove, String replacedWith) {
		String tidyStr = str.replace(whatToRemove, replacedWith);
				
		return tidyStr;
	}
	
	private String prettyPrintXML(String xml) throws IOException, ParserConfigurationException, XPathExpressionException, TransformerException, SAXException, UnsupportedEncodingException {	
		
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
	}
	
	public String encode2Base64(String value2Encode) throws UnsupportedEncodingException {
		byte[] encoded = Base64.getMimeEncoder().encode(value2Encode.getBytes("UTF-8"));
		return new String(encoded);
	}
}
