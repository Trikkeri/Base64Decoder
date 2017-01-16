package com.trg.Base64Decoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Model {
	
	public Model()  {
		
	}

	public String decodeBase64(String base64) {
		byte[] decoded = Base64.getMimeDecoder().decode(base64);
		String returnValue = "";
		
		try {
			returnValue =  (new String(decoded, "UTF-8") + "\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
}
