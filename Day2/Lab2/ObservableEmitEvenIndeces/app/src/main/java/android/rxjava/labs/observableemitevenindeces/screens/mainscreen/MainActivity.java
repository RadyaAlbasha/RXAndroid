package android.rxjava.labs.observableemitevenindeces.screens.mainscreen;

import android.os.Bundle;
import android.rxjava.labs.observableemitevenindeces.R;
import android.rxjava.labs.observableemitevenindeces.model.NumbersAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.IView {

    private static final String TAG = "MainActivity" ;
    private MainContract.IPresnter presenter;
    private RecyclerView namesRecyclerView;
    private ArrayList<Integer> numbersList;

    Observable<int[]> numberObservable;
    int count ;

    public MainActivity() {
        this.numbersList = new ArrayList<>();
        count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        namesRecyclerView = findViewById(R.id.recyclerViewNames);
        namesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        int [] numbersArray = new int[]{2,1,4,5,6,9,12,7,14,10};
        numberObservable = Observable.just(numbersArray);
        numberObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<int[], ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> apply(int[] ints) throws Exception {
                        ArrayList<Integer> resList = new ArrayList<>();
                        for (int i=0 ; i< ints.length ; i++ )
                        {
                            if(i %2==0)
                                resList.add(ints[i]);
                        }
                        return resList;
                    }
                }).subscribe(new Observer<ArrayList<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<Integer> integers) {
                for(int i=0 ;i<integers.size() ;i++) {
                    if (integers.get(i) % 2 == 0)
                        numbersList.add(integers.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                presenter.setNumbers(numbersList);
            }
        });

    }


    @Override
    public void showNames(NumbersAdapter adapter) {
        namesRecyclerView.setAdapter(adapter);
    }
}

