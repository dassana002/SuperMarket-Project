# Agent Guidelines for FXclassProject

## Build & Run Commands

- **Build**: `mvn clean compile`
- **Run Application**: `mvn clean javafx:run`
- **Debug Mode**: `mvn clean javafx:run@debug`
- **Compile Only**: `mvn compile`

Note: No test framework is currently configured. Tests should be added via Maven (e.g., JUnit).

## Code Style Guidelines

### Package Structure
- Use hierarchical package naming: `lk.ijse.fxclassproject.*`
- Organize by layer: `Controllers`, `Models`, `DTO`, `DBConnection`, `Utility`

### Imports
- Use explicit imports, not wildcard imports
- Order: Java standard library → JavaFX → Project packages
- Remove unused imports

### Naming Conventions
- **Classes**: PascalCase (e.g., `CustomerController`, `CustomerDTO`, `DBConnection`)
- **Methods**: camelCase (e.g., `loadCustomerTable`, `customerDelete`)
- **Fields**: camelCase (e.g., `idField`, `custModel`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DB_URL`)
- **FXML fields**: camelCase with descriptive suffixes (`Field`, `Table`, `Col`)

### Code Formatting
- Indentation: 4 spaces
- Braces: K&R style (opening brace on same line)
- Max line length: 120 characters

### Error Handling
- Use try-catch blocks explicitly; avoid silent failures
- Log exceptions with `e.printStackTrace()` (upgrade to proper logging later)
- Always show user-facing errors via `javafx.scene.control.Alert`
- Validate input before processing (e.g., parse numeric fields safely)

### JavaFX Patterns
- Use `@FXML` annotation for FXML-injected fields
- Implement `Initializable` for controller setup
- Use `ObservableList` for table data binding
- Use `PropertyValueFactory` to link TableColumn to DTO attributes

### Type Safety
- Target Java 11 (compiler source/target)
- Specify generic types in collections (e.g., `List<CustomerDTO>`)
- Avoid raw types in TableView declarations

### DTO/Model Separation
- **DTO**: Plain data transfer objects with getters (e.g., `CustomerDTO`)
- **Model**: Business logic layer (e.g., `CustomerModel` calls database operations)
- **Controller**: UI event handlers that orchestrate DTO/Model interaction

## Dependencies
- JavaFX 13 (controls, fxml)
- MySQL Connector J 9.5.0
- Maven Compiler 3.8.0
- Maven JavaFX Plugin 0.0.4
