
### Getting the API Instance

```java
import xyz.herberto.foxEconomy.api.FoxAPI;
import java.util.UUID;

// Get the API instance
FoxAPI api = FoxAPI.getInstance();
```

### Check if API is Available

```java
import xyz.herberto.foxEconomy.api.FoxAPIProvider;

if (FoxAPIProvider.isAvailable()) {
    FoxAPI api = FoxAPI.getInstance();
    // Use the API
} else {
    // FoxEconomy is not loaded
    System.out.println("FoxEconomy is not available!");
}
```

## API Methods

### hasAccount(UUID uuid)
Check if a player has an economy account.

```java
UUID playerUUID = player.getUuid();
boolean hasAccount = api.hasAccount(playerUUID);
```

### getBalance(UUID uuid)
Get a player's current balance.

```java
UUID playerUUID = player.getUuid();
double balance = api.getBalance(playerUUID);
System.out.println("Player balance: $" + balance);
```

### setBalance(UUID uuid, double amount)
Set a player's balance to a specific amount.

```java
UUID playerUUID = player.getUuid();
boolean success = api.setBalance(playerUUID, 1000.0);
if (success) {
    System.out.println("Balance set to $1000");
}
```

### addMoney(UUID uuid, double amount)
Add money to a player's balance.

```java
UUID playerUUID = player.getUuid();
boolean success = api.addMoney(playerUUID, 500.0);
if (success) {
    System.out.println("Added $500 to player's balance");
}
```

### removeMoney(UUID uuid, double amount)
Remove money from a player's balance.

```java
UUID playerUUID = player.getUuid();
boolean success = api.removeMoney(playerUUID, 250.0);
if (success) {
    System.out.println("Removed $250 from player's balance");
}
```

### resetBalance(UUID uuid)
Reset a player's balance to 0.

```java
UUID playerUUID = player.getUuid();
boolean success = api.resetBalance(playerUUID);
if (success) {
    System.out.println("Player's balance has been reset");
}
```

### hasAmount(UUID uuid, double amount)
Check if a player has at least the specified amount.

```java
UUID playerUUID = player.getUuid();
double requiredAmount = 1000.0;
boolean hasEnough = api.hasAmount(playerUUID, requiredAmount);
if (hasEnough) {
    System.out.println("Player has enough money!");
}
```

### createAccount(UUID uuid)
Create an economy account for a player (usually automatic on join).

```java
UUID playerUUID = player.getUuid();
boolean created = api.createAccount(playerUUID);
if (created) {
    System.out.println("Account created for player");
} else {
    System.out.println("Account already exists");
}
```