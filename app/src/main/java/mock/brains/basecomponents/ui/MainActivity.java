package mock.brains.basecomponents.ui;

import android.os.Bundle;
import android.util.Log;

import mock.brains.basecomponents.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbManager.helloDb();
        mApiManager.helloApi();
        mSharedPrefsManager.helloSharedPrefs();
        Log.e(TAG, "onCreate: " + apiKeyStoreManager.getApiKeyOne());
        Log.e(TAG, "onCreate: " + apiKeyStoreManager.getApiKeyTwo());
    }

}
