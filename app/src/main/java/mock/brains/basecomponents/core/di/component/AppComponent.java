package mock.brains.basecomponents.core.di.component;

import android.app.Application;

import mock.brains.basecomponents.core.SharedPrefsManager;
import mock.brains.basecomponents.core.api.ApiManager;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.di.module.ApiModule;
import mock.brains.basecomponents.core.di.module.AppModule;
import mock.brains.basecomponents.core.di.module.DbModule;
import mock.brains.basecomponents.core.di.module.SharedPrefsModule;
import mock.brains.basecomponents.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        DbModule.class,
        ApiModule.class,
        SharedPrefsModule.class
})
public interface AppComponent {

    void inject(Application application);

    void inject(MainActivity activity);

    SharedPrefsManager sharedPrefsManager();

    DbManager dbManager();

    ApiManager apiManager();
}

