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

public class EcoGiveSubCommand extends CommandBase {

    private final RequiredArg<PlayerRef> playerArg;
    private final RequiredArg<Double> amountArg;

    public EcoGiveSubCommand() {
        super("give", "Give a player money");
        addAliases("add");
        playerArg = withRequiredArg("player", "Player to give money", ArgTypes.PLAYER_REF);
        amountArg = withRequiredArg("amount", "Amount to give", ArgTypes.DOUBLE);
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
                    FoxEconomy.getProfileHandler().addBalance(player.getUuid(), amount);
                    context.sendMessage(Message.raw("You have given " + player.getUsername() + " $" + amount + "."));
                    player.sendMessage(Message.raw("You have been given $" + amount + "."));
                } else {
                    context.sendMessage(Message.translation("server.commands.errors.noSuchPlayer").param("username", player.getUsername()));
                }
            } else {
                context.sendMessage(Message.raw("Invalid number."));
            }


        });
    }

}
