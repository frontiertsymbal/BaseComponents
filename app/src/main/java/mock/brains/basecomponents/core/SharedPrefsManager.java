package mock.brains.basecomponents.core;

import android.content.SharedPreferences;

import timber.log.Timber;

public class SharedPrefsManager {

    private final SharedPreferences sharedPreferences;

    public SharedPrefsManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    //****************************************************************************************************************************************************************************//

    // TODO AlexTsymbal: implement methods for work with SharedPreferences

    //Sample method
    public void helloSharedPrefs() {
        Timber.e("SharedPreferences enabled = %1$s", String.valueOf(sharedPreferences != null));
    }
}
