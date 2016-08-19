package com.levent_j.learnrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "RxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String[] strings = new String[]{"E","F","G","H"};
//        Observable observable = Observable.just("A","B","C","D");
        List<Student> students = new ArrayList<>();
        for (int i=0;i<3;i++){
            Student student = new Student();
            student.setName("STU"+i);
            List<Course> courses = new ArrayList<>();
            for (int j=0;j<5;j++){
                Course course = new Course();
                course.setName("CLASS"+i+j);
                courses.add(course);
            }
            student.setCourses(courses);
            students.add(student);
        }

        Observable.from(students)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return Observable.from(student.getCourses());
//                    }
//                })
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "ABC";
                    }
                })
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        return s.equals("STU0");
//                    }
//                })
                .subscribe(subscriber);

//        Observable.from(students)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//            @Override
//            public void call(String s) {
//                Log.d(TAG,"this is:"+s);
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Log.d(TAG, "error!");
//            }
//        }, new Action0() {
//            @Override
//            public void call() {
//                Log.d(TAG, "Completed!");
//            }
//        });
    }

//    private Subscriber<String> subscriber = new Subscriber<String>() {
//        @Override
//        public void onCompleted() {
//            Log.d(TAG,"Completed!");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            Log.d(TAG,"error!");
//        }
//
//        @Override
//        public void onNext(String s) {
//            Log.d(TAG,"this is:"+s);
//        }
//    };

//    private Subscriber<Course> subscriber = new Subscriber<Course>() {
//        @Override
//        public void onCompleted() {
//            Log.d(TAG,"Completed!");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onNext(Course course) {
//            Log.d(TAG,"class is"+course.getName());
//        }
//    };

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG,"Completed!");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.d(TAG,"Stu is"+s);
        }
    };


}
