package pkgStreamBundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
/*
 	*Program: Team Project
 	*Author: Team Authors
 	*Date: 4/29/2022
 	*Purpose: To make a streaming service bundle service.
*/
import java.util.*;

public class Sale {
	//calling all the public variables ill need throughout the code - pie
	Services services[]; // Reminder arrays have fixed sizes - pie
	CustomerInfo customers[];
	ArrayList<Receipts> receipt = new ArrayList<Receipts>();//Reminder array lists don't have fixed sizes - pie //[Fixed] Constructed new array list and assigned it to receipt variable - justin
	Scanner keyboard; // Scans the keyboard for input - pie
	int selCust; // Select current Customer - pie
	int cId = 0;
	String fName = "", cEmail = "";
	String pack = ""; // which package are they using - pie
	double payment = 0.00; // how much they are paying - pie
	String paymentType = ""; // how are they paying - pie
	String reviewdate = "";
	double subtotal = 0.00, total = 0.00;
	String serviceList = "";
	int count = 0;
	


/******************************************Constructor******************************************/
	Sale()					
	{
		//Declare the objects
		services = new Services[8];
		customers = new CustomerInfo[6];
		keyboard = new Scanner(System.in);
		selCust = -1; //Reminder we use this to get the index of the selected Customer in the array - pie
		
		
		
	}

/****************************************End Constructor *************************************/
	
	
/***************************************Load Services
 * @throws FileNotFoundException *****************************************/

	//load services
	void loadServies() throws FileNotFoundException {
		
		// Working Variables - pie
		FileReader servfile = new FileReader("services.dat");
		Scanner fileReader = new Scanner(servfile);
		
		//Create the Variables for the file - pie
		int i = 0; //index for the array - pie
		int sId = 0; // Service Id - pie
		String sName = ""; // Service Name - pie
		float price = 0; //Service Price - pie
		String desc = ""; // Service Descripton - pie
		String eachLine = ""; // tell where to end each line - pie
		StringTokenizer st; // name - pie
		
		while (fileReader.hasNextLine()) // Test for the eof (end of file)
		{
			//make a service to add to the array - pie
			eachLine = fileReader.nextLine();
			st = new StringTokenizer(eachLine, ",");
			while (st.hasMoreTokens()) {

				sId = Integer.parseInt(st.nextToken());
				sName = st.nextToken();
				price = Float.parseFloat(st.nextToken());
				desc = st.nextToken();
				
				//add services to the array - pie
				services[i] = new Services(sId, sName, price, desc);
				i++;
				
			}
			
		}
		
		//Reviewer Info
		fileReader.close();
		
	}
	

/***************************************EndOfLoadServices*************************************/
	
	
	
/****************************************LoadCustomerInfo************************************/
	
	//load Customers
		void loadCustomers() throws FileNotFoundException {
			
			// Working Variables for the Customer - pie
			FileReader custfile = new FileReader("customer.dat");
			Scanner fileReader = new Scanner(custfile);
			
			//Create the Variables for the file - pie
			int i = 0; //index for the array - pie
			int custID = 0;
			String fullname ="";
			String email = "";
			String password = "";
			String eachLine = "";
			StringTokenizer st;
			
			while (fileReader.hasNextLine()) // Test for the eof (end of file) - pie
			{
				
				//I copy pasted from above - pie
				eachLine = fileReader.nextLine();
				st = new StringTokenizer(eachLine, ",");
				while (st.hasMoreTokens()) {
					// remember the order of your files
					custID = Integer.parseInt(st.nextToken());
					fullname = st.nextToken();
					email = st.nextToken();
					password = st.nextToken();
					
					//add customers to the array - pie
					customers[i] = new CustomerInfo(custID, fullname, email, password);
					i++;
					
				}
				
			}
			
			//Reviewer Info
			fileReader.close();
			
		}
	
/***************************************EndLoadCustomerInfo**********************************/
	
	
/***************************************Login***********************************************/

