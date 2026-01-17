package xyz.herberto.foxAPIExample.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import xyz.herberto.foxAPIExample.FoxAPIExample;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class CustomBalanceCommand extends AbstractAsyncCommand {

    private final OptionalArg<PlayerRef> playerArg;

    public CustomBalanceCommand() {
        super("custombalance", "View your or another player's balance");
        playerArg = withOptionalArg("target", "Player to view balance", ArgTypes.PLAYER_REF);
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {

        PlayerRef target = playerArg.get(context);

        if (target == null) {
            double balance = FoxAPIExample.getFoxAPI().getBalance(context.sender().getUuid());
            context.sendMessage(Message.raw("Your balance is $" + balance));
        } else {

            PlayerRef targetPlayer = playerArg.get(context);
            if (FoxAPIExample.getFoxAPI().hasAccount(targetPlayer.getUuid())) {
                double balance = FoxAPIExample.getFoxAPI().getBalance(targetPlayer.getUuid());
                context.sendMessage(Message.raw(targetPlayer.getUsername() + "'s balance is $" + balance));
            } else {
                context.sendMessage(Message.translation("server.commands.errors.noSuchPlayer").param("username", targetPlayer.getUsername()));
            }

        }

        return CompletableFuture.completedFuture(null);
    }

}
