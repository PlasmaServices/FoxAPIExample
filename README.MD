# FoxEconomy

## About
FoxEconomy is a simple, easy-to-use economy plugin for Hytale.

Using the FoxAPI, you can integrate your plugins to use FoxEconomy.

## Building From Source
To build from source, you will need to obtain a HytaleServer.jar file, which requires you to own Hytale. View Hytale's documentation on downloading a jar [here](https://support.hytale.com/hc/en-us/articles/45326769420827-Hytale-Server-Manual#server-setup)

Once you have a HytaleServer.jar file, create a directory called `libs/` in the project root directory, and place the `HytaleServer.jar` file in the folder.

You can then build it using Maven from your IDE of choice.

In IntelliJ Idea, click the Maven logo on the right hand side, click FoxEconomy, Lifecycle, and then dobule click `install`.

This will create the FoxEconomy jar in the `target/` folder.

## Using FoxAPI in Your Plugin
To use FoxAPI in your plugin, add FoxEconomy as a dependency in your `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>xyz.herberto.foxEconomy</groupId>
        <artifactId>FoxEconomy</artifactId>
        <version>1.0.0</version>
        <systemPath>${pom.basedir}/libs/FoxEconomy.jar</systemPath>
        <scope>system</scope>
    </dependency>
</dependencies>
```
You will need to add the FoxEconomy JAR file to your project's `libs/` folder, similar to the **Building From Source** section.

The API can be used as follows:
```java
import xyz.herberto.foxEconomy.api.FoxAPI;

FoxAPI api = FoxAPI.getInstance();

double balance = api.getBalance(playerUUID);
api.addMoney(playerUUID, 100.0);

```

A full setup should look like this:
```java
package fox.economy.example;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import lombok.Getter;
import xyz.herberto.foxEconomy.api.FoxAPI;
import xyz.herberto.foxEconomy.api.FoxAPIProvider;

import javax.annotation.Nonnull;

@Getter
public class FoxAPIExample extends JavaPlugin {

    private FoxAPI economyAPI;

    public FoxAPIExample(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        // Check if FoxEconomy is available
        if (!FoxAPIProvider.isAvailable()) {
            getLogger().atSevere().log("FoxEconomy is not available! This plugin requires FoxEconomy to function.");
            return;
        }

        // Get the FoxAPI instance
        try {
            economyAPI = FoxAPI.getInstance();
            getLogger().atInfo().log("Successfully hooked into FoxEconomy!");
        } catch (IllegalStateException e) {
            getLogger().atSevere().log("Failed to get FoxAPI instance!", e);
            return;
        }
        
        getLogger().atInfo().log("FoxAPIExample has been enabled!");
    }

    @Override
    protected void shutdown() {
        economyAPI = null;
        getLogger().atInfo().log("FoxAPIExample has been disabled!");
    }

}
```

All API methods are detailed in the API-README.MD file.

## Support
Support for FoxEconomy and FoxAPI is provided in the [Plasma Services Discord Server](https://discord.gg/mcplugins)
