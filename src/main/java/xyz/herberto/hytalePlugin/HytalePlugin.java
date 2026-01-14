package xyz.herberto.hytalePlugin;


import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.hytalePlugin.commands.BalanceCommand;
import xyz.herberto.hytalePlugin.commands.economy.EconomyCommands;
import xyz.herberto.hytalePlugin.profiles.ProfileHandler;

import javax.annotation.Nonnull;

public final class HytalePlugin extends JavaPlugin {

    @Getter private static HytalePlugin instance;
    @Getter private static ProfileHandler profileHandler;

    public HytalePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        instance = this;

        profileHandler = new ProfileHandler(getDataDirectory().toFile());

        getLogger().atInfo().log("Hytale Plugin has been enabled!");

        getCommandRegistry().registerCommand(new EconomyCommands());
        getCommandRegistry().registerCommand(new BalanceCommand());


    }


}
