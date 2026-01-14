package xyz.herberto.foxEconomy.profiles;

import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import xyz.herberto.foxEconomy.FoxEconomy;

public class ProfileListener {

    public static void register() {
        FoxEconomy.getInstance().getEventRegistry().registerGlobal(PlayerConnectEvent.class, event -> {
            FoxEconomy.getProfileHandler().setDefaultProfile(event.getPlayerRef().getUuid(), event.getPlayerRef().getUsername());
        });
    }

}
