package mock.brains.basecomponents.core.rx;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

public class RxBus<T> {

    // TODO: AlexTsymbal Think about initialization
    private final SerializedSubject<T, T> rxBus = new SerializedSubject<>(PublishSubject.create());
    private final SerializedSubject<T, T> delayedRxBus = new SerializedSubject<>(BehaviorSubject.<T>create());

    public <E extends T> void post(E event) {
        rxBus.onNext(event);
    }

    public <E extends T> void postDelay(E event) {
        delayedRxBus.onNext(event);
    }

    public Observable<T> observeEvent() {
        return rxBus;
    }

    public Observable<T> observeDelayedEvent() {
        return delayedRxBus;
    }

    public <E extends T> Observable<E> observeEvent(Class<E> eventClass) {
        return rxBus.ofType(eventClass);
    }

    @SafeVarargs
    public final <E extends T> Observable<Object> observeEvents(Class<E>... classArray) {
        ArrayList<Observable<T>> eventClassList = new ArrayList<>();
        for (Class clazz : classArray) {
            eventClassList.add(this.observeEvent(clazz));
        }
        return Observable.merge(eventClassList);
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }
}
