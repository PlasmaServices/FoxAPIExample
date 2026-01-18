package xyz.herberto.foxAPIExample;


import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.foxAPIExample.commands.CustomBalanceCommand;
import xyz.herberto.foxAPI.api.FoxAPI;
import xyz.herberto.foxAPI.api.FoxAPIProvider;

import javax.annotation.Nonnull;
import java.util.List;


public final class FoxAPIExample extends JavaPlugin {

    @Getter private static FoxAPI foxAPI;
    @Getter private static FoxAPIExample instance;

    public FoxAPIExample(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        instance = this;
    }

    @Override
    protected void start() {
        // FoxAPI MUST be registered in the start void and not the setup void for it to load correctly

        if(!FoxAPIProvider.isAvailable()) {
            getLogger().atSevere().log("FoxAPI is not available! Shutting down...");
            return;
        }

        try {
            foxAPI = FoxAPI.getInstance();
            getLogger().atInfo().log("Hooked into FoxAPI successfully!");
        } catch(IllegalStateException e) {
            getLogger().atSevere().log("Failed to hook into FoxAPI! Shutting down...", e);
            return;
        }

        List.of(
                new CustomBalanceCommand()
        ).forEach(command -> getCommandRegistry().registerCommand(command));

        getLogger().atInfo().log("FoxAPIExample has been enabled! - developed by https://herberto.xyz");
    }

    @Override
    protected void shutdown() {

        instance = null;
        getLogger().atInfo().log("FoxAPIExample has been disabled!");
    }


}
