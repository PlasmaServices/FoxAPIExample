package xyz.herberto.hytalePlugin;


import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.hytalePlugin.commands.economy.EconomyCommands;
import xyz.herberto.hytalePlugin.commands.TestCommand;
import xyz.herberto.hytalePlugin.profiles.ProfileHandler;
import xyz.herberto.hytalePlugin.profiles.ProfileListener;

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

        System.out.println("HytalePlugin loaded");

        getCommandRegistry().registerCommand(new TestCommand("test", "A test command"));
        getCommandRegistry().registerCommand(new EconomyCommands());


    }


}
