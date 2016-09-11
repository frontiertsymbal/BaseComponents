package mock.brains.basecomponents.core.di.module;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mock.brains.basecomponents.core.rx.PermissionsManager;
import mock.brains.basecomponents.core.rx.RxPermissions;
import timber.log.Timber;

@Module
public class PermissionModule {

    @Provides
    @Singleton
    RxPermissions provideRxPermissions(Context context) {
        return new RxPermissions(context);
    }

    @Provides
    @Singleton
    RxPermissions.PermissionsRequester providePermissionRequester(final Context context) {
        return new RxPermissions.PermissionsRequester() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void performRequestPermissions(String[] permissions) {
                // forward request to the system by calling fragment's method
                if (context instanceof Activity) {
                    ((Activity) context).requestPermissions(permissions, RxPermissions.REQUEST_PERMISSIONS_CODE);
                } else {
                    Timber.d("Context may be Activity");
                }
            }
        };
    }

    @Provides
    @Singleton
    PermissionsManager providePermissionsManager(RxPermissions mRxPermissions, RxPermissions.PermissionsRequester mPermissionRequester, Context context) {
        return new PermissionsManager(mRxPermissions, mPermissionRequester, context);
    }
}
