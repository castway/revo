package com.tomankiewicz.rafal.revolut_statement_handler;

import org.apache.commons.cli.Options;

public class OptionsDefinition {

		private Options options;
		
	OptionsDefinition(){
		
		this.options = new Options();
		configureOptions();
	}
	
	void configureOptions() {

		options.addOption("f", "filename", true, "use given file as data source");
		options.addOption("s", "sum", false, "calculate the sum of all spendings listed in file");
		options.addOption("d", "deductIncome", false, "calculate the sum of all spendings listed in file, deduct income");
	}
	
	Options getCreatedOptions() {
		return options;
	}
}
