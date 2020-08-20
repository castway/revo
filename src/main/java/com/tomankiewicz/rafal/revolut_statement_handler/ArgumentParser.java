package com.tomankiewicz.rafal.revolut_statement_handler;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser {

	private HelpFormatter formatter;
	private OptionsDefinition optionsDefinition;
	
	public ArgumentParser() {

		this.formatter = new HelpFormatter();
		this.optionsDefinition = new OptionsDefinition();
	}

	CommandLine parseClArguments(String[] args) {

		Options options = optionsDefinition.getCreatedOptions();
		CommandLineParser parser = new DefaultParser();
		
		CommandLine line = null;
		
			try {
				line = parser.parse(options, args);
			} catch (ParseException e) {
				System.err.println("Unable to parse the arguments: " + e.getMessage());
			}
		
		return line;
	}

	public void displayHelp() {
		Options options = optionsDefinition.getCreatedOptions();
		formatter.printHelp("Revolut statement handler <revo>", options);
		
	}

	
}
