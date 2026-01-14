package xyz.herberto.hytalePlugin;


import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.hytalePlugin.commands.BalanceCommand;
import xyz.herberto.hytalePlugin.commands.PayCommand;
import xyz.herberto.hytalePlugin.commands.economy.EconomyCommands;
import xyz.herberto.hytalePlugin.profiles.ProfileHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;

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

        Arrays.asList(
                new EconomyCommands(),
                new BalanceCommand(),
                new PayCommand()
        ).forEach(command -> getCommandRegistry().registerCommand(command));

        getLogger().atInfo().log("Hytale Plugin has been enabled!");

    }


}
