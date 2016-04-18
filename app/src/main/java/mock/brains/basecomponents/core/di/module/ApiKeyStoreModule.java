package mock.brains.basecomponents.core.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mock.brains.basecomponents.core.ApiKeyStoreManager;

@Module
public class ApiKeyStoreModule {

    @Provides
    @Singleton
    ApiKeyStoreManager provideApiKeyStoreManager() {
        return new ApiKeyStoreManager();
    }
}
