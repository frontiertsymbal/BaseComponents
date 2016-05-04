package mock.brains.basecomponents.core.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import mock.brains.basecomponents.core.ApiKeyStoreManager;
import mock.brains.basecomponents.core.SharedPrefsManager;
import mock.brains.basecomponents.core.api.ApiManager;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.di.module.ApiModule;
import mock.brains.basecomponents.core.di.module.AppModule;
import mock.brains.basecomponents.core.di.module.DbModule;
import mock.brains.basecomponents.core.di.module.SharedPrefsModule;
import mock.brains.basecomponents.ui.BaseActivity;

@Singleton
@Component(modules = {
        AppModule.class,
        DbModule.class,
        ApiModule.class,
        SharedPrefsModule.class
})
public interface AppComponent {

    void inject(Application application);

    void inject(BaseActivity activity);

    SharedPrefsManager sharedPrefsManager();

    DbManager dbManager();

    ApiManager apiManager();

    ApiKeyStoreManager apiKeyStoreManager();
}

