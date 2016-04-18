package mock.brains.basecomponents.ui;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import mock.brains.basecomponents.R;
import mock.brains.basecomponents.core.ApiKeyStoreManager;
import mock.brains.basecomponents.core.SharedPrefsManager;
import mock.brains.basecomponents.core.api.ApiManager;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.di.InjectHelper;

public class MainActivity extends BaseActivity {

    @Inject
    DbManager mDbManager;
    @Inject
    ApiManager mApiManager;
    @Inject
    SharedPrefsManager mSharedPrefsManager;
    @Inject
    ApiKeyStoreManager apiKeyStoreManager;

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

    @Override
    protected void initDiComponent() {
        InjectHelper.getAppComponent(this).inject(this);
    }
}
