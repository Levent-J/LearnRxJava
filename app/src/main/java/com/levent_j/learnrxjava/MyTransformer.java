package com.levent_j.learnrxjava;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by levent_j on 16-8-22.
 */
public class MyTransformer<T> implements Observable.Transformer<Respone<T>,T>{
    @Override
    public Observable<T> call(Observable<Respone<T>> responeObservable) {

        return responeObservable.flatMap(new Func1<Respone<T>, Observable<T>>() {

            @Override
            public Observable<T> call(Respone<T> tRespone) {
                T t = (T) new Student();
                return Observable.just(t);
            }
        });
    }
}