	//login
		boolean login()
		{
			
			//Make the login for the user - pie
			String entid = "", entpass = "";
			int numAttempts = 0; // to count how many times they attempt to login in before force close - pie
			System.out.println("Welcome to Service Solar");
			do
			{ // Get review id and password - pie
				System.out.print("Enter your UserName: ");
				entid = keyboard.next(); // get the username - pie
				int rid = Integer.parseInt(entid);  	// Convert the inputed String to an integer. - pie
				System.out.print("Enter your Password: ");
				entpass = keyboard.next(); //gets the password - pie
				
				// search the Array for the reviewer
				for (int i = 0; i < 3; i++) {
				 if (rid == customers[i].getCustID() && entpass.equals(customers[i].getPassword()) ) {
					 
					 selCust = i; //If they have a matching ID and Passsword send the Customer data up - pie
					 
					 cId = customers[i].getCustID();
					 fName = customers[i].getFullname();
					 cEmail = customers[i].getEmail();
					 
					 return true;
					
				 }
				 }
				System.out.println("Invalid Password!"); //If it doesnt match tell them
				numAttempts++; // if they messed up add 1 to the signin attempts - pie
			}
			
			while (numAttempts < 3);
			return false; // once it hits 3 tell them no - pie
			
		}

/**********************************EndOfLogin********************************************/
	
	
/*********************************ChooseOption
 * @throws IOException *****************************************/
	
	void ChooseOption() throws IOException {
		
		// Time to let the user tell us what they want to do - pie
		//Variables - pie
		int opt1= 0; // Just tells us what the user wants to do - pie
		
		System.out.println("Welcome to Service Solar.");
		System.out.println("How can we help you today " +  customers[selCust].getFullname() + "?");
		// Say their name so they feel welcomed and made sure they signed into the proper account - pie
		
		//Give them 3 options so we know what they want to do - pie
		System.out.println("     1.Purchase Packages");
		System.out.println("     2.View Receipts");
		System.out.println("     3.Sign Out");
		
		System.out.println("We have many options please pick one.");
		opt1 = keyboard.nextInt();
		//Get their option - pie
		
		if (opt1 == 1) {
			
			//Allow them to buy our services - pie
			System.out.println("Thank you for choosing 1.Purchase Packages.\n");
			pickPackages();
			
		}
		else if (opt1 == 2) {
			
			//Allow them to View their previous receipt - pie
			System.out.println("Thank you for choosing 2.View Receipts.\n");
			readReceipts();
			
		}
		else if (opt1 == 3) {
			
			//Exit the program - pie
			System.out.println("Thank you for using our services.");
			System.exit(100);
			
		}
		else {
			
			//If they give a invalid input, tell them and start again - pie
			System.out.println("Im sorry, that was a incorrect option.\n");
			ChooseOption();
			
		}
		
		/*for (int i=0; i < services.length; i++)
		{
			System.out.println((i+1) + ". " + services[i].getsName());
		}*/
		
	}
	
/*******************************EndChooseOption***************************************/


/**********************************Packages
 * @throws IOException *****************************************/
	
