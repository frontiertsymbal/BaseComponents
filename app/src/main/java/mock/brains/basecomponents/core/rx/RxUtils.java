package mock.brains.basecomponents.core.rx;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RxUtils {

    public static void safeUnsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static void safeUnsubscribe(CompositeSubscription compositeSubscription) {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    public static <T> Observable.Transformer<T, T> applyIoAndMainSchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // TODO: AlexTsymbal methods with doOn with dialog
//                .doOnSubscribe(() -> DialogHelper.showProgressDialog(context, dialog))
//                .doOnCompleted(() -> DialogHelper.dismissProgressDialog(context))
//                .doOnError(e -> DialogHelper.dismissProgressDialog(context));
}
