package xyz.herberto.foxEconomy.commands.economy.sub;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import xyz.herberto.foxEconomy.FoxEconomy;

import javax.annotation.Nonnull;

public class EcoResetSubCommand extends CommandBase {

    private final RequiredArg<PlayerRef> playerArg;

    public EcoResetSubCommand() {
        super("reset", "Reset a player's balance");
        playerArg = withRequiredArg("player", "Player to reset balance", ArgTypes.PLAYER_REF);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {

        World world = Universe.get().getDefaultWorld();

        if (world == null) {
            context.sendMessage(Message.raw("Default world not found."));
            return;
        }

        world.execute(() -> {
            PlayerRef player = playerArg.get(context);

            if (FoxEconomy.getProfileHandler().hasProfile(player.getUuid())) {
                FoxEconomy.getProfileHandler().resetBalance(player.getUuid());
                context.sendMessage(Message.raw("You have reset the balance of  " + player.getUsername() + "."));
                player.sendMessage(Message.raw("Your balance has been reset to $0.0."));
            } else {
                context.sendMessage(Message.translation("server.commands.errors.noSuchPlayer").param("username", player.getUsername()));
            }


        });
    }

}
