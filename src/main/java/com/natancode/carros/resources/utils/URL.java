package com.natancode.carros.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	//Tirando espaços dos nomes
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}