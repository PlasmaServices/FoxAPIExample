package xyz.herberto.hytalePlugin.commands.economy;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import xyz.herberto.hytalePlugin.commands.economy.sub.EcoGiveSubCommand;
import xyz.herberto.hytalePlugin.commands.economy.sub.EcoRemoveSubCommand;
import xyz.herberto.hytalePlugin.commands.economy.sub.EcoResetSubCommand;
import xyz.herberto.hytalePlugin.commands.economy.sub.EcoSetSubCommand;

public class EconomyCommands extends AbstractCommandCollection {

    public EconomyCommands() {
        super("economy", "Player economy commands");
        addAliases("eco");
        addSubCommand(new EcoSetSubCommand());
        addSubCommand(new EcoGiveSubCommand());
        addSubCommand(new EcoResetSubCommand());
        addSubCommand(new EcoRemoveSubCommand());
    }


}
