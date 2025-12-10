import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * CardCollection - Collection Manager Class
 * 
 * This class manages a collection of cards, providing functionality to:
 * - Add new cards
 * - Delete cards
 * - Modify existing cards
 * - Display cards
 * - Search and filter cards
 * - Import/Export to CSV (prepared for future implementation)
 * 
 * @author Card Collection Tracker
 * @version 1.0
 */
public class CardCollection {
    // ArrayList to store the card collection
    private ArrayList<MTGCard> collection;
    private String collectionName;
    
    /**
     * Default Constructor
     * Creates an empty collection with default name
     */
    public CardCollection() {
        this.collection = new ArrayList<>();
        this.collectionName = "My MTG Collection";
    }
    
    /**
     * Parameterized Constructor
     * Creates an empty collection with specified name
     * 
     * @param collectionName The name of the collection
     */
    public CardCollection(String collectionName) {
        this.collection = new ArrayList<>();
        this.collectionName = collectionName;
    }
    
    /**
     * Gets the collection name
     * @return The collection name
     */
    public String getCollectionName() {
        return collectionName;
    }
    
    /**
     * Sets the collection name
     * @param collectionName The new collection name
     */
    public void setCollectionName(String collectionName) {
        if (collectionName != null && !collectionName.trim().isEmpty()) {
            this.collectionName = collectionName.trim();
        }
    }
    
    /**
     * Gets the size of the collection
     * @return Number of cards in the collection
     */
    public int getSize() {
        return collection.size();
    }
    
    /**
     * Checks if the collection is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }
    
    /**
     * Adds a new card to the collection
     * 
     * @param card The MTG card to add
     * @return true if added successfully, false if card is null
     */
    public boolean addCard(MTGCard card) {
        if (card == null) {
            System.out.println("Error: Cannot add null card to collection.");
            return false;
        }
        
        collection.add(card);
        System.out.println("\nCard '" + card.getName() + "' added successfully to collection!");
        return true;
    }
    
    /**
     * Deletes a card from the collection by index
     * 
     * @param index The index of the card to delete (0-based)
     * @return true if deleted successfully, false if index is invalid
     */
    public boolean deleteCard(int index) {
        if (index < 0 || index >= collection.size()) {
            System.out.println("Error: Invalid card index. Must be between 0 and " + (collection.size() - 1));
            return false;
        }
        
        MTGCard removed = collection.remove(index);
        System.out.println("\nCard '" + removed.getName() + "' removed from collection.");
        return true;
    }
    
