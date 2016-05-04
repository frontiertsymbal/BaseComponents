package mock.brains.basecomponents.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import mock.brains.basecomponents.BaseComponentsApplication;
import mock.brains.basecomponents.core.ApiKeyStoreManager;
import mock.brains.basecomponents.core.SharedPrefsManager;
import mock.brains.basecomponents.core.api.ApiManager;
import mock.brains.basecomponents.core.briteDb.DbManager;
import mock.brains.basecomponents.core.di.InjectHelper;
import mock.brains.basecomponents.core.di.component.AppComponent;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private CompositeSubscription compositeSubscription;

    @Inject
    protected DbManager mDbManager;
    @Inject
    protected ApiManager mApiManager;
    @Inject
    protected SharedPrefsManager mSharedPrefsManager;
    @Inject
    protected ApiKeyStoreManager apiKeyStoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inject all dependencies to base activity
        InjectHelper.buildAppComponent(this).inject(this);

        //Init RxCompositeSubscription
        compositeSubscription = new CompositeSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    protected AppComponent getAppComponent() {
        return ((BaseComponentsApplication) getApplication()).getAppComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
