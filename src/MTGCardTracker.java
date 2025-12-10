import java.util.Scanner;
import java.util.ArrayList;

/**
 * MTGCardTracker - Main Application Class
 * 
 * This is the main driver class for the Magic: The Gathering Card Collection Tracker.
 * It provides a command-line interface for managing a card collection with full
 * CRUD (Create, Read, Update, Delete) operations.
 * 
 * Features:
 * - Add new cards to collection
 * - Delete cards from collection
 * - Modify existing card details
 * - Display cards in various formats
 * - Search and filter cards
 * - View collection statistics
 * - Import/Export CSV files (prepared for future GUI)
 * 
 * @author Card Collection Tracker
 * @version 1.0
 */
public class MTGCardTracker {
    
    // Static collection instance
    private static CardCollection collection;
    private static Scanner scanner;
    
    /**
     * Main method - Entry point of the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        collection = new CardCollection("My MTG Collection");
        
        // Add some sample cards for testing (optional - can be removed)
        addSampleCards();
        
        // Display welcome message
        displayWelcome();
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = InputValidator.getValidInteger(scanner, 0, 10);
            
            switch (choice) {
                case 1:
                    addNewCard();
                    break;
                case 2:
                    deleteCard();
                    break;
                case 3:
                    modifyCard();
                    break;
                case 4:
                    displayCards();
                    break;
                case 5:
                    searchCards();
                    break;
                case 6:
                    filterCards();
                    break;
                case 7:
                    viewStatistics();
                    break;
                case 8:
                    exportToCSV();
                    break;
                case 9:
                    importFromCSV();
                    break;
                case 10:
                    manageCollection();
                    break;
                case 0:
                    running = false;
                    displayGoodbye();
                    break;
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the welcome message
     */
    private static void displayWelcome() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("  WELCOME TO MTG CARD COLLECTION TRACKER");
        System.out.println("  Track and manage your Magic: The Gathering card collection!");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Displays the main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("MAIN MENU - " + collection.getCollectionName());
        System.out.println("Total Cards: " + collection.getSize() + " | Total Value: $" + 
                         String.format("%.2f", collection.getTotalValue()));
        System.out.println("=".repeat(80));
        System.out.println("1.  Add New Card");
        System.out.println("2.  Delete Card");
        System.out.println("3.  Modify Card");
        System.out.println("4.  Display Cards");
        System.out.println("5.  Search Cards");
        System.out.println("6.  Filter Cards");
        System.out.println("7.  View Statistics");
        System.out.println("8.  Export to CSV");
        System.out.println("9.  Import from CSV");
        System.out.println("10. Manage Collection Settings");
        System.out.println("0.  Exit");
        System.out.println("=".repeat(80));
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Adds a new card to the collection
     * Prompts user for all card details with validation
     */
    private static void addNewCard() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ADD NEW MTG CARD");
        System.out.println("=".repeat(80));
        
