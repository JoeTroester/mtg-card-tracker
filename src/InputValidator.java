import java.util.Scanner;

/**
 * InputValidator - Utility Class
 * 
 * This class provides robust input validation methods to handle edge cases
 * and prevent errors from invalid user input.
 * 
 * @author Card Collection Tracker
 * @version 1.0
 */
public class InputValidator {
    
    /**
     * Gets a valid integer from user input within a specified range
     * Handles invalid input types and out-of-range values
     * 
     * @param scanner Scanner object for input
     * @param min Minimum valid value (inclusive)
     * @param max Maximum valid value (inclusive)
     * @return Valid integer within range
     */
    public static int getValidInteger(Scanner scanner, int min, int max) {
        int value = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                String input = scanner.nextLine().trim();
                
                // Check for empty input
                if (input.isEmpty()) {
                    System.out.print("Error: Input cannot be empty. Please enter a number between " + 
                                   min + " and " + max + ": ");
                    continue;
                }
                
                value = Integer.parseInt(input);
                
                // Check range
                if (value < min || value > max) {
                    System.out.print("Error: Number must be between " + min + " and " + max + ". Try again: ");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("Error: Invalid input. Please enter a whole number between " + 
                               min + " and " + max + ": ");
            }
        }
        
        return value;
    }
    
    /**
     * Gets a valid double (decimal number) from user input
     * Handles invalid input types and negative values
     * 
     * @param scanner Scanner object for input
     * @return Valid positive double value
     */
    public static double getValidDouble(Scanner scanner) {
        double value = -1.0;
        boolean valid = false;
        
        while (!valid) {
            try {
                String input = scanner.nextLine().trim();
                
                // Check for empty input
                if (input.isEmpty()) {
                    System.out.print("Error: Input cannot be empty. Please enter a valid number: ");
                    continue;
                }
                
                value = Double.parseDouble(input);
                
                // Check for negative values
                if (value < 0) {
                    System.out.print("Error: Value cannot be negative. Try again: ");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("Error: Invalid input. Please enter a valid decimal number: ");
            }
        }
        
        return value;
    }
    
    /**
     * Gets a non-empty string from user input
     * Trims whitespace and ensures input is not blank
     * 
     * @param scanner Scanner object for input
     * @param fieldName Name of the field being requested (for error messages)
     * @return Valid non-empty string
     */
    public static String getNonEmptyString(Scanner scanner, String fieldName) {
        String input = "";
        boolean valid = false;
        
        while (!valid) {
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.print("Error: " + fieldName + " cannot be empty. Please enter a value: ");
            } else {
                valid = true;
            }
        }
        
        return input;
    }
    
    /**
     * Gets a valid choice from a list of options
     * Case-insensitive matching
     * 
     * @param scanner Scanner object for input
     * @param validOptions Array of valid string options
     * @param fieldName Name of the field being requested
     * @return Valid option string (with proper capitalization from validOptions)
     */
    public static String getValidChoice(Scanner scanner, String[] validOptions, String fieldName) {
        String input = "";
        boolean valid = false;
        
        while (!valid) {
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.print("Error: " + fieldName + " cannot be empty. Valid options: " + 
                               String.join(", ", validOptions) + "\nTry again: ");
                continue;
            }
            
            // Check if input matches any valid option (case-insensitive)
            for (String option : validOptions) {
                if (option.equalsIgnoreCase(input)) {
                    return option; // Return the properly capitalized version
                }
            }
            
            // If we get here, input was invalid
            System.out.print("Error: Invalid " + fieldName + ". Valid options: " + 
                           String.join(", ", validOptions) + "\nTry again: ");
        }
        
        return input;
    }
    
    /**
     * Gets a yes/no confirmation from user
     * Accepts: y, yes, n, no (case-insensitive)
     * 
     * @param scanner Scanner object for input
     * @param prompt The question to ask the user
     * @return true for yes, false for no
     */
    public static boolean getYesNo(Scanner scanner, String prompt) {
        System.out.print(prompt + " (y/n): ");
        String input = "";
        boolean valid = false;
        
        while (!valid) {
            input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.print("Error: Please enter 'y' for yes or 'n' for no: ");
            }
        }
        
        return false;
    }
    
    /**
     * Pauses execution and waits for user to press Enter
     * Useful for "Press Enter to continue" prompts
     * 
     * @param scanner Scanner object for input
     */
    public static void pressEnterToContinue(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
