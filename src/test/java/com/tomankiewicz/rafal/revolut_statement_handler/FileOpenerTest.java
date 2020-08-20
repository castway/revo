package com.tomankiewicz.rafal.revolut_statement_handler;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileOpenerTest {

	static FileOpener fileOpener;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		fileOpener = new FileOpener();
		
		FileNames.createOkTestFile();
	}

	@Test
	void fileOpenerShouldReturnAnewReaderInstanceEachTimeGetMethodIsInvoked() throws FileNotFoundException {

		Reader firstInstance = fileOpener.getFileReader(FileNames.TEST_FILE_CORRECT);
		Reader secondInstance = fileOpener.getFileReader(FileNames.TEST_FILE_CORRECT);

		// check if the two variables point to different objects
		
		assertTrue(firstInstance != secondInstance);
	}
	
	@Test
	void getMethodShouldThrowExceptionIfFileNotFound() {
		
		/*
		 * Cannot test the getFileReader(String file) method itself, because it's catch block terminates the program.
		 * The method invokes the constructor as below, which throws the exception.
		 * 
		 * EDIT: An additional library 'SystemLambda' allows testing methods invoking System.exit(), 
		 * see whenFileNotFoundTheApplicationShouldBeTerminatedWithStatusCode1()
		 */
		
		assertThrows(FileNotFoundException.class, () -> new FileReader("nonexistentFile"));
	}
	
	@Test
	void whenFileNotFoundTheApplicationShouldBeTerminatedWithStatusCode1() throws Exception {
		
		int statusCode = catchSystemExit(() -> fileOpener.getFileReader("nonexistentFile.csv"));
		
		assertEquals(1, statusCode);
	}

}
