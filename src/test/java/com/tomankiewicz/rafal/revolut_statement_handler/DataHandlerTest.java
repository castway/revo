package com.tomankiewicz.rafal.revolut_statement_handler;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataHandlerTest {

	private static DataHandler dataHandler;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
		dataHandler = new DataHandler();
		
		FileNames.createNokHeadersTestFile();
		FileNames.createOkTestFile();
		
	}
	
	@AfterAll
	static void cleanup() {
		
		/*
		 *  The following code throws java.nio.file.FileSystemException
		 *  The reason is supposedly the fact that there is a known Java bug
		 *  preventing the file deletion. For details see:
		 *  https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4715154
		 *  
		 */
		
//		boolean deleted = false;
//		try {
//			deleted = Files.deleteIfExists(Paths.get(FILE_NAME));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(deleted);
	}

	@Test
	void provideSpendingsSumMethodShouldTerminateApplicationIfWrongFileFormat() throws Exception {
		
		int statusCode = catchSystemExit(() -> dataHandler.provideSpendingsSum(FileNames.TEST_FILE_WRONG_HEADERS));
		
		assertEquals(1, statusCode);
	}
	
	@Test
	void provideSpendingsSumMethodShouldReturnACorrectSumIfFileOk() {
		
		double sum = dataHandler.provideSpendingsSum(FileNames.TEST_FILE_CORRECT);
		assertEquals(70, sum);
	}
	
	@Test
	void provideSpendingsSumIncomeReducedShouldTerminateApplicationIfWrongFileFormat() throws Exception {
		
		int statusCode = catchSystemExit(() -> dataHandler.provideSpendingsIncomeReduced(FileNames.TEST_FILE_WRONG_HEADERS));
		
		assertEquals(1, statusCode);
	}
	
	@Test
	void provideSpendingsSumIncomeReducedMethodShouldReturnACorrectValueIfFileOk() {
		
		double sum = dataHandler.provideSpendingsIncomeReduced(FileNames.TEST_FILE_CORRECT);
		assertEquals(-30, sum);
	}
	
	@Test
	void dataHandlerShouldProvideFileName() {
		
		String fileInfo = dataHandler.provideFileInfo(FileNames.TEST_FILE_CORRECT);
		
		assertTrue(fileInfo.contains(FileNames.TEST_FILE_CORRECT));
	}
	
	@Test
	void dataHandlerShouldProvideTheFilesTimespan() {
		
		String fileInfo = dataHandler.provideFileInfo(FileNames.TEST_FILE_CORRECT);
		
		assertTrue(fileInfo.contains("TIME PERIOD: "));

	}
	
	@Test
	void dataHandlerShouldProvideTheNumberOfTransactions() {
		
		String fileInfo = dataHandler.provideFileInfo(FileNames.TEST_FILE_CORRECT);
		
		assertTrue(fileInfo.contains("NUMBER OF TRANSACTIONS: "));

	}

}
