package com.tomankiewicz.rafal.revolut_statement_handler;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataPrepTest {

	private static DataHandler dataHandler;
	
	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		
		FileNames.createOkTestFile();
		dataHandler = new DataHandler();
	}
	@Test
	void getIterableDataMethodShouldReturnAnInstanceOfIterable() {
		
		DataPrep dataPrepper = new DataPrep();
		assertTrue(dataPrepper.getIterableData(FileNames.TEST_FILE_CORRECT) instanceof Iterable<?>);

	}
	
	@Test
	void attemptToUseWrongFormatFileShouldTerminateTheApplication() throws Exception {
		
		FileNames.createWrongFormatTestFile();
		
		int statusCode = catchSystemExit(() -> dataHandler.provideSpendingsSum(FileNames.TEST_FILE_WRONG_FORMAT));
		assertEquals(1, statusCode);
	}

}
