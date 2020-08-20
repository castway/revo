# revo

### Description 
revo is a Command Line Interface application for handling Revolut account statements. If you use Revolut and like to keep track of your spendings, you are probably aware that the summaries provided by Revolut do not take into account the fact that some expenses are split with your friends.
Revolut provides summaries of your gross expenses, even though you might have been reimbursed for some of them.

### Prerequisites
In order to execute the JAR file, you will need Java Runtime Environment (JRE) installed. To check if you are all set, follow these steps regardless of the operating system of your choice:

1. Open command line.

2. Type in: `java -version`

3. If the command has been recognized and the Java version is displayed, you can proceed to **Execution** section.
	Otherwise, visit https://www.oracle.com/java/technologies/javase-downloads.html and download JRE.
	
### Execution
Follow the steps below to run the application:

1. Download *revo.jar* from the 'Releases' section of this repo.

2. Export the expense summary of your chosen period of time from your Revolut app. It is important to export it as an Excel file.

3. Save the exported expense summary on your computer, preferably in the same directory as *revo.jar.*

4. Open command line.

5. Enter one of the following commands:<br>
		`java -jar revo.jar -f <filename>` to display the applications help section.<br>
		`java -jar revo.jar -f <filename> -s` to display a gross sum of your expenses in your Revolut summary.<br>
		`java -jar revo.jar -f <filename> -d` to display a net sum of your expenses (gross sum reduced by reimbursements from other users).<br>
	*You can use all the options simultaneously, so the following will also work: `java -jar revo.jar -f <filename> -s -d`*<br>
	
	> **NOTE** `<filename>` pertains to a full file name with extension (e.g. testfile.csv). If the file of your choice is stored in a different location than the executable jar file, you should include the relative path in your `<filename>` (e.g. myFolder/testfile.csv).
	> **NOTE** The application accepts csv files with a certain column schema, as exported from Revolut application v7.12.1. No backward or future compatibility can be guaranteed. 
					
	 
 