	void pickPackages() throws IOException {
		
		//Variables used - pie
		//Lets them choose between premade or custom packages - pie
		int opt2 = 0;
		//If they pick premade which package do they want - pie
		int optPre = 0;
		//Yes or no variable - pie
		String yN = "";
		//How much are they paying - pie
		payment = 00.00;
		
		System.out.println("You have choosen packages.");
		System.out.println("We have 2 types of packages.");
		
		System.out.println("     1.Premade Packages");
		System.out.println("     2.Custom Packages\n");
		//do they want premade or custom - pie
		opt2 = keyboard.nextInt();
		
		if (opt2 == 1) {
			
			System.out.println("Thank you for choosing 1.Premade Packages.");
			System.out.println();
			//Give them our premade packages - pie
			System.out.println("We have some prepackaged bundles available for purchase.");
			System.out.println();
			System.out.println("     1.The TriForce Bundle. [Hulu, Hbo Max, and Netflix] @ --> $34.50 per month");
			System.out.println("     2.The Helicopter Bundle.[Hbo Max, Netflix, Disney+, and Amazon Prime Video] @ --> $38.00 per month");
			System.out.println("     3.The Golden Sun Bundle.[Hulu, Hbo Max, Netflix, AMC+, and Youtube Premium] @ --> $51.60 per month");
			System.out.println("     4.The Pie Bundle.[Hulu, Hbo Max, Netflix, Disney+, Spotify, and Amazon Prime Video] @ --> $56.40 per month");
			System.out.println("     5.The Solar Bundle. This includes all 8 of the Streaming Services we offer @ -->00 $68.50 per month");
			System.out.println();
			System.out.println("Please pick one. Please note if you choose one it will replace your current plan.\n");
			optPre = keyboard.nextInt();
			//get the users preferred bundle - pie
				
			if 	(optPre == 1) {
				
				//They choose this bundle - pie
				System.out.println("You have The TriForce Bundle.");
				System.out.println("This package cost $34.50 per month.");
				System.out.println("Compared to the $43.50 you would pay without us.");
				System.out.println("Are you sure?");
				System.out.println("Please type Y for yes and N for no.\n");
				yN = keyboard.next();
				
				//Make their input uppercase and check if its is Y - pie
				if(yN.toUpperCase().equals("Y")) {
					
					//Get the package they want and tell them how much it cost - pie
					pack = "The TriForce Bundle";
					payment = 34.50;
					System.out.println("And how will you pay?");
					PaymentStyle();
					
				}else {
					
					//They didnt want this package - pie
					System.out.println("You have rejected this package.");
					pickPackages();
					
				}
				
				
			}
			else if (optPre == 2) {
				
				//They choose this bundle - pie
				System.out.println("You have The Helicopter Bundle.");
				System.out.println("This package cost $38.00 per month.");
				System.out.println("Compared to the $47.50 you would pay without us.");
				System.out.println("Are you sure?");
				System.out.println("Please type Y for yes and N for no.\n");
				yN = keyboard.next();
				
				//Make their input uppercase and check if its is Y - pie
				if(yN.toUpperCase().equals("Y")) {
					
					//Get the package they want and tell them how much it cost - pie
					pack = "The Helicopter Bundle";
					payment = 38.00;
					System.out.println("And how will you pay?");
					PaymentStyle();
					
				}else {
					
					//They didnt want this package - pie
					System.out.println("You have rejected this package.");
					pickPackages();
					
				}
				
			}
			else if (optPre == 3) {
				
				//They choose this bundle - pie
				System.out.println("You have The Golden Sun Bundle.");
				System.out.println("This package cost $51.60 per month.");
				System.out.println("Compared to the $64.50 you would pay without us.");
				System.out.println("Are you sure?");
				System.out.println("Please type Y for yes and N for no.\n");
				yN = keyboard.next();
				
				//Make their input uppercase and check if its is Y - pie
				if(yN.toUpperCase().equals("Y")) {
					
					//Get the package they want and tell them how much it cost - pie
					pack = "The Golden Sun Bundle";
					payment = 51.60;
					System.out.println("And how will you pay?");
					PaymentStyle();
					
				}else {
					
					//They didnt want this package - pie
					System.out.println("You have rejected this package.");
					pickPackages();
					
				}
				
			}
			else if (optPre == 4) {
				
				//They choose this bundle - pie
				System.out.println("You have The Pie Bundle.");
				System.out.println("This package cost $56.40 per month.");
				System.out.println("Compared to the $70.50 you would pay without us.");
				System.out.println("Are you sure?");
				System.out.println("Please type Y for yes and N for no.\n");
				yN = keyboard.next();
				
				//Make their input uppercase and check if its is Y - pie
				if(yN.toUpperCase().equals("Y")) {
					
					//Get the package they want and tell them how much it cost - pie
					pack = "The Pie Bundle";
					payment = 56.40;
					System.out.println("And how will you pay?");
					PaymentStyle();
					
				}else {
					
					//They didnt want this package - pie
					System.out.println("You have rejected this package.");
					pickPackages();
					
				}
				
			}
			else if (optPre == 5) {
				
				//They choose this bundle - pie
				System.out.println("You have The Solar Bundle.");
				System.out.println("This package cost $68.50 per month.");
				System.out.println("Compared to the $91.50 you would pay without us.");
				System.out.println("Are you sure?");
				System.out.println("Please type Y for yes and N for no.\n");
				yN = keyboard.next();
				
				//Make their input uppercase and check if its is Y - pie
				if(yN.toUpperCase().equals("Y")) {
					
					//Get the package they want and tell them how much it cost - pie
					pack = "The Solar Bundle";
					payment = 68.50;
					System.out.println("And how will you pay?");
					PaymentStyle();
					
				}else {
					
					//They didnt want this package - pie
					System.out.println("You have rejected this package.");
					pickPackages();
					
				}
				
			}else {
				
				//If they give a invalid input, tell them and start again - pie
				System.out.println("Im sorry, that was a incorrect option.");
				pickPackages();
				
			}
			
			
		}
		else if (opt2 == 2) {
			
			System.out.println("Thank you for choosing 2.Custom Packages.");
			CustomPackages();
			
			
		}else {
			
			//If they give a invalid input, tell them and start again - pie
			System.out.println("Im sorry, that was a incorrect option.");
			System.out.println("Please close the program and try again.");
			keyboard.close();
			
		}
		
	}

/**********************************End Of Packages*****************************************/


/**********************************CustomPackages
 * @throws IOException *****************************************/
	
