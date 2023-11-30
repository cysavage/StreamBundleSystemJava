package pkgStreamBundle;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SaleSystem {

	
	public static void main(String[] args) throws IOException {
		Sale serSol = new Sale();	
		boolean isValidLogin = false;
		
		try {
			
			serSol.loadCustomers();
			serSol.loadServies();
			isValidLogin = serSol.login();
			
			if (!isValidLogin)
			{ System.out.print("Number of login attempts exceeded, program terminated.");
			System.exit(001);
			}
		} 
		
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage()); // Sends out the default system error message
			System.out.println("File not FOund, Program terminated.");
			System.exit(100);
			}
		
		serSol.ChooseOption();
		
		try {
			serSol.writeReceiptFile();
		} catch (IOException fnf) {
			System.out.println("Unable to write a review file");
		}
		
	}
			
			
}
