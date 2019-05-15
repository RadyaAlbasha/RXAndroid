package android.rxjava.labs.instantsearchwithrxview.screens.mainscreen;

import android.rxjava.labs.instantsearchwithrxview.R;
import android.rxjava.labs.instantsearchwithrxview.model.NumbersAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.IView {

    private static final String TAG = "MainActivity" ;
    private MainContract.IPresnter presenter;
    private RecyclerView namesRecyclerView;
    private EditText inputSearch;

    private ArrayList<String> dataList;
    private ArrayList<String> outList;

    Observable<int[]> numberObservable;
    int count ;

    public MainActivity() {
        this.dataList = new ArrayList<>();
        this.outList = new ArrayList<>();
        count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputSearch =findViewById(R.id.editText);
        presenter = new MainPresenter(this);
        namesRecyclerView = findViewById(R.id.recyclerViewNames);
        namesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        dataList.add("radya");
        dataList.add("sahar");
        dataList.add("reem");
        dataList.add("rana");
        dataList.add("dena");
        dataList.add("salma");
        dataList.add("esraa");
        dataList.add("sara");

        presenter.setNumbers(dataList);
        RxTextView.textChangeEvents(inputSearch)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TextViewTextChangeEvent>() {
                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        Log.i("TEEEEEEEEEEEEEEST", textViewTextChangeEvent.text().toString());
                        outList.clear();
                        // txtSearchString.setText("Query: " + textViewTextChangeEvent.text().toString());
                        for (int i = 0; i < dataList.size(); i++)
                        {
                            if(dataList.get(i).startsWith(textViewTextChangeEvent.text().toString())){
                                outList.add(dataList.get(i));
                            }
                        }
                        presenter.setNumbers(outList);
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public void showNames(NumbersAdapter adapter) {
        namesRecyclerView.setAdapter(adapter);
    }
}