	void CustomPackages() throws IOException {
		
		System.out.println("This is our Custom Packages.");
		System.out.println("   We have many options.");
		
		//Make a Reader for us to read for the User  - pie
		BufferedReader read = new BufferedReader(new FileReader("Services.dat"));
        try (//Scan the Reader  - pie
		Scanner serviceReader = new Scanner(read)) {
			//Make variables for us to tell when the line ends  - pie
			String eachLine = "";
			// Make a String Tokenizer - pie
			StringTokenizer st;
			//Make Tester Variables so we can read it nicely  - pie
			int tID;
			String tSName,tDesc;
			float tPrice;
			count = 0;
			String yN;
			
			
			while (serviceReader.hasNextLine()) { // Test for the eof (end of file)  - pie
				
				//Read Each Line for us to understand - pie
				eachLine = serviceReader.nextLine();
				//Realize where the token ends - pie
				st = new StringTokenizer(eachLine, ",");
				//Make a while loop for the tokens so we can read each variables - pie
				while (st.hasMoreTokens()) {
					// take each variable and allow us to read them - pie
					tID = Integer.parseInt(st.nextToken());
					tSName = st.nextToken();
					tPrice = Float.parseFloat(st.nextToken());
					tDesc = st.nextToken();
					
					//Prints out the list - pie
					System.out.println(tID + ". " + tSName + ": $" + tPrice);
					//Adds the service to a table - pie
					serviceList += tID + ". " + tSName + ": $" + tPrice + "\n";
					
				}
				
			}
			
			System.out.println("We have many options.\nWhich would you like to add to your Custom Package?");
			System.out.println("Type 11 to Quit");
			int i = 1;
			while (i < 9) {
				
				int cuInput = keyboard.nextInt();
				
				if(cuInput == 1) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 1. Hulu.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 12.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 2) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 2. Hbo Max.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 14.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 3) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 3. Netflix.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 15.50;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 4) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 4. AMC+.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 8.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 5) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 5. Disney+.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 7.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 6) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 6. Spotify. ");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 9.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 7) {
					
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 7. Youtube Premium.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 11.99;
						
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 8) {
					//Tells them which one they picked - pie
					System.out.println("You have Choosen 8. Amazon Prime Video.");
					System.out.println("Do you accept this?");
					//Makes sure the User wants this Service - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						//We tell the user it has been added - pie
						System.out.println("We have added it to your package");
						//We count how much services they have - pie
						count = count + 1;
						//We add to the subtotal - pie
						subtotal = subtotal + 8.99;					
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
						//The User didn't want this Service - pie
						System.out.println("Okay we will not add this to your package.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					else {
						
						//The User didn't want this Service - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
					
					//If the user picks everything - pie
					if (count == 8 ) {
						
						System.out.println("You have selected all of our Services.");
						
					}
					else {
						
						//Prints the Service Table
						System.out.println(serviceList);
						System.out.println("Please pick a Services.");
						//continues the while loop
						
					}
					
				}
				else if (cuInput == 11) {
					
					System.out.println("Would you like to end the Custom Package?");
					//Makes sure the User wants to end the package - pie
					System.out.println("Please type Y for yes and N for no.");
					yN = keyboard.next();
					
					
					//Make their input uppercase and check if its is Y - pie
					if(yN.toUpperCase().equals("Y")) {
						
						i = i+ 11;
						System.out.println("You have choosen to end this package.");
						
					}
					else if(yN.toUpperCase().equals("N")) {
						
					}
					else {
						
						//The User didn't respond properly - pie
						System.out.println("This is not a correct response.");
						//Makes sure the count isnt messed up - pie
						i = i - 1;
						
					}
					
				}
				else {
					
					//The User didn't pick the right option - pie
					System.out.println("This is not a correct response.");
					//Makes sure the count isnt messed up - pie
					i = i - 1;
					
				}
				
				
				i++;
			
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		System.out.println("You have choosen " + count + " items for your custom package.");
		System.out.println("Please wait while we calculate your discount.");
		System.out.println(".\n..\n...\n..\n.\n..\n...\n..\n.");
		discounts();
		
		
		
	}


