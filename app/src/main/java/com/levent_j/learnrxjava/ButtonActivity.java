package com.levent_j.learnrxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.setEnabled(false);
                Observable
                        .just(longRunningOperation())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                                       @Override
                                       public void call(String s) {
                                           Snackbar.make(view, "S is"+s, Snackbar.LENGTH_LONG)
                                                   .setAction("Action", null).show();
                                       }
                                   }
                                , new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                    }
                                },
                                new Action0() {
                                    @Override
                                    public void call() {
                                        view.setEnabled(true);
                                    }
                                });


            }
        });
    }

    private String longRunningOperation(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK!";
    }

}
