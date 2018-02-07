import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab15Main {
	
	public static void createFile(String dirString, String fileString) {
		//if u don't want this to go in a folder, just use the get method taking in one parameter for filename
		//
		//
		Path filePath = Paths.get(dirString, fileString);
		
		if(Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("File created successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		
			}
			catch (Exception e) {
				System.out.println("Not sure what happened. Contact customer service");
			}
		}
	}

	public static ArrayList<String> createCountryList() {
		ArrayList<String> countryArrayList = new ArrayList<String>();
		Path readFile = Paths.get("countries.txt"); 
		File file = readFile.toFile();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			
			while(line != null) {
				//System.out.println(line);
				//splits the line up on commas, then adds them to an array
				//then uses the pieces from the array to create a new Employee object and add that to employeeArrayList
				countryArrayList.add(line);
				line = reader.readLine();
			}
			reader.close(); 
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong with this!");
			e.printStackTrace();
		}
		return countryArrayList;
	}
	
	public static void writeToFile(String countryName) {
		
		Path writeFile = Paths.get("countries.txt"); //use dir name if file is in dir, not if not
		
		File file = writeFile.toFile(); //toFile turns file into file object
		//intro to Streams! more efficient than scanner
		
		try {
			
			//below: true parameter allows us to append to end of file
			PrintWriter printOut = new PrintWriter(new FileOutputStream(file, true));
//			printOut.println("Writing to files is cool.");
//			printOut.println("Writing to files is cool...next!");
			printOut.println(countryName);
			printOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//Menu with three options
		//1. Display a list of countries that have been saved in a file
		//2. Prompt the user to enter a country and then write the country to the file of countries
		//3. Send a goodbye message and quit
		
	
		System.out.println("Welcome to the country list maintainer!");
		char keepGoing = 'y';
		ArrayList<String> countryList = new ArrayList<String>();
		countryList = createCountryList();
		while (keepGoing == 'y') {
			System.out.println("Enter 1 to display the list of countries, 2 to add a country to the list, or 3 to quit.");
			int userChoice = scan.nextInt();
			switch (userChoice) {
			case 1:
				System.out.println("Here are the countries in the list");
				countryList = createCountryList();
				for (String e: countryList) {
					System.out.println(e);
				}
				break;
			case 2:
				System.out.println("Please enter the name of a country:");
				String userCountry = scan.next();
				writeToFile(userCountry);
				break;
				
			case 3:
				keepGoing = 'n';
			}
		}
		System.out.println("Goodbye!");
		scan.close();
	}

}