/**********************************End Of CustomPackages*****************************************/
	
/**********************************PaymentStyle
 * @throws IOException *****************************************/
	
	void PaymentStyle() throws IOException {
		
		//Variables - pie
		//Figure out if they want PayPal or Credit Cards - pie
		int opt3 = 0;
		//Yes or No - pie
		String yN = "";
		
		//Tell the User how much they will owe - pie
		System.out.println("Your payment due is: $" + payment);
		System.out.println("How would you like to pay?");
		
		//Ask the User how they will pay - pie
		System.out.println("     1.Credit Card");
		System.out.println("     2.Paypal");
		opt3 = keyboard.nextInt();
		
		if (opt3 == 1) {
			
			//They choose Credit Card, now we confirm t it - pie
			System.out.println("So you would like to use your Credit Card on file?");
			System.out.println("Please type Y for yes and N for no.");
			//get a Yes or No - pie
			yN = keyboard.next();
			
			//Make their input uppercase and check if its is Y - pie
			if(yN.toUpperCase().equals("Y")) {
				
				System.out.println("You have paid with your Credit Card.");
				System.out.println("Your receipt will be printed shortly.");
				System.out.println("You should recieve a email receipt as well.");
				//Tells the user they have paid and set the PaymentType to Credit Card - pie
				paymentType = "Credit Card";
				//Send to the Receipt 
				ReceiptFile();
				
			}else {
				
				//The User didnt want to use this payment Method - pie
				System.out.println("You have rejected this payment.");
				PaymentStyle();
				
			}
			
		}
		else if (opt3 == 2) {
			
			System.out.println("So you would like to use your PayPal on file?");
			System.out.println("Please type Y for yes and N for no.");
			yN = keyboard.next();
			
			//Make their input uppercase and check if its is Y - pie
			if(yN.toUpperCase().equals("Y")) {
				
				System.out.println("You have paid with your PayPal.");
				System.out.println("You should recieve a email from PayPal shortly.");
				System.out.println("Your receipt will be printed shortly.");
				//Tells the user they have paid and set the PaymentType to PayPal - pie
				paymentType = "PayPal";
				//Send to the Receipt
				ReceiptFile();
				
			}else {
				
				//The User didnt want to use this payment Method - pie
				System.out.println("You have rejected this payment.");
				PaymentStyle();
				
			}
			
		}
		else {
			
			//If they give a invalid input, tell them and start again - pie
			System.out.println("This is an incorrect choice.");
			PaymentStyle();
			
		}
		
	}


