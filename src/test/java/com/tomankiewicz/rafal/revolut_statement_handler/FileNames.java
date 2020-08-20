package com.tomankiewicz.rafal.revolut_statement_handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

final class FileNames {
	
	static final String TEST_FILE_CORRECT = "./test-file-correct.csv";
	static final String TEST_FILE_WRONG_HEADERS = "./test-file-wrong-headers.csv";
	static final String TEST_FILE_WRONG_FORMAT = "./test-file-wrong-format.docx";

	private FileNames() {
		
	}
	
	static void createNokHeadersTestFile() throws IOException {
		
		BufferedWriter writerNokFile = Files.newBufferedWriter(Paths.get(FileNames.TEST_FILE_WRONG_HEADERS));
		
		CSVPrinter printerNokFile = new CSVPrinter(writerNokFile, 
				CSVFormat.EXCEL.withDelimiter(';').withHeader("Title", "Money in", "Money out"));
		
		printerNokFile.printRecord("Od Jan Kowalski", "100,00Â", "");
		printerNokFile.printRecord("Sklep", "", "50,00Â");
		printerNokFile.printRecord("Targ", "", "20,00Â");
		
		printerNokFile.flush();
		printerNokFile.close();
	}
	
	static void createOkTestFile() throws IOException {
		
		BufferedWriter writerOkFile = Files.newBufferedWriter(Paths.get(FileNames.TEST_FILE_CORRECT));
		
		CSVPrinter printerOkFile = new CSVPrinter(writerOkFile, 
				CSVFormat.EXCEL.withDelimiter(';').withHeader("Reference", "Paid In (PLN)", "Paid Out (PLN)"));
		
		printerOkFile.printRecord("Od Jan Kowalski", "100,00Â", "");
		printerOkFile.printRecord("Sklep", "", "50,00Â");
		printerOkFile.printRecord("Targ", "", "20,00Â");
				
		printerOkFile.flush();
		printerOkFile.close();
	}
	
	static void createWrongFormatTestFile() throws IOException {
		
		File file = new File(TEST_FILE_WRONG_FORMAT);
		file.createNewFile();
	}
}
