package com.levent_j.learnrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "RxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable observable = Observable.just("A","B","C","D");
        observable.subscribe(subscriber);
    }

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG,"Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG,"error!");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG,"this is:"+s);
        }
    };


}
