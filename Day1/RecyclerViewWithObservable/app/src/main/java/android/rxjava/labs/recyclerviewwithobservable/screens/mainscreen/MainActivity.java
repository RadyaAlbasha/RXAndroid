package android.rxjava.labs.recyclerviewwithobservable.screens.mainscreen;

import android.rxjava.labs.recyclerviewwithobservable.R;
import android.rxjava.labs.recyclerviewwithobservable.model.NamesAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.IView {
    private static final String TAG = "MainActivity" ;
    private MainContract.IPresnter presenter;

    private RecyclerView namesRecyclerView;
    private ArrayList<String> names;
    private ArrayList<String> capNames;
    Observable<String> nameObservable;

    public MainActivity() {
        this.names = new ArrayList<>();
        this.capNames = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        namesRecyclerView = findViewById(R.id.recyclerViewNames);
        namesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        nameObservable = Observable.just("Radya","Esraa","Nouran","Salma","Menna","Sahar");
        Observer<String> nameObserver= getNameObserver();
        nameObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nameObserver);

        Observer<String> capNameObserver= getCapNameObserver();
        nameObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(capNameObserver);
    }

    private Observer<String> getCapNameObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }
            @Override
            public void onNext(String cn) {
                Log.d(TAG, "Cap Name: " + cn.toUpperCase());
                //add upper case to array
                capNames.add(cn.toUpperCase());

            }
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
                presenter.setCapNames(capNames);
            }
        };
    }

    private Observer<String> getNameObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }
            @Override
            public void onNext(String n) {
                Log.d(TAG, "Name: " + n);
                names.add(n);

            }
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
                presenter.setNames(names);
            }
        };
    }

    @Override
    public void showNames(NamesAdapter adapter) {
            namesRecyclerView.setAdapter(adapter);
    }
}
