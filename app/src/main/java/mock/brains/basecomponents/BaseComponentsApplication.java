package mock.brains.basecomponents;

import android.app.Application;

import mock.brains.basecomponents.core.di.InjectHelper;
import mock.brains.basecomponents.core.di.component.AppComponent;
import timber.log.Timber;

public class BaseComponentsApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initInjector() {
        appComponent = InjectHelper.buildAppComponent(this);
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
