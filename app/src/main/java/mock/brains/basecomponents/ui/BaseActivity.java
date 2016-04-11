package mock.brains.basecomponents.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mock.brains.basecomponents.BaseComponentsApplication;
import mock.brains.basecomponents.core.di.component.AppComponent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDiComponent();
        compositeSubscription = new CompositeSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    abstract protected void initDiComponent();

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
