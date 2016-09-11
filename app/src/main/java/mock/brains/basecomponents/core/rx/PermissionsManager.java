package mock.brains.basecomponents.core.rx;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;

import rx.functions.Action1;

public class PermissionsManager {
    private RxPermissions rxPermissions;
    private RxPermissions.PermissionsRequester rxPermissionsRequester;
    private Context mContext;

    public PermissionsManager(RxPermissions rxPermissions, RxPermissions.PermissionsRequester rxPermissionsRequester, Context context) {
        this.rxPermissions = rxPermissions;
        this.rxPermissionsRequester = rxPermissionsRequester;
        this.mContext = context;
    }

    public void requestPermissions(String[] permissions, Action1<? super Boolean> action) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rxPermissions
                    .request(rxPermissionsRequester,
                            permissions)
                    .subscribe(action);
        } else {
            action.call(true);
        }
    }

    public RxPermissions.PermissionsRequester getRxPermissionsRequester() {
        return rxPermissionsRequester;
    }

    public boolean checkPermissionsWithoutDialog(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                int permissionResult = mContext.checkSelfPermission(permission);
                if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getStoragePermissions() {
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
    }

    public String[] getCameraPermissions() {
        return new String[]{Manifest.permission.CAMERA};
    }

    public String[] combine(String[]... arrays) {
        ArrayList<String> arrayListPermissions = new ArrayList<>();
        for (String[] permission : arrays) {
            arrayListPermissions.addAll(Arrays.asList(permission));
        }
        return arrayListPermissions.toArray(new String[arrayListPermissions.size()]);
    }

}
