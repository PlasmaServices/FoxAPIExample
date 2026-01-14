package xyz.herberto.hytalePlugin.profiles;

import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import xyz.herberto.hytalePlugin.HytalePlugin;

public class ProfileListener {

    public static void register() {
        HytalePlugin.getInstance().getEventRegistry().registerGlobal(PlayerConnectEvent.class, event -> {
            HytalePlugin.getProfileHandler().setDefaultProfile(event.getPlayerRef().getUuid(), event.getPlayerRef().getUsername());
        });
    }

}
