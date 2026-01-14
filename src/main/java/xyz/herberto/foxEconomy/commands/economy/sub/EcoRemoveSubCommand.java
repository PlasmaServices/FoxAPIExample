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

public class EcoRemoveSubCommand extends CommandBase {

    private final RequiredArg<PlayerRef> playerArg;
    private final RequiredArg<Double> amountArg;

    public EcoRemoveSubCommand() {
        super("remove", "Remove money from a player");
        addAliases("take");
        playerArg = withRequiredArg("player", "Player to remove money from", ArgTypes.PLAYER_REF);
        amountArg = withRequiredArg("amount", "Amount to remove", ArgTypes.DOUBLE);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {

        World world = Universe.get().getDefaultWorld();

        if(world == null) {
            context.sendMessage(Message.raw("Default world not found."));
            return;
        }

        world.execute(() -> {
            PlayerRef player = playerArg.get(context);
            double amount = amountArg.get(context);

            if (amount >= 0) {
                if (FoxEconomy.getProfileHandler().hasProfile(player.getUuid())) {
                    FoxEconomy.getProfileHandler().removeBalance(player.getUuid(), amount);
                    context.sendMessage(Message.raw("You have taken $" + amount + " from " + player.getUsername()));
                    player.sendMessage(Message.raw("$" + amount + " has been taken from your balance."));
                } else {
                    context.sendMessage(Message.translation("server.commands.errors.noSuchPlayer").param("username", player.getUsername()));
                }
            } else {
                context.sendMessage(Message.raw("Invalid number."));
            }


        });
    }

}