        try {
            // Get card name
            System.out.print("Enter card name: ");
            String name = InputValidator.getNonEmptyString(scanner, "Card name");
            
            // Get rarity
            System.out.println("\nValid rarities: " + String.join(", ", MTGCard.getValidRarities()));
            System.out.print("Enter rarity: ");
            String rarity = InputValidator.getValidChoice(scanner, MTGCard.getValidRarities(), "rarity");
            
            // Get condition
            System.out.println("\nValid conditions: " + String.join(", ", MTGCard.getValidConditions()));
            System.out.print("Enter condition: ");
            String condition = InputValidator.getValidChoice(scanner, MTGCard.getValidConditions(), "condition");
            
            // Get value
            System.out.print("\nEnter card value (in dollars): $");
            double value = InputValidator.getValidDouble(scanner);
            
            // Get edition
            System.out.print("Enter edition/set name: ");
            String edition = InputValidator.getNonEmptyString(scanner, "Edition");
            
            // Get card type
            System.out.print("Enter card type (e.g., Creature, Instant, Sorcery, Enchantment): ");
            String cardType = InputValidator.getNonEmptyString(scanner, "Card type");
            
            // Get color
            System.out.println("\nValid colors: " + String.join(", ", MTGCard.getValidColors()));
            System.out.print("Enter color: ");
            String color = InputValidator.getValidChoice(scanner, MTGCard.getValidColors(), "color");
            
            // Get mana cost
            System.out.print("Enter converted mana cost (0-20): ");
            int manaCost = InputValidator.getValidInteger(scanner, 0, 20);
            
            // Get subtype
            System.out.print("Enter card subtype (e.g., Human Wizard, Dragon, etc.): ");
            String subtype = InputValidator.getNonEmptyString(scanner, "Subtype");
            
            // Get foil status
            boolean isFoil = InputValidator.getYesNo(scanner, "\nIs this card foil?");
            
            // Create and add the card
            MTGCard card = new MTGCard(name, rarity, condition, value, edition, cardType, 
                                      color, manaCost, subtype, isFoil);
            
            collection.addCard(card);
            
            // Display the newly added card
            System.out.println("\n--- New Card Added ---");
            card.displayCard();
            
        } catch (IllegalArgumentException e) {
            System.out.println("\nError creating card: " + e.getMessage());
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Deletes a card from the collection
     */
    private static void deleteCard() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to delete.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DELETE CARD");
        System.out.println("=".repeat(80));
        
        collection.displayCardList();
        
        System.out.println("\n1. Delete by index");
        System.out.println("2. Delete by name");
        System.out.println("0. Cancel");
        System.out.print("Enter choice: ");
        
        int choice = InputValidator.getValidInteger(scanner, 0, 2);
        
        switch (choice) {
            case 1:
                System.out.print("Enter card index to delete: ");
                int index = InputValidator.getValidInteger(scanner, 0, collection.getSize() - 1);
                
                MTGCard cardToDelete = collection.getCard(index);
                if (cardToDelete != null) {
                    System.out.println("\nYou are about to delete:");
                    cardToDelete.displayCard();
                    
                    if (InputValidator.getYesNo(scanner, "\nAre you sure you want to delete this card?")) {
                        collection.deleteCard(index);
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                }
                break;
            case 2:
                System.out.print("Enter card name to delete: ");
                String name = scanner.nextLine();
                collection.deleteCardByName(name);
                break;
            case 0:
                System.out.println("Delete operation cancelled.");
                break;
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Modifies an existing card in the collection
     */
    private static void modifyCard() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to modify.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("MODIFY CARD");
        System.out.println("=".repeat(80));
        
        collection.displayCardList();
        
        System.out.print("\nEnter the index of the card to modify (or -1 to cancel): ");
        int index = InputValidator.getValidInteger(scanner, -1, collection.getSize() - 1);
        
        if (index == -1) {
            System.out.println("Modify operation cancelled.");
            return;
        }
        
        collection.modifyCard(index, scanner);
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Displays cards in the collection
     */
    private static void displayCards() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to display.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DISPLAY CARDS");
        System.out.println("=".repeat(80));
        System.out.println("1. Display all cards (detailed)");
        System.out.println("2. Display card list (compact)");
        System.out.println("0. Cancel");
        System.out.print("Enter choice: ");
        
        int choice = InputValidator.getValidInteger(scanner, 0, 2);
        
        switch (choice) {
            case 1:
                collection.displayAllCards();
                break;
            case 2:
                collection.displayCardList();
                break;
            case 0:
                return;
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Searches for cards by name
     */
    private static void searchCards() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to search.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SEARCH CARDS");
        System.out.println("=".repeat(80));
        System.out.print("Enter search term (card name): ");
        String searchTerm = scanner.nextLine();
        
        ArrayList<MTGCard> results = collection.searchByName(searchTerm);
        
        if (results.isEmpty()) {
            System.out.println("\nNo cards found matching '" + searchTerm + "'");
        } else {
            System.out.println("\nFound " + results.size() + " card(s) matching '" + searchTerm + "':");
            for (MTGCard card : results) {
                card.displayCard();
            }
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Filters cards by various criteria
     */
    private static void filterCards() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to filter.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("FILTER CARDS");
        System.out.println("=".repeat(80));
        System.out.println("1. Filter by rarity");
        System.out.println("2. Filter by color");
        System.out.println("0. Cancel");
        System.out.print("Enter choice: ");
        
        int choice = InputValidator.getValidInteger(scanner, 0, 2);
        ArrayList<MTGCard> results = new ArrayList<>();
        String filterType = "";
        String filterValue = "";
        
        switch (choice) {
            case 1:
                System.out.println("\nValid rarities: " + String.join(", ", MTGCard.getValidRarities()));
                System.out.print("Enter rarity to filter by: ");
                filterValue = InputValidator.getValidChoice(scanner, MTGCard.getValidRarities(), "rarity");
                results = collection.filterByRarity(filterValue);
                filterType = "rarity '" + filterValue + "'";
                break;
            case 2:
                System.out.println("\nValid colors: " + String.join(", ", MTGCard.getValidColors()));
                System.out.print("Enter color to filter by: ");
                filterValue = InputValidator.getValidChoice(scanner, MTGCard.getValidColors(), "color");
                results = collection.filterByColor(filterValue);
                filterType = "color '" + filterValue + "'";
                break;
            case 0:
                return;
        }
        
        if (results.isEmpty()) {
            System.out.println("\nNo cards found with " + filterType);
        } else {
            System.out.println("\nFound " + results.size() + " card(s) with " + filterType + ":");
            for (MTGCard card : results) {
                card.displayCard();
            }
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Displays collection statistics
     */
    private static void viewStatistics() {
        collection.displayStatistics();
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Exports collection to CSV file
     */
    private static void exportToCSV() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. Nothing to export.");
            InputValidator.pressEnterToContinue(scanner);
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EXPORT TO CSV");
        System.out.println("=".repeat(80));
        System.out.print("Enter filename (e.g., my_collection.csv): ");
        String filename = InputValidator.getNonEmptyString(scanner, "Filename");
        
        if (!filename.toLowerCase().endsWith(".csv")) {
            filename += ".csv";
        }
        
        collection.exportToCSV(filename);
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Imports collection from CSV file
     */
    private static void importFromCSV() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("IMPORT FROM CSV");
        System.out.println("=".repeat(80));
        System.out.print("Enter filename to import: ");
        String filename = InputValidator.getNonEmptyString(scanner, "Filename");
        
        if (!filename.toLowerCase().endsWith(".csv")) {
            filename += ".csv";
        }
        
        int count = collection.importFromCSV(filename);
        System.out.println("Successfully imported " + count + " cards.");
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Manages collection settings
     */
    private static void manageCollection() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("MANAGE COLLECTION");
        System.out.println("=".repeat(80));
        System.out.println("Current collection name: " + collection.getCollectionName());
        System.out.print("\nEnter new collection name (or press Enter to keep current): ");
        
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            collection.setCollectionName(newName);
            System.out.println("Collection name updated to: " + collection.getCollectionName());
        } else {
            System.out.println("Collection name unchanged.");
        }
        
        InputValidator.pressEnterToContinue(scanner);
    }
    
    /**
     * Displays goodbye message
     */
    private static void displayGoodbye() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("  Thank you for using MTG Card Collection Tracker!");
        System.out.println("  Your collection has been saved. See you next time!");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Adds sample cards to the collection for testing purposes
     * This method can be removed or commented out in production
     */
    private static void addSampleCards() {
        // Sample Card 1 - Powerful creature
        MTGCard card1 = new MTGCard(
            "Black Lotus",
            "Rare",
            "Near Mint",
            50000.00,
            "Alpha",
            "Artifact",
            "Colorless",
            0,
            "Artifact",
            false
        );
        
        // Sample Card 2 - Common card
        MTGCard card2 = new MTGCard(
            "Lightning Bolt",
            "Common",
            "Excellent",
            5.50,
            "Unlimited",
            "Instant",
            "Red",
            1,
            "Instant",
            false
        );
        
        // Sample Card 3 - Mythic Rare foil
        MTGCard card3 = new MTGCard(
            "Nicol Bolas, Dragon-God",
            "Mythic Rare",
            "Mint",
            35.99,
            "War of the Spark",
            "Planeswalker",
            "Multicolor",
            4,
            "Elder Dragon Planeswalker",
            true
        );
        
        collection.addCard(card1);
        collection.addCard(card2);
        collection.addCard(card3);
    }
}
