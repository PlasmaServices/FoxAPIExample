package xyz.herberto.foxEconomy.api;

import xyz.herberto.foxEconomy.FoxEconomy;

import java.util.UUID;


public class FoxAPIImpl implements FoxAPI {

    private final FoxEconomy plugin;

    public FoxAPIImpl(FoxEconomy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean hasAccount(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        return FoxEconomy.getProfileHandler().hasProfile(uuid);
    }

    @Override
    public double getBalance(UUID uuid) {
        if (uuid == null) {
            return 0.0;
        }
        if (!hasAccount(uuid)) {
            return 0.0;
        }
        return FoxEconomy.getProfileHandler().getBalance(uuid);
    }

    @Override
    public boolean setBalance(UUID uuid, double amount) {
        if (uuid == null || amount < 0) {
            return false;
        }
        try {
            if (!hasAccount(uuid)) {
                createAccount(uuid);
            }
            FoxEconomy.getProfileHandler().setBalance(uuid, amount);
            return true;
        } catch (Exception e) {
            plugin.getLogger().atSevere().log("Error setting balance for UUID: " + uuid, e);
            return false;
        }
    }

    @Override
    public boolean addMoney(UUID uuid, double amount) {
        if (uuid == null || amount < 0) {
            return false;
        }
        try {
            if (!hasAccount(uuid)) {
                createAccount(uuid);
            }
            FoxEconomy.getProfileHandler().addBalance(uuid, amount);
            return true;
        } catch (Exception e) {
            plugin.getLogger().atSevere().log("Error adding money for UUID: " + uuid, e);
            return false;
        }
    }

    @Override
    public boolean removeMoney(UUID uuid, double amount) {
        if (uuid == null || amount < 0) {
            return false;
        }
        try {
            // Create account if it doesn't exist
            if (!hasAccount(uuid)) {
                createAccount(uuid);
            }
            FoxEconomy.getProfileHandler().removeBalance(uuid, amount);
            return true;
        } catch (Exception e) {
            plugin.getLogger().atSevere().log("Error removing money for UUID: " + uuid, e);
            return false;
        }
    }

    @Override
    public boolean resetBalance(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        try {
            // Create account if it doesn't exist
            if (!hasAccount(uuid)) {
                createAccount(uuid);
            }
            FoxEconomy.getProfileHandler().resetBalance(uuid);
            return true;
        } catch (Exception e) {
            plugin.getLogger().atSevere().log("Error resetting balance for UUID: " + uuid, e);
            return false;
        }
    }

    @Override
    public boolean hasAmount(UUID uuid, double amount) {
        if (uuid == null || amount < 0) {
            return false;
        }
        return getBalance(uuid) >= amount;
    }

    @Override
    public boolean createAccount(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        if (hasAccount(uuid)) {
            return false;
        }
        try {
            FoxEconomy.getProfileHandler().setDefaultProfile(uuid);
            return true;
        } catch (Exception e) {
            plugin.getLogger().atSevere().log("Error creating account for UUID: " + uuid, e);
            return false;
        }
    }

}

