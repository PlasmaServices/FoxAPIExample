package xyz.herberto.foxEconomy;


import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.foxEconomy.commands.BalanceCommand;
import xyz.herberto.foxEconomy.commands.PayCommand;
import xyz.herberto.foxEconomy.commands.economy.EconomyCommands;
import xyz.herberto.foxEconomy.profiles.ProfileHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;

public final class FoxEconomy extends JavaPlugin {

    @Getter private static FoxEconomy instance;
    @Getter private static ProfileHandler profileHandler;

    public FoxEconomy(@Nonnull JavaPluginInit init) {
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

        getLogger().atInfo().log("FoxEconomy has been enabled! - developed by https://herberto.xyz");

    }

    @Override
    protected void shutdown() {
        instance = null;
        profileHandler = null;
        getLogger().atInfo().log("FoxEconomy has been disabled!");
    }


}
