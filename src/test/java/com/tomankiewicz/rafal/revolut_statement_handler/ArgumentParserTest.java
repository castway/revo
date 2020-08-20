package com.tomankiewicz.rafal.revolut_statement_handler;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

class ArgumentParserTest {

	@Test
	void theParsingMethodShouldCorrectlyParseTheArgsPassedAsParams() {

		ArgumentParser argParser = new ArgumentParser();
		String[] args = {"first", "second", "third"};
		String[] emptyArgs = {};
		
		assertThat(argParser.parseClArguments(args).getArgList(), contains("first", "second", "third"));
		assertThat(argParser.parseClArguments(emptyArgs).getArgList(), is(emptyCollectionOf(String.class)));
		
		
	}

}
