package com.tomankiewicz.rafal.revolut_statement_handler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

class FileOpener {

	private Reader in;
	
	Reader getFileReader(String file) throws FileNotFoundException {
		
		if (!file.contains("csv")) {
			throw new FileNotFoundException("use full csv file name with extension");
		}
		try {
			in = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
			System.exit(1);
		}
		
		return in;
	}
	
	Reader getRunningReader() {
		return in;
	}
	
}


