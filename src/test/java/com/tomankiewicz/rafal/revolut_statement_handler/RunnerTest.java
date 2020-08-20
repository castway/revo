package com.tomankiewicz.rafal.revolut_statement_handler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunnerTest {

	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	private final static PrintStream originalErr = System.err;
	
	@BeforeAll
	static void setUpStreams() throws Exception {
		
		    System.setOut(new PrintStream(outContent));
		    System.setErr(new PrintStream(errContent));
	}

	@AfterAll
	static void restoreStreams() {
		
		   System.setOut(originalOut);
		   System.setErr(originalErr);
	}
	
	@BeforeEach
	@AfterEach
	void clearOutContent() {
		outContent.reset();
	}
	
	@Test
	void helpShouldBeDisplayedIfNoFilenameOptionPassed() {

		String[] args = {"-s", "-d"};
		Runner runner = new Runner(args);
		
		runner.run();
		assertTrue(outContent.toString().contains("Revolut statement handler"));
	}
	
	@Test
	void helpShouldBeDisplayedIfWrongParametersPassed() {

		String[] args = {"foo", "bar"};
		Runner runner = new Runner(args);
		
		runner.run();
		
		assertTrue(outContent.toString().contains("usage: Revolut statement handler"));
	}
	
	@Test
	void helpShouldBeDisplayedIfOnlyFilenameOptionPassed() {
		
		String[] args = {"-f"};
		Runner runner = new Runner(args);
		
		runner.run();
		assertTrue(outContent.toString().contains("usage: Revolut statement handler"));
	}
	
	@Test
	void helpShouldBeDisplayedIfOnlyFilenameOptionAndFileNamePassed() {
		
		String[] args = {"-f", FileNames.TEST_FILE_CORRECT};
		Runner runner = new Runner(args);
		
		runner.run();
		assertTrue(outContent.toString().contains("usage: Revolut statement handler"));
	}
	
	@Test
	void onlySumShouldBeDisplayedIfOnlySumRequested() throws IOException {
		
		String[] args = {"-f", FileNames.TEST_FILE_CORRECT.replaceFirst("./", ""), "-s"};

		Runner runner = new Runner(args);
		runner.run();
		assertTrue(outContent.toString().contains("SUMMARIZED FILE SPENDINGS: "));
		assertTrue(!outContent.toString().contains("NET"));
	}
	
	@Test
	void onlyNetSpendingsShouldBeDisplayedIfOnlyNetRequested() {
		
		String[] args = {"-f", FileNames.TEST_FILE_CORRECT.replaceFirst("./", ""), "-d"};

		Runner runner = new Runner(args);
		runner.run();
		assertTrue(!outContent.toString().contains("SUMMARIZED FILE SPENDINGS: "));
		assertTrue(outContent.toString().contains("NET"));
	}
	
	@Test
	void bothTotalAndNetSpendingsShouldBeDisplayedIfBothRequested() {
		
		String[] args = {"-f", FileNames.TEST_FILE_CORRECT, "-s", "-d"};

		Runner runner = new Runner(args);
		runner.run();
		assertTrue(outContent.toString().contains("SUMMARIZED FILE SPENDINGS: "));
		assertTrue(outContent.toString().contains("NET"));
	}
	
}
