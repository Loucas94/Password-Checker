/** \file   PasswordMain.java
* \brief     Main Class that implements the menu.
* \author    Loucas Mouttotou
* \date      2024-2024
* \bug       No bugs so far
* \copyright University of Nicosia.
*/

//Libraries.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Main class.
public class PasswordMain {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     String commonPass = "topPasswords.txt";
     PasswordCheck validator = new PasswordCheck();
     TopPasswords commonPasswords = new TopPasswords(commonPass);
     randomPassword randomPasswordGenerator = new randomPassword(commonPasswords);
     
     //Menu.
     while (true) {
         System.out.println("Choose an option:");
         System.out.println("1) Enter password to check complexity");
         System.out.println("2) Generate a random password");
         System.out.println("3) Genarate a report on given passwords.");
         System.out.println("4) Generate a list of weak passwords and save to a file");
         System.out.println("5) Exit");
         System.out.print("Enter your choice: ");

         int choice;
         
         //Makes sure the user gives a valid input choice and allows re-entering.
         while (true) {
             try {
                 choice = scanner.nextInt();
                 scanner.nextLine();
                 break;
             } catch (java.util.InputMismatchException e) {
                 System.out.println("Invalid input.");
                 scanner.nextLine();
             }
         }
         
         //Case switch that implements each choice from user.
         switch (choice) {
             case 1:
                 System.out.print("Enter a password to check its complexity: ");
                 String password = scanner.nextLine();
                 try {
                     if (commonPasswords.isCommon(password)) {
                         System.out.println("Your password is too common.Please choose a stronger password.");
                     } else {
                         String result = validator.checkPasswordStrength(password);
                         System.out.println(result);
                     }
                 } catch (IOException e) {
                     System.out.println("Error reading the password file: " + e.getMessage());
                 }
                 break;
             case 2:
                 System.out.print("Enter the desired password length (minimum 12): ");
                 int len;
                 while (true) {
                     try {
                         len = scanner.nextInt();
                         scanner.nextLine();
                         break;
                     } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input. Please enter a valid number for the password length.");
                         scanner.nextLine();
                     }
                 }
                 String randomPassword = randomPasswordGenerator.generatePassword(len);
                 System.out.println("Generated Password: " + randomPassword);
                 break;
             case 3:
            	 String filePath = "passwords.txt"; // file containing passwords
                 try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
                      BufferedWriter writer = new BufferedWriter(new FileWriter("password_report.txt"))) {

                     String line;
                     while ((line = reader.readLine()) != null) {
                         String report;
                         if (commonPasswords.isCommon(line)) {
                             report = "Password: " + line + " - Too common\n";
                         } else {
                             report = "Password: " + line + "\n" + validator.checkPasswordStrength(line) + "\n";
                         }
                         writer.write(report);
                     }
                     System.out.println("Password report generated successfully in 'password_report.txt'.");
                 } catch (IOException e) {
                     System.out.println("Error processing the file: " + e.getMessage());
                 }
                 break;
             case 4:
            	 System.out.print("Enter the desired number of weak passwords to generate: ");
                 int numWeakPasswords;
                 while (true) {
                     try {
                         numWeakPasswords = scanner.nextInt();
                         scanner.nextLine();
                         break;
                     } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input. Please enter a valid number.");
                         scanner.nextLine();
                     }
                 }

                 try (BufferedWriter writer = new BufferedWriter(new FileWriter("weak_passwords.txt"))) {
                     for (int i = 0; i < numWeakPasswords; i++) {
                         String weakPassword = randomPasswordGenerator.generatePassword(12).toLowerCase(); //Force password to be lowercase, hence weak.
                         writer.write(weakPassword + "\n");
                     }
                     System.out.println("Weak passwords generated successfully in 'weak_passwords.txt'.");
                 } catch (IOException e) {
                     System.out.println("Error writing weak passwords to file: " + e.getMessage());
                 }
                 break;
             case 5:
                 System.out.println("Goodbye...");
                 scanner.close();
                 return;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }
}