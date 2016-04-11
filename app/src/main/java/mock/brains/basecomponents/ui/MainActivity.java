package mock.brains.basecomponents.ui;

import android.os.Bundle;

import mock.brains.basecomponents.R;
import mock.brains.basecomponents.core.SharedPrefsManager;
import mock.brains.basecomponents.core.api.ApiManager;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.di.InjectHelper;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    DbManager mDbManager;
    @Inject
    ApiManager mApiManager;
    @Inject
    SharedPrefsManager mSharedPrefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbManager.helloDb();
        mApiManager.helloApi();
        mSharedPrefsManager.helloSharedPrefs();
    }

    @Override
    protected void initDiComponent() {
        InjectHelper.getAppComponent(this).inject(this);
    }
}
