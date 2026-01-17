package xyz.herberto.foxEconomy.api;

/**
 * Internal provider class for FoxAPI
 * This class should not be used directly by external plugins
 */
public class FoxAPIProvider {

    private static FoxAPI api;

    public static void setApi(FoxAPI apiInstance) {
        if (api != null) {
            throw new IllegalStateException("FoxAPI has already been initialized");
        }
        api = apiInstance;
    }

    public static void unsetApi() {
        api = null;
    }

    static FoxAPI getApi() {
        if (api == null) {
            throw new IllegalStateException("FoxAPI is not available - is FoxEconomy loaded?");
        }
        return api;
    }

    public static boolean isAvailable() {
        return api != null;
    }

}

