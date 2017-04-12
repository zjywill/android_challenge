package news.agoda.com.sample.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public interface SchedulerProvider {
    <T> ObservableTransformer<T, T> applySchedulers();

    Scheduler mainThread();

    Scheduler backgroundThread();
}
