package com.tomankiewicz.rafal.revolut_statement_handler;

import org.apache.commons.cli.CommandLine;

public class Runner {

	private final String[] args;
	private final ArgumentParser argParser;
	private DataHandler dataHandler;
	
	Runner(String[] args) {
		this.args = args;
		this.argParser = new ArgumentParser();
	}
	
	void run(){

		CommandLine line = argParser.parseClArguments(args);
		
		if (line != null && line.hasOption("filename")) {
			
			String file = line.getOptionValue("filename");
			
			if (line.getOptions().length == 1) {
				argParser.displayHelp();
			}
			
			dataHandler = new DataHandler();
			
			if (line.hasOption("sum")) {
				double sum = dataHandler.provideSpendingsSum(file);
				System.out.println("SUMMARIZED FILE SPENDINGS: " + sum + " PLN");
			}
			
			if (line.hasOption("deductIncome")) {
				double sum = dataHandler.provideSpendingsIncomeReduced(file);
				System.out.println("SUMMARIZED FILE SPENDINGS NET: " + sum + " PLN");
				// TODO: get list of income sources
			}
			
		} else {
			argParser.displayHelp();
		}
	}
	
			


}
