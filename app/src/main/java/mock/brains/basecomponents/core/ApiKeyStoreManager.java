package mock.brains.basecomponents.core;

public class ApiKeyStoreManager {

    static {
        System.loadLibrary("ApiStore");
    }

    private native String getKeyOne();

    private native String getKeyTwo();

    public String getApiKeyOne() {
        return getKeyOne();
    }

    public String getApiKeyTwo() {
        return getKeyTwo();
    }
}
