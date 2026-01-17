# FoxAPIExample

This plugin details basic usage of the [FoxAPI](https://github.com/PlasmaServices/FoxAPI) to get a player's balance in a custom command using the FoxAPI.

## Setup
To build from source, you will need to obtain a HytaleServer.jar file, which requires you to own Hytale. View Hytale's documentation on downloading a jar [here](https://support.hytale.com/hc/en-us/articles/45326769420827-Hytale-Server-Manual#server-setup)

Next, you need the FoxAPI jar, which can be downloaded from [BuiltByBit](https://builtbybit.com/resources/foxeconomy.89954/) or built from the [Github Repo](https://github.com/PlasmaServices/FoxEconomy)

Once you have a HytaleServer.jar and FoxAPI.jar files, create a directory called `libs/` in the project root directory, and place the `HytaleServer.jar` and `FoxAPI.jar` files in the folder.

You can then build it using Maven from your IDE of choice.

In IntelliJ Idea, click the Maven logo on the right hand side, click FoxEconomy, Lifecycle, and then dobule click `install`.

This will create the FoxEconomy jar in the `target/` folder.
