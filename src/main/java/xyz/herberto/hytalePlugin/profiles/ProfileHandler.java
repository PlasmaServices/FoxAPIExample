package xyz.herberto.hytalePlugin.profiles;


import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ProfileHandler {

    public final File playerDataFolder;

    public ProfileHandler(File pluginFolder) {
        this.playerDataFolder = new File(pluginFolder, "playerdata");
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
        ProfileListener.register();
    }

    public File getPlayerFolder(UUID uuid) {
        File playerFolder = new File(playerDataFolder, uuid.toString());
        if (!playerFolder.exists()) {
            playerFolder.mkdirs();
        }
        return playerFolder;
    }

    public boolean hasProfile(UUID uuid) {
        File playerFile = new File(playerDataFolder + "/" + uuid.toString(), "profile.yml");
        return playerFile.exists();
    }

    public File getPlayerFile(UUID uuid) {
        File playerFile = new File(getPlayerFolder(uuid), "profile.yml");
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerFile;
    }

    public double getBalance(UUID uuid) {
        return getPlayerConfig(uuid).getDouble("balance", 0.0);
    }

    public void addBalance(UUID uuid, double amount) {
        setBalance(uuid, getBalance(uuid) + amount);
    }

    public void removeBalance(UUID uuid, double amount) {
        setBalance(uuid, getBalance(uuid) - amount);
    }

    public void resetBalance(UUID uuid) {
        setBalance(uuid, 0.0);
    }

    public void setBalance(UUID uuid, double balance) {
        FileConfiguration config = getPlayerConfig(uuid);
        config.set("balance", balance);
        savePlayerConfig(uuid, config);
    }

    public FileConfiguration getPlayerConfig(UUID uuid) {
        return YamlConfiguration.loadConfiguration(getPlayerFile(uuid));
    }

    public void savePlayerConfig(UUID uuid, FileConfiguration config) {
        try {
            config.save(getPlayerFile(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultProfile(UUID uuid, String name) {
        FileConfiguration config = getPlayerConfig(uuid);
        if(config.getString("name") == null || config.getDouble("balance") == 0) {
            config.set("name", name);
            config.set("balance", 0.00);
            savePlayerConfig(uuid, config);
        }
    }


}
