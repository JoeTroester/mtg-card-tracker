/**
 * MTGCard - Derived (Sub) Class
 * 
 * This class extends Card and represents Magic: The Gathering cards specifically.
 * It adds MTG-specific attributes and validation.
 * 
 * @author Card Collection Tracker
 * @version 1.0
 */
public class MTGCard extends Card {
    // MTG-specific attributes
    private String color;           // Card color (White, Blue, Black, Red, Green, Colorless, Multicolor)
    private int manaCost;           // Converted mana cost
    private String cardSubtype;     // Creature type, spell type, etc.
    private boolean isFoil;         // Whether the card is foil
    
    // Valid MTG rarities
    private static final String[] VALID_RARITIES = {"Common", "Uncommon", "Rare", "Mythic Rare", "Special"};
    
    // Valid card conditions
    private static final String[] VALID_CONDITIONS = {"Mint", "Near Mint", "Excellent", "Good", "Light Played", 
                                                      "Played", "Poor", "Damaged"};
    
    // Valid MTG colors
    private static final String[] VALID_COLORS = {"White", "Blue", "Black", "Red", "Green", "Colorless", "Multicolor"};
    
    /**
     * Default Constructor
     * Creates an MTG card with default values
     */
    public MTGCard() {
        super();
        this.color = "Colorless";
        this.manaCost = 0;
        this.cardSubtype = "None";
        this.isFoil = false;
    }
    
    /**
     * Parameterized Constructor
     * Creates an MTG card with specified attributes
     * 
     * @param name Card name
     * @param rarity Card rarity
     * @param condition Physical condition
     * @param value Monetary value
     * @param edition Set/Edition name
     * @param cardType Main card type
     * @param color Card color
     * @param manaCost Converted mana cost
     * @param cardSubtype Card subtype
     * @param isFoil Whether card is foil
     */
    public MTGCard(String name, String rarity, String condition, double value, String edition, 
                   String cardType, String color, int manaCost, String cardSubtype, boolean isFoil) {
        super(name, rarity, condition, value, edition, cardType);
        this.color = color;
        this.manaCost = manaCost;
        this.cardSubtype = cardSubtype;
        this.isFoil = isFoil;
    }
    
    /**
     * Copy Constructor
     * Creates a copy of an existing MTG card
     * 
     * @param other The MTG card to copy
     */
    public MTGCard(MTGCard other) {
        super(other);
        this.color = other.color;
        this.manaCost = other.manaCost;
        this.cardSubtype = other.cardSubtype;
        this.isFoil = other.isFoil;
    }
    
    // Accessors for MTG-specific attributes
    
    /**
     * Gets the card's color
     * @return The color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Gets the card's mana cost
     * @return The converted mana cost
     */
    public int getManaCost() {
        return manaCost;
    }
    
    /**
     * Gets the card's subtype
     * @return The card subtype
     */
    public String getCardSubtype() {
        return cardSubtype;
    }
    
    /**
     * Checks if the card is foil
     * @return true if foil, false otherwise
     */
    public boolean isFoil() {
        return isFoil;
    }
    
    // Mutators for MTG-specific attributes with validation
    
    /**
     * Sets the card's color with validation
     * @param color The new color
     * @throws IllegalArgumentException if color is invalid
     */
    public void setColor(String color) {
        if (!isValidColor(color)) {
            throw new IllegalArgumentException("Invalid color. Must be: White, Blue, Black, Red, Green, Colorless, or Multicolor");
        }
        this.color = color;
    }
    
    /**
     * Sets the card's mana cost
     * @param manaCost The new mana cost
     * @throws IllegalArgumentException if mana cost is negative
     */
    public void setManaCost(int manaCost) {
        if (manaCost < 0) {
            throw new IllegalArgumentException("Mana cost cannot be negative");
        }
        this.manaCost = manaCost;
    }
    
