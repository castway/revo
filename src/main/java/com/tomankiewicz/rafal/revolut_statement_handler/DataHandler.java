package com.tomankiewicz.rafal.revolut_statement_handler;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.csv.CSVRecord;


class DataHandler {

	private DataPrep dataPreper;

	DataHandler() {

		this.dataPreper = new DataPrep();
	}

	double provideSpendingsSum(String file) {

		Iterable<CSVRecord> records = dataPreper.getIterableData(file);
		List<String> data = new ArrayList<>();
		
		for (CSVRecord record : records) {
			try {
				data.add(record.get("Paid Out (PLN)"));
			} catch (IllegalArgumentException e) {
				System.err.println("Unrecognized file format: " + e.getMessage());
				System.exit(1);
			}
		}
		
		
		return data.stream()
				   .filter(s -> s.length() > 0)
				   .map(s -> s.replaceAll("(\\D)", ""))
				   .mapToDouble(Double::parseDouble)
				   .map(d -> d / 100)
				   .sum();
		
	}

	double provideSpendingsIncomeReduced(String file) {

		Iterable<CSVRecord> records = dataPreper.getIterableData(file);

		List<TwoColumnHolder> incomeList = new ArrayList<>();
		
		for (CSVRecord record : records) {
			try {
				incomeList.add(new TwoColumnHolder(record.get("Reference"), record.get("Paid In (PLN)")));
			} catch (IllegalArgumentException e) {
				System.err.println("Unrecognized file format: " + e.getMessage());
				System.exit(1);
			}
		}


		double spendings = provideSpendingsSum(file);
		double income = incomeList.stream()
								  .filter(s -> s.getFirstColumn().startsWith("Od"))
								  .map(s -> s.getSecondColumn().replaceAll("(\\D)", ""))
								  .mapToDouble(Double::parseDouble)
								  .map(d -> d / 100)
								  .sum();
		
		return spendings - income;
	}

	public String provideFileInfo(String file) {

		Iterable<CSVRecord> records = dataPreper.getIterableData(file);
		List<TwoColumnHolder> fileInfo = new ArrayList<>();
		
		for (CSVRecord record : records) {
			try {
				fileInfo.add(new TwoColumnHolder(record.get("Completed Date"), record.get("Category")));
			} catch (IllegalArgumentException e) {
				System.err.println("Unrecognized file format: " + e.getMessage());
				System.exit(1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		String section = "***********************************";
		String fileName = "DATA SOURCE FILE: " + file;
		String timeSpan = "TIME PERIOD: " + 
							fileInfo.get(fileInfo.size() - 1).getFirstColumn() + " - " + 
							fileInfo.get(0).getFirstColumn();
		String transactionCount = "NUMBER OF TRANSACTIONS: " + fileInfo.size();
		
		formatter.format("%s%n", section);
		formatter.format("%s%n", fileName);
		formatter.format("%s%n", timeSpan);
		formatter.format("%s%n", transactionCount);
		formatter.format("%s%n", section);
		
		formatter.close();
		
		return sb.toString();
	}

}
