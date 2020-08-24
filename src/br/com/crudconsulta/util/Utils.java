package br.com.crudconsulta.util;

import java.nio.file.*;
import java.net.*;
import java.io.*;

public class Utils {
	public static InputStream getConfigFile(String file) {
		InputStream is = null;
		try {
			String path = Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = Paths.get(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			is = new FileInputStream(path + "/" + file);
		} catch(URISyntaxException e) {
			e.getMessage();
		} catch(FileNotFoundException e) {
			e.getMessage();
		}
		return is;
	}
}