    /**
     * Sets the card's subtype
     * @param cardSubtype The new subtype
     * @throws IllegalArgumentException if subtype is null or empty
     */
    public void setCardSubtype(String cardSubtype) {
        if (cardSubtype == null || cardSubtype.trim().isEmpty()) {
            throw new IllegalArgumentException("Card subtype cannot be empty");
        }
        this.cardSubtype = cardSubtype.trim();
    }
    
    /**
     * Sets whether the card is foil
     * @param isFoil true for foil, false for non-foil
     */
    public void setIsFoil(boolean isFoil) {
        this.isFoil = isFoil;
    }
    
    // Override setters from base class to add MTG-specific validation
    
    /**
     * Sets the card's rarity with MTG-specific validation
     * @param rarity The new rarity
     * @throws IllegalArgumentException if rarity is invalid for MTG
     */
    @Override
    public void setRarity(String rarity) {
        if (!isValidRarity(rarity)) {
            throw new IllegalArgumentException("Invalid rarity. Must be: Common, Uncommon, Rare, Mythic Rare, or Special");
        }
        super.setRarity(rarity);
    }
    
    /**
     * Sets the card's condition with MTG-specific validation
     * @param condition The new condition
     * @throws IllegalArgumentException if condition is invalid
     */
    @Override
    public void setCondition(String condition) {
        if (!isValidCondition(condition)) {
            throw new IllegalArgumentException("Invalid condition. Must be: Mint, Near Mint, Excellent, Good, Light Played, Played, Poor, or Damaged");
        }
        super.setCondition(condition);
    }
    
    // Validation helper methods
    
    /**
     * Validates if a rarity string is valid for MTG
     * @param rarity The rarity to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidRarity(String rarity) {
        for (String valid : VALID_RARITIES) {
            if (valid.equalsIgnoreCase(rarity)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validates if a condition string is valid
     * @param condition The condition to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidCondition(String condition) {
        for (String valid : VALID_CONDITIONS) {
            if (valid.equalsIgnoreCase(condition)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validates if a color string is valid for MTG
     * @param color The color to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidColor(String color) {
        for (String valid : VALID_COLORS) {
            if (valid.equalsIgnoreCase(color)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Displays the MTG card in a formatted manner
     * Implementation of abstract method from Card class
     */
    @Override
    public void displayCard() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("MTG CARD DETAILS");
        System.out.println("=".repeat(80));
        System.out.printf("Name:           %s%s%n", getName(), isFoil ? " (FOIL)" : "");
        System.out.printf("Edition:        %s%n", getEdition());
        System.out.printf("Card Type:      %s%n", getCardType());
        System.out.printf("Subtype:        %s%n", cardSubtype);
        System.out.printf("Color:          %s%n", color);
        System.out.printf("Mana Cost:      %d%n", manaCost);
        System.out.printf("Rarity:         %s%n", getRarity());
        System.out.printf("Condition:      %s%n", getCondition());
        System.out.printf("Value:          $%.2f%n", getValue());
        System.out.println("=".repeat(80));
    }
    
    /**
     * Returns a detailed string representation of the MTG card
     * @return String with all card information
     */
    @Override
    public String toString() {
        return String.format("%s | Color: %s | Mana: %d | Subtype: %s | Foil: %s",
                           super.toString(), color, manaCost, cardSubtype, isFoil ? "Yes" : "No");
    }
    
    /**
     * Gets a list of valid rarities for display purposes
     * @return Array of valid rarity strings
     */
    public static String[] getValidRarities() {
        return VALID_RARITIES.clone();
    }
    
    /**
     * Gets a list of valid conditions for display purposes
     * @return Array of valid condition strings
     */
    public static String[] getValidConditions() {
        return VALID_CONDITIONS.clone();
    }
    
    /**
     * Gets a list of valid colors for display purposes
     * @return Array of valid color strings
     */
    public static String[] getValidColors() {
        return VALID_COLORS.clone();
    }
}