/**********************************End Of PaymentStyle*****************************************/


/**********************************Discounts
 * @throws IOException *****************************************/

void discounts() throws IOException {
	
	double dis = 0.0;
	
	//In process - pie
	System.out.println("This is still in development.\n\n");
	System.out.println("Your Subtotal is " + subtotal);
	if (count <= 3 ) {
		
		dis = subtotal * 0.15;
		total = (Math.round(subtotal * 100.0) / 100.0) - dis;
		payment = Math.round(total * 100.0) / 100.0;
		System.out.println(payment);
		
		
	}
	else if ((4 <= count) & (count <=6)) {
		
		dis = subtotal * 0.20;
		total = (Math.round(subtotal * 100.0) / 100.0) - dis;
		payment = Math.round(total * 100.0) / 100.0;
		System.out.println(payment);
		
	}
	else if (count >= 7) {
		
		dis = subtotal * 0.25;
		total = (Math.round(subtotal * 100.0) / 100.0) - dis;
		payment = Math.round(total * 100.0) / 100.0;
		System.out.println(payment);
		
	}
	else {
		
		System.out.println("How are you even here?");
		
	}
	pack = "Custom Package";
	PaymentStyle();
	
}
	
/**********************************EndOfDiscounts***********************************/
	
/**********************************ReadReceipts
 * @throws IOException *****************************************/
	
	void readReceipts() throws IOException{
		
		// MAke a Yes or No String - Pie
		String yN;
		
		//Complete - pie
		//System.out.println("This is still in development.");
		//Makes sure the user is correct - pie
		System.out.println("Are you " + fName + ". With the Account ID of " + cId + "? \n \n");
		
		System.out.println("Please type Y for yes and N for no.");
		//get a Yes or No - pie
		yN = keyboard.next();
		
		//Make their input uppercase and check if its is Y - pie
		if(yN.toUpperCase().equals("Y")) {
			
			//Make a Reader for us to read for the User  - pie
			BufferedReader read = new BufferedReader(new FileReader("receipts.dat"));
	        try (//Scan the Reader  - pie
			Scanner receiptReader = new Scanner(read)) {
				//Make variables for us to tell when the line ends  - pie
				String eachLine = "";
				// Make a String Tokenizer - pie
				StringTokenizer st;
				//Make Tester Variables so we can read it nicely  - pie
				int testID;
				String tFName, tEmail, tRDate, tPack, tPayType;
				double tPay;
				
				while (receiptReader.hasNextLine()) { // Test for the eof (end of file)  - pie
					//Read Each Line for us to understand - pie
					eachLine = receiptReader.nextLine();
					//Realize where the token ends - pie
					st = new StringTokenizer(eachLine, ",");
					//Make a while loop for the tokens so we can read each variables - pie
					while (st.hasMoreTokens()) {
						// take each variable and allow us to read them - pie
						testID = Integer.parseInt(st.nextToken());
						tFName = st.nextToken();
						tEmail = st.nextToken();
						tRDate = st.nextToken();
						tPack = st.nextToken();
						tPay = Double.parseDouble(st.nextToken());
						tPayType = st.nextToken();
						
						//Test if the User owns this receipt - pie
						if (testID == cId) {
							
							//If they own this receipt print it - pie					
							System.out.println("Account Number: " + testID + "   \n" + 
									"Account Holder Name: " + tFName + ", Purchase Date: " + tRDate +  "   \n" + "   \n" + "\n" + "You have picked " + tPack + ", It cost " + tPay +"   \n" + "You paid with your " + tPayType + "   \n");
							
						}
						
						//If they dont ignore it - pie
						else {
							
							
						}
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			//This is the wrong user - pie
			System.out.println("You should log out.");
			ChooseOption();
			
		}
		
		//Ask Them if they want to go back if not kill the program - pie
		System.out.println("Would you like to go back?");
		System.out.println("Please type Y for yes and N for no.");
		//get a Yes or No - pie
		yN = keyboard.next();
		
		//Make their input uppercase and check if its is Y - pie
		if(yN.toUpperCase().equals("Y")) {
			
			ChooseOption();
			
		}else if(yN.toUpperCase().equals("N")){
			
			//The User wanted to quit - pie
			System.out.println("Thank you. \n "
					+"Good Bye! ( ^o^)/");
			System.exit(100);
			
		}
		else {
			
			//If they give a invalid input, tell them and start again - pie
			System.out.println("Im sorry, that was a incorrect option.\n "+"Good Bye! ( ^o^)/");
			System.exit(100);
			
		}
		
	}
	
/**********************************EndOfReadReceipts***********************************/
	
/***********************************CaptureDate&Time********************************/

	String getSystemDate()
	{
	
		String pattern = "MM-dd-yyyy hh:mm:ss aa";
		Calendar calendar = Calendar.getInstance();
		String purchaseTime = new SimpleDateFormat(pattern).format(calendar.getTime());
		System.out.println(purchaseTime);
		return purchaseTime;
		
		
	}
	
/***********************************EndCaptureDate&Time*****************************/

/**********************************ReceiptFile
 * @throws IOException *****************************************/
	
	void ReceiptFile() throws IOException {
		
		//add review to the list
		reviewdate = getSystemDate();
		//(int custID, String fullName, String email, String recDate, String pack, String payment, String paymentType);
		Receipts pie = new Receipts(cId, fName, cEmail,
				reviewdate, pack, payment, paymentType );
		
		System.out.println("Id: " + cId + " Name: " + fName + " Email: " + cEmail + "\n" + 
		"Date: " + reviewdate + " Pack: " + pack + "\n" +
		"Payment: " + payment + " Type: " + paymentType );
		
		
		
		receipt.add(pie);
		
		
		writeReceiptFile();
		
	}
	
/**********************************EndOfReceiptFile***********************************/

/*******************************WriteReceiptFile
 * @throws IOException *************************/
	
	void writeReceiptFile() throws IOException 
	{
		
		//Working Variables
		FileWriter receiptFile = new FileWriter("receipts.dat", true);
		BufferedWriter bw = new BufferedWriter(receiptFile);
		    // pie
		
		try {
			
			bw.write(cId + ", " + fName + ", " + cEmail + ", " + reviewdate + ", " + 
			pack + ", " + payment + ", " + paymentType  + " \n");
			
			System.out.println("Account Number: " + cId + "   \n" + 
					"Account Holder Name: " + fName + ", Purchase Date: " + reviewdate +  "   \n" + "   \n" + "   \n" +
					"You have picked " + pack + ", It cost" + payment +"   \n" + 
					"You paid with your " + paymentType + "   \n");
			
			bw.close();
			
		} catch(IOException e){
			
			System.out.println("An error occurred.");
		     e.printStackTrace();
			
		}
		
		/*//Create the variable for the file
		for (int i = 0; i < receipt.size(); i ++) {
			
			//Receipts r = (Receipts) receipt.get(i); // Cast the object to a review
			bw.write("Account Number: " + cId + "   \n" + 
			"Account Holder Name: " + fName + ", Purchase Date: " + reviewdate +  "   \n" + "   \n" + "   \n" +
			"You have picked " + pack + ", It cost" + payment +"   \n" + 
			"You paid with your " + paymentType + "   \n");
			
			System.out.println("Account Number: " + cId + "   \n" + 
					"Account Holder Name: " + fName + ", Purchase Date: " + reviewdate +  "   \n" + "   \n" + "   \n" +
					"You have picked " + pack + ", It cost" + payment +"   \n" + 
					"You paid with your " + paymentType + "   \n");
			
		}
		
		System.out.println("Review File Written");
		
		bw.close();*/
		
	}
	
/************************************EndReceiptFile**********************************/
	
/*****************************************EndOfSale***************************************/
}
