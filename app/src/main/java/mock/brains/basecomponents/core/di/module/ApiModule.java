package mock.brains.basecomponents.core.di.module;

import android.content.Context;

import mock.brains.basecomponents.core.api.ApiInterface;
import mock.brains.basecomponents.core.api.ApiManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String CACHE_FOLDER_NAME = "cached";

    private int diskCacheSize;
    private int requestTimeout;
    private String apiBaseUrl;
    private Class<ApiInterface> apiInterface;

    public ApiModule(String apiBaseUrl, Class<ApiInterface> apiInterface, int diskCacheSize, int requestTimeout) {
        this.apiBaseUrl = apiBaseUrl;
        this.apiInterface = apiInterface;
        this.diskCacheSize = diskCacheSize;
        this.requestTimeout = requestTimeout;
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(ApiInterface apiInterface) {
        return new ApiManager(apiInterface);
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(apiInterface);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory converter, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //HTTP logging init
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Cache init
        File cacheDir = new File(context.getCacheDir(), CACHE_FOLDER_NAME);
        Cache cache = new Cache(cacheDir, diskCacheSize);

        httpClient.connectTimeout(requestTimeout, TimeUnit.SECONDS);
        httpClient.readTimeout(requestTimeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(requestTimeout, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        httpClient.cache(cache);

        return httpClient.build();
    }
}
