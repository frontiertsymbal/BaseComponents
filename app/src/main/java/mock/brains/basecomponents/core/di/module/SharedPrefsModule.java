package mock.brains.basecomponents.core.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import mock.brains.basecomponents.core.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsModule {

    private String sharedPrefsName;

    public SharedPrefsModule(String sharedPrefsName) {
        this.sharedPrefsName = sharedPrefsName;
    }

    @Provides
    @Singleton
    SharedPreferences providePreferences(Context context) {
        return context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPrefsManager provideSharedPrefsManager(SharedPreferences sharedPreferences) {
        return new SharedPrefsManager(sharedPreferences);
    }
}
