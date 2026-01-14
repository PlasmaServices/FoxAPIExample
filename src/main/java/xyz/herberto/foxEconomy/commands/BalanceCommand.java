package xyz.herberto.foxEconomy.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import xyz.herberto.foxEconomy.FoxEconomy;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class BalanceCommand extends AbstractAsyncCommand {

    private final OptionalArg<PlayerRef> playerArg;

    public BalanceCommand() {
        super("balance", "View your or another player's balance");
        addAliases("bal", "money");
        playerArg = withOptionalArg("target", "Player to view balance", ArgTypes.PLAYER_REF);
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {

        PlayerRef target = playerArg.get(context);

        if (target == null) {
            double balance = FoxEconomy.getProfileHandler().getBalance(context.sender().getUuid());
            context.sendMessage(Message.raw("Your balance is $" + balance));
        } else {

            PlayerRef targetPlayer = playerArg.get(context);
            if (FoxEconomy.getProfileHandler().hasProfile(targetPlayer.getUuid())) {
                double balance = FoxEconomy.getProfileHandler().getBalance(targetPlayer.getUuid());
                context.sendMessage(Message.raw(targetPlayer.getUsername() + "'s balance is $" + balance));
            } else {
                context.sendMessage(Message.translation("server.commands.errors.noSuchPlayer").param("username", targetPlayer.getUsername()));
            }

        }

        return CompletableFuture.completedFuture(null);
    }

}
