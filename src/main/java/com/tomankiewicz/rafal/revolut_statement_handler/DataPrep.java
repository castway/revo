package com.tomankiewicz.rafal.revolut_statement_handler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

class DataPrep {

	private FileOpener fileOpener;
	private Iterable<CSVRecord> records;
	
	DataPrep(){
		this.fileOpener = new FileOpener();
	}
	
	Iterable<CSVRecord> getIterableData(String file) {

		try {
			records = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(fileOpener.getFileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
			System.exit(1);
			
		} catch (IOException e) {
			System.err.println("There was a problem parsing file: " + e.getMessage());
			System.exit(1);
		}
		
		return records;
	}
	
}
