package xyz.herberto.foxEconomy.api;

import java.util.UUID;

public interface FoxAPI {

    /**
     * Get the singleton instance of FoxAPI
     *
     * @return The FoxAPI instance
     * @throws IllegalStateException if FoxEconomy is not loaded
     */
    static FoxAPI getInstance() {
        return FoxAPIProvider.getApi();
    }

    /**
     * Check if a player has an economy profile
     *
     * @param uuid The player's UUID
     * @return true if the player has a profile, false otherwise
     */
    boolean hasAccount(UUID uuid);

    /**
     * Get a player's current balance
     *
     * @param uuid The player's UUID
     * @return The player's balance, or 0.0 if they don't have an account
     */
    double getBalance(UUID uuid);

    /**
     * Set a player's balance to a specific amount
     *
     * @param uuid The player's UUID
     * @param amount The new balance amount
     * @return true if successful, false otherwise
     */
    boolean setBalance(UUID uuid, double amount);

    /**
     * Add money to a player's balance
     *
     * @param uuid The player's UUID
     * @param amount The amount to add (must be positive)
     * @return true if successful, false if amount is negative or operation failed
     */
    boolean addMoney(UUID uuid, double amount);

    /**
     * Remove money from a player's balance
     *
     * @param uuid The player's UUID
     * @param amount The amount to remove (must be positive)
     * @return true if successful, false if amount is negative or operation failed
     */
    boolean removeMoney(UUID uuid, double amount);

    /**
     * Reset a player's balance to 0.0
     *
     * @param uuid The player's UUID
     * @return true if successful, false otherwise
     */
    boolean resetBalance(UUID uuid);

    /**
     * Check if a player has at least the specified amount
     *
     * @param uuid The player's UUID
     * @param amount The amount to check
     * @return true if the player has at least the amount, false otherwise
     */
    boolean hasAmount(UUID uuid, double amount);

    /**
     * Create a new economy account for a player
     * This is automatically called when a player joins, but can be called manually
     *
     * @param uuid The player's UUID
     * @return true if the account was created, false if it already exists
     */
    boolean createAccount(UUID uuid);

}

