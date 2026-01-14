package xyz.herberto.foxEconomy.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import xyz.herberto.foxEconomy.FoxEconomy;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class PayCommand extends AbstractAsyncCommand {

    private final RequiredArg<PlayerRef> playerArg;
    private final RequiredArg<Double> amountArg;

    public PayCommand() {
        super("pay", "Pay another player money");
        playerArg = withRequiredArg("player", "Player to pay", ArgTypes.PLAYER_REF);
        amountArg = withRequiredArg("amount", "Amount to pay", ArgTypes.DOUBLE);
    }

    @Override
    @Nonnull
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {

        if(!context.isPlayer()) {
            context.sendMessage(Message.raw("You must be a player to use this command!"));
            return CompletableFuture.completedFuture(null);
        }

        PlayerRef target = playerArg.get(context);
        double amount = amountArg.get(context);

        CommandSender player = context.sender();

        if(FoxEconomy.getProfileHandler().getBalance(player.getUuid()) < amount) {
            context.sendMessage(Message.raw("You cannot afford that!"));
            return CompletableFuture.completedFuture(null);
        }

        FoxEconomy.getProfileHandler().removeBalance(player.getUuid(), amount);
        FoxEconomy.getProfileHandler().addBalance(target.getUuid(), amount);


        player.sendMessage(Message.raw("You have paid " + target.getUsername() + " $" + amount + "."));
        target.sendMessage(Message.raw("You have received $" + amount + " from " + player.getDisplayName() + "."));


        return CompletableFuture.completedFuture(null);
    }


}
