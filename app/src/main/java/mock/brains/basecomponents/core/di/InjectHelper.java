package mock.brains.basecomponents.core.di;

import android.content.Context;

import mock.brains.basecomponents.core.api.ApiInterface;
import mock.brains.basecomponents.core.di.component.AppComponent;
import mock.brains.basecomponents.core.di.component.DaggerAppComponent;
import mock.brains.basecomponents.core.di.module.ApiKeyStoreModule;
import mock.brains.basecomponents.core.di.module.ApiModule;
import mock.brains.basecomponents.core.di.module.AppModule;
import mock.brains.basecomponents.core.di.module.DbModule;
import mock.brains.basecomponents.core.di.module.PermissionModule;
import mock.brains.basecomponents.core.di.module.SharedPrefsModule;
import mock.brains.basecomponents.core.util.Const;

public class InjectHelper {

    public static AppComponent buildAppComponent(Context context) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .sharedPrefsModule(new SharedPrefsModule(Const.SHARED_PREFERENCES_NAME))
                .apiModule(new ApiModule(Const.API_BASE_URL, ApiInterface.class, Const.DISK_CACHE_SIZE, Const.REQUEST_TIMEOUT))
                .dbModule(new DbModule(Const.DB_NAME, Const.DB_VER))
                .apiKeyStoreModule(new ApiKeyStoreModule())
                .permissionModule(new PermissionModule())
                .build();
    }
}
