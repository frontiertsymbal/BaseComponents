package mock.brains.basecomponents.core.rx;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public final class RxOnSubscribeBoundedCache<T> implements Observable.OnSubscribe<T> {

    @SuppressWarnings("rawtypes")
    static final AtomicIntegerFieldUpdater<RxOnSubscribeBoundedCache> SRC_SUBSCRIBED_UPDATER
            = AtomicIntegerFieldUpdater.newUpdater(RxOnSubscribeBoundedCache.class, "sourceSubscribed");
    protected final Observable<? extends T> source;
    protected final Subject<? super T, ? extends T> cache;
    volatile int sourceSubscribed;

    public RxOnSubscribeBoundedCache(Observable<? extends T> source, int bufferSize) {
        this.source = source;
        this.cache = ReplaySubject.<T>createWithSize(bufferSize);
    }

    @Override
    public void call(Subscriber<? super T> s) {
        if (SRC_SUBSCRIBED_UPDATER.compareAndSet(this, 0, 1)) {
            source.subscribe(cache);
        }
        cache.unsafeSubscribe(s);
    }
}