    /**
     * Deletes a card from the collection by name
     * 
     * @param cardName The name of the card to delete
     * @return true if deleted successfully, false if not found
     */
    public boolean deleteCardByName(String cardName) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getName().equalsIgnoreCase(cardName)) {
                collection.remove(i);
                System.out.println("\nCard '" + cardName + "' removed from collection.");
                return true;
            }
        }
        
        System.out.println("Error: Card '" + cardName + "' not found in collection.");
        return false;
    }
    
    /**
     * Gets a card from the collection by index
     * 
     * @param index The index of the card to retrieve
     * @return The MTG card at the specified index, or null if invalid
     */
    public MTGCard getCard(int index) {
        if (index < 0 || index >= collection.size()) {
            System.out.println("Error: Invalid card index.");
            return null;
        }
        return collection.get(index);
    }
    
    /**
     * Modifies an existing card in the collection
     * Allows updating any attribute of a card
     * 
     * @param index The index of the card to modify
     * @param scanner Scanner for user input
     */
    public void modifyCard(int index, Scanner scanner) {
        if (index < 0 || index >= collection.size()) {
            System.out.println("Error: Invalid card index.");
            return;
        }
        
        MTGCard card = collection.get(index);
        boolean modifying = true;
        
        while (modifying) {
            System.out.println("\n--- Modify Card: " + card.getName() + " ---");
            System.out.println("1. Modify Name");
            System.out.println("2. Modify Rarity");
            System.out.println("3. Modify Condition");
            System.out.println("4. Modify Value");
            System.out.println("5. Modify Edition");
            System.out.println("6. Modify Card Type");
            System.out.println("7. Modify Color");
            System.out.println("8. Modify Mana Cost");
            System.out.println("9. Modify Subtype");
            System.out.println("10. Toggle Foil Status");
            System.out.println("11. View Current Card");
            System.out.println("0. Done Modifying");
            System.out.print("Enter choice: ");
            
            int choice = InputValidator.getValidInteger(scanner, 0, 11);
            
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();
                        card.setName(name);
                        System.out.println("Name updated successfully!");
                        break;
                    case 2:
                        System.out.println("Valid rarities: " + String.join(", ", MTGCard.getValidRarities()));
                        System.out.print("Enter new rarity: ");
                        String rarity = scanner.nextLine();
                        card.setRarity(rarity);
                        System.out.println("Rarity updated successfully!");
                        break;
                    case 3:
                        System.out.println("Valid conditions: " + String.join(", ", MTGCard.getValidConditions()));
                        System.out.print("Enter new condition: ");
                        String condition = scanner.nextLine();
                        card.setCondition(condition);
                        System.out.println("Condition updated successfully!");
                        break;
                    case 4:
                        System.out.print("Enter new value: $");
                        double value = InputValidator.getValidDouble(scanner);
                        card.setValue(value);
                        System.out.println("Value updated successfully!");
                        break;
                    case 5:
                        System.out.print("Enter new edition: ");
                        String edition = scanner.nextLine();
                        card.setEdition(edition);
                        System.out.println("Edition updated successfully!");
                        break;
                    case 6:
                        System.out.print("Enter new card type (e.g., Creature, Instant, Sorcery): ");
                        String cardType = scanner.nextLine();
                        card.setCardType(cardType);
                        System.out.println("Card type updated successfully!");
                        break;
                    case 7:
                        System.out.println("Valid colors: " + String.join(", ", MTGCard.getValidColors()));
                        System.out.print("Enter new color: ");
                        String color = scanner.nextLine();
                        card.setColor(color);
                        System.out.println("Color updated successfully!");
                        break;
                    case 8:
                        System.out.print("Enter new mana cost: ");
                        int manaCost = InputValidator.getValidInteger(scanner, 0, 20);
                        card.setManaCost(manaCost);
                        System.out.println("Mana cost updated successfully!");
                        break;
                    case 9:
                        System.out.print("Enter new subtype: ");
                        String subtype = scanner.nextLine();
                        card.setCardSubtype(subtype);
                        System.out.println("Subtype updated successfully!");
                        break;
                    case 10:
                        card.setIsFoil(!card.isFoil());
                        System.out.println("Foil status is now: " + (card.isFoil() ? "FOIL" : "NON-FOIL"));
                        break;
                    case 11:
                        card.displayCard();
                        break;
                    case 0:
                        modifying = false;
                        System.out.println("Modifications saved!");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Displays all cards in the collection
     */
    public void displayAllCards() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty. No cards to display.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("COLLECTION: " + collectionName);
        System.out.println("Total Cards: " + collection.size());
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < collection.size(); i++) {
            System.out.printf("\n[Card #%d]%n", i);
            collection.get(i).displayCard();
        }
    }
    
    /**
     * Displays a simple list of all cards (compact view)
     */
    public void displayCardList() {
        if (collection.isEmpty()) {
            System.out.println("\nCollection is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(100));
        System.out.println("CARD LIST - " + collectionName);
        System.out.println("=".repeat(100));
        System.out.printf("%-5s %-30s %-20s %-15s %-10s%n", "Index", "Name", "Edition", "Rarity", "Value");
        System.out.println("-".repeat(100));
        
        for (int i = 0; i < collection.size(); i++) {
            MTGCard card = collection.get(i);
            System.out.printf("%-5d %-30s %-20s %-15s $%-9.2f%n", 
                            i, 
                            card.getName(), 
                            card.getEdition(), 
                            card.getRarity(), 
                            card.getValue());
        }
        System.out.println("=".repeat(100));
    }
    
    /**
     * Searches for cards by name (partial match, case-insensitive)
     * 
     * @param searchTerm The term to search for
     * @return ArrayList of matching cards
     */
    public ArrayList<MTGCard> searchByName(String searchTerm) {
        ArrayList<MTGCard> results = new ArrayList<>();
        
        for (MTGCard card : collection) {
            if (card.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(card);
            }
        }
        
        return results;
    }
    
    /**
     * Filters cards by rarity
     * 
     * @param rarity The rarity to filter by
     * @return ArrayList of cards with matching rarity
     */
    public ArrayList<MTGCard> filterByRarity(String rarity) {
        ArrayList<MTGCard> results = new ArrayList<>();
        
        for (MTGCard card : collection) {
            if (card.getRarity().equalsIgnoreCase(rarity)) {
                results.add(card);
            }
        }
        
        return results;
    }
    
    /**
     * Filters cards by color
     * 
     * @param color The color to filter by
     * @return ArrayList of cards with matching color
     */
    public ArrayList<MTGCard> filterByColor(String color) {
        ArrayList<MTGCard> results = new ArrayList<>();
        
        for (MTGCard card : collection) {
            if (card.getColor().equalsIgnoreCase(color)) {
                results.add(card);
            }
        }
        
        return results;
    }
    
    /**
     * Calculates the total value of all cards in the collection
     * 
     * @return Total monetary value
     */
    public double getTotalValue() {
        double total = 0.0;
        for (MTGCard card : collection) {
            total += card.getValue();
        }
        return total;
    }
    
    /**
     * Displays collection statistics
     */
    public void displayStatistics() {
        if (collection.isEmpty()) {
            System.out.println("\nNo statistics available - collection is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COLLECTION STATISTICS - " + collectionName);
        System.out.println("=".repeat(60));
        System.out.printf("Total Cards:        %d%n", collection.size());
        System.out.printf("Total Value:        $%.2f%n", getTotalValue());
        System.out.printf("Average Card Value: $%.2f%n", getTotalValue() / collection.size());
        
        // Count by rarity
        int common = 0, uncommon = 0, rare = 0, mythic = 0, special = 0;
        for (MTGCard card : collection) {
            switch (card.getRarity()) {
                case "Common": common++; break;
                case "Uncommon": uncommon++; break;
                case "Rare": rare++; break;
                case "Mythic Rare": mythic++; break;
                case "Special": special++; break;
            }
        }
        
        System.out.println("\nCards by Rarity:");
        System.out.printf("  Common:      %d%n", common);
        System.out.printf("  Uncommon:    %d%n", uncommon);
        System.out.printf("  Rare:        %d%n", rare);
        System.out.printf("  Mythic Rare: %d%n", mythic);
        System.out.printf("  Special:     %d%n", special);
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exports collection to CSV file (prepared for future GUI implementation)
     * 
     * @param filename The name of the file to export to
     * @return true if successful, false otherwise
     */
    public boolean exportToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write header
            writer.println("Name,Rarity,Condition,Value,Edition,CardType,Color,ManaCost,Subtype,Foil");
            
            // Write each card
            for (MTGCard card : collection) {
                writer.printf("%s,%s,%s,%.2f,%s,%s,%s,%d,%s,%s%n",
                            card.getName(),
                            card.getRarity(),
                            card.getCondition(),
                            card.getValue(),
                            card.getEdition(),
                            card.getCardType(),
                            card.getColor(),
                            card.getManaCost(),
                            card.getCardSubtype(),
                            card.isFoil() ? "Yes" : "No");
            }
            
            System.out.println("\nCollection exported successfully to " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Error exporting to CSV: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Imports collection from CSV file (prepared for future GUI implementation)
     * 
     * @param filename The name of the file to import from
     * @return Number of cards imported
     */
    public int importFromCSV(String filename) {
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 10) {
                    try {
                        MTGCard card = new MTGCard(
                            parts[0],  // name
                            parts[1],  // rarity
                            parts[2],  // condition
                            Double.parseDouble(parts[3]),  // value
                            parts[4],  // edition
                            parts[5],  // cardType
                            parts[6],  // color
                            Integer.parseInt(parts[7]),  // manaCost
                            parts[8],  // subtype
                            parts[9].equalsIgnoreCase("Yes")  // isFoil
                        );
                        
                        collection.add(card);
                        count++;
                    } catch (Exception e) {
                        System.out.println("Error importing card: " + parts[0] + " - " + e.getMessage());
                    }
                }
            }
            
            System.out.println("\nImported " + count + " cards from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        
        return count;
    }
}
