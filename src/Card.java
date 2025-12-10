/**
 * Card - Base (Super) Class
 * 
 * This is the base class for all trading cards in the collection system.
 * It provides common attributes and methods that all card types share.
 * 
 * @author Card Collection Tracker
 * @version 1.0
 */
public abstract class Card {
    // Private instance variables (encapsulation)
    private String name;
    private String rarity;
    private String condition;
    private double value;
    private String edition;
    private String cardType;
    
    /**
     * Default Constructor
     * Initializes a card with default values
     */
    public Card() {
        this.name = "Unknown";
        this.rarity = "Common";
        this.condition = "Near Mint";
        this.value = 0.0;
        this.edition = "Unknown";
        this.cardType = "Unknown";
    }
    
    /**
     * Parameterized Constructor
     * Creates a card with specified attributes
     * 
     * @param name The card's name
     * @param rarity The card's rarity level
     * @param condition The physical condition of the card
     * @param value The monetary value of the card
     * @param edition The edition/set the card belongs to
     * @param cardType The type of card
     */
    public Card(String name, String rarity, String condition, double value, String edition, String cardType) {
        this.name = name;
        this.rarity = rarity;
        this.condition = condition;
        this.value = value;
        this.edition = edition;
        this.cardType = cardType;
    }
    
    /**
     * Copy Constructor
     * Creates a new card as a copy of an existing card
     * 
     * @param other The card to copy
     */
    public Card(Card other) {
        this.name = other.name;
        this.rarity = other.rarity;
        this.condition = other.condition;
        this.value = other.value;
        this.edition = other.edition;
        this.cardType = other.cardType;
    }
    
    // Accessors (Getters)
    
    /**
     * Gets the card's name
     * @return The card name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the card's rarity
     * @return The rarity level
     */
    public String getRarity() {
        return rarity;
    }
    
    /**
     * Gets the card's condition
     * @return The physical condition
     */
    public String getCondition() {
        return condition;
    }
    
    /**
     * Gets the card's value
     * @return The monetary value
     */
    public double getValue() {
        return value;
    }
    
    /**
     * Gets the card's edition
     * @return The edition/set name
     */
    public String getEdition() {
        return edition;
    }
    
    /**
     * Gets the card's type
     * @return The card type
     */
    public String getCardType() {
        return cardType;
    }
    
    // Mutators (Setters) with validation
    
    /**
     * Sets the card's name
     * @param name The new card name
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Card name cannot be empty");
        }
        this.name = name.trim();
    }
    
    /**
     * Sets the card's rarity
     * @param rarity The new rarity level
     * @throws IllegalArgumentException if rarity is null or empty
     */
    public void setRarity(String rarity) {
        if (rarity == null || rarity.trim().isEmpty()) {
            throw new IllegalArgumentException("Rarity cannot be empty");
        }
        this.rarity = rarity.trim();
    }
    
    /**
     * Sets the card's condition
     * @param condition The new condition
     * @throws IllegalArgumentException if condition is null or empty
     */
    public void setCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be empty");
        }
        this.condition = condition.trim();
    }
    
    /**
     * Sets the card's value
     * @param value The new monetary value
     * @throws IllegalArgumentException if value is negative
     */
    public void setValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
    }
    
    /**
     * Sets the card's edition
     * @param edition The new edition name
     * @throws IllegalArgumentException if edition is null or empty
     */
    public void setEdition(String edition) {
        if (edition == null || edition.trim().isEmpty()) {
            throw new IllegalArgumentException("Edition cannot be empty");
        }
        this.edition = edition.trim();
    }
    
    /**
     * Sets the card's type
     * @param cardType The new card type
     * @throws IllegalArgumentException if cardType is null or empty
     */
    public void setCardType(String cardType) {
        if (cardType == null || cardType.trim().isEmpty()) {
            throw new IllegalArgumentException("Card type cannot be empty");
        }
        this.cardType = cardType.trim();
    }
    
    /**
     * Abstract method to display card details
     * Must be implemented by derived classes
     */
    public abstract void displayCard();
    
    /**
     * Returns a string representation of the card
     * @return String containing all card information
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Rarity: %s | Condition: %s | Value: $%.2f | Edition: %s | Type: %s",
                           name, rarity, condition, value, edition, cardType);
    }
    
    /**
     * Checks if two cards are equal based on name and edition
     * @param obj The object to compare with
     * @return true if cards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Card)) return false;
        
        Card other = (Card) obj;
        return this.name.equalsIgnoreCase(other.name) && 
               this.edition.equalsIgnoreCase(other.edition);
    }
}
