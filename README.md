# MTG Card Collection Tracker

## Project Overview
A Java-based application for tracking Magic: The Gathering card collections with full CRUD operations, search/filter capabilities, and CSV import/export functionality.

## Features
- ✓ Add, delete, modify, and display cards
- ✓ Search cards by name
- ✓ Filter cards by rarity and color
- ✓ View collection statistics
- ✓ Export/Import collection to/from CSV
- ✓ Comprehensive input validation and error handling
- ✓ Command-line interface (CLI)
- ✓ Extensible design for future GUI implementation

## Project Structure
```
C:\MTGCardTracker\
├── src\
│   ├── Card.java              (Base/Super class)
│   ├── MTGCard.java           (Derived/Sub class)
│   ├── CardCollection.java    (Collection manager)
│   ├── InputValidator.java    (Input validation utility)
│   └── MTGCardTracker.java    (Main application)
├── TEST_CASES.md              (Comprehensive testing document)
└── README.md                  (This file)
```

## Class Hierarchy

### Card (Base Class)
- **Purpose:** Abstract base class for all trading cards
- **Attributes:** name, rarity, condition, value, edition, cardType
- **Methods:** Getters, setters, toString(), equals(), abstract displayCard()

### MTGCard (Derived Class)
- **Purpose:** Specific implementation for Magic: The Gathering cards
- **Additional Attributes:** color, manaCost, cardSubtype, isFoil
- **Validation:** MTG-specific validation for rarities, conditions, and colors
- **Methods:** Enhanced display, MTG-specific getters/setters

### CardCollection (Manager Class)
- **Purpose:** Manages a collection of MTGCard objects
- **Data Structure:** ArrayList<MTGCard>
- **Operations:** Add, delete, modify, display, search, filter, import/export

### InputValidator (Utility Class)
- **Purpose:** Provides robust input validation
- **Methods:** Validate integers, doubles, strings, choices, yes/no prompts

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Windows Command Prompt or any Java IDE

### Compilation
```cmd
cd C:\MTGCardTracker\src
javac Card.java MTGCard.java CardCollection.java InputValidator.java MTGCardTracker.java
```

### Execution
```cmd
java MTGCardTracker
```

## Usage Guide

### Main Menu Options
1. **Add New Card** - Add a new card to the collection
2. **Delete Card** - Remove a card by index or name
3. **Modify Card** - Update any attribute of an existing card
4. **Display Cards** - View all cards (detailed or compact list)
5. **Search Cards** - Search by card name (partial match supported)
6. **Filter Cards** - Filter by rarity or color
7. **View Statistics** - See collection totals and breakdowns
8. **Export to CSV** - Save collection to CSV file
9. **Import from CSV** - Load collection from CSV file
10. **Manage Collection Settings** - Change collection name
0. **Exit** - Close the application

### Adding a Card
When adding a card, you'll be prompted for:
- **Name:** Card name (e.g., "Black Lotus")
- **Rarity:** Common, Uncommon, Rare, Mythic Rare, or Special
- **Condition:** Mint, Near Mint, Excellent, Good, Light Played, Played, Poor, or Damaged
- **Value:** Monetary value in dollars
- **Edition:** Set name (e.g., "Alpha", "War of the Spark")
- **Card Type:** Creature, Instant, Sorcery, Enchantment, etc.
- **Color:** White, Blue, Black, Red, Green, Colorless, or Multicolor
- **Mana Cost:** Converted mana cost (0-20)
- **Subtype:** Card subtype (e.g., "Human Wizard", "Dragon")
- **Foil:** Yes or No

### Valid Options

**Rarities:**
- Common
- Uncommon
- Rare
- Mythic Rare
- Special

**Conditions:**
- Mint
- Near Mint
- Excellent
- Good
- Light Played
- Played
- Poor
- Damaged

**Colors:**
- White
- Blue
- Black
- Red
- Green
- Colorless
- Multicolor

## CSV Format

### Export Format
The CSV file contains the following columns:
```
Name,Rarity,Condition,Value,Edition,CardType,Color,ManaCost,Subtype,Foil
```

### Example CSV
```csv
Name,Rarity,Condition,Value,Edition,CardType,Color,ManaCost,Subtype,Foil
Black Lotus,Rare,Near Mint,50000.00,Alpha,Artifact,Colorless,0,Artifact,No
Lightning Bolt,Common,Excellent,5.50,Unlimited,Instant,Red,1,Instant,No
```

## Testing

### Comprehensive Test Suite
See `TEST_CASES.md` for detailed testing documentation including:
- 114 test cases covering all functionality
- Edge case testing (empty collections, boundary values, special characters)
- Error testing (invalid inputs, out of range, null values)
- Valid data testing (all CRUD operations)
- Integration testing (complete workflows, class hierarchy)

### Quick Test
The application includes 3 sample cards preloaded for testing:
1. Black Lotus (Rare artifact)
2. Lightning Bolt (Common instant)
3. Nicol Bolas, Dragon-God (Mythic Rare foil planeswalker)

## Design Principles

### Object-Oriented Design
- **Inheritance:** MTGCard extends Card
- **Encapsulation:** Private fields with public getters/setters
- **Abstraction:** Abstract displayCard() method
- **Polymorphism:** Method overriding (toString, displayCard)

### Data Structure
- ArrayList for dynamic collection management
- Efficient add, delete, search, and filter operations

### Input Validation
- Type checking (integers, doubles, strings)
- Range validation (min/max values)
- Choice validation (predefined options)
- Empty/null checking
- Comprehensive error messages

### Error Handling
- try-catch blocks for file operations
- IllegalArgumentException for invalid data
- User-friendly error messages
- Graceful recovery from errors

## Future Enhancements (GUI Ready)

The application is designed to support future GUI implementation:
- CSV import/export already functional
- Single card entry methods prepared
- Separation of concerns (UI, logic, data)
- Easy integration with JavaFX or Swing

### Planned GUI Features
- Graphical card entry forms
- Visual card display with images
- Drag-and-drop CSV import
- Export to multiple formats
- Advanced filtering and sorting
- Card value tracking over time

## Requirements Met

### Data Structures ✓
- **Base Class (Card):** Well-designed with all accessors, mutators, constructors
- **Derived Class (MTGCard):** Clearly related to base, includes all necessary methods

### Basic Functionality ✓
- **Adding data:** addCard() method with validation
- **Deleting data:** deleteCard() by index or name
- **Modifying data:** modifyCard() with interactive menu
- **Displaying data:** Multiple display formats (detailed, compact)

### Input ✓
- Edge cases tested (empty collections, boundary values)
- Errors handled (invalid types, ranges)
- Valid data accepted (all MTG card attributes)

### Testing ✓
- Comprehensive test document (114 test cases)
- Edge testing (empty, boundaries, special characters)
- Error testing (invalid types, ranges, choices)

### Output ✓
- Easy to read formatting (tables, borders, clear labels)
- Accurate calculations (totals, averages, statistics)

### Style ✓
- Organized code structure (5 classes, clear separation)
- Well-commented (class headers, method docs, inline comments)

## License
Educational project for learning Java OOP concepts.

## Author
Card Collection Tracker Development Team

## Version
1.0 - December 9, 2025
