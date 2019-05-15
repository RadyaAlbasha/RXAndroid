package android.rxjava.labs.observablewithfilter.screens.mainscreen;

import android.rxjava.labs.observablewithfilter.R;
import android.rxjava.labs.observablewithfilter.model.UsersAdapter;
import android.rxjava.labs.observablewithfilter.model.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.IView {

    private static final String TAG = "MainActivity" ;
    private MainContract.IPresnter presenter;
    private RecyclerView namesRecyclerView;
    private ArrayList<User> usersList;

    public MainActivity() {
        this.usersList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        namesRecyclerView = findViewById(R.id.recyclerViewNames);
        namesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Observable
                .merge(getMaleObservable(), getFemaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<User>() {

                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGender().equals("female");
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
                        usersList.add(user);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        presenter.setUsers(usersList);
                    }
                });
    }

    private Observable<User> getFemaleObservable() {
        String[] names = new String[]{"Passant", "Esraa", "Hasnaa"};
        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user= new User();
            user.setName(name);
            user.setGender("female");
            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user: users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(1000);
                                emitter.onNext(user);
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
}
    private Observable<User> getMaleObservable() {
        String[] names = new String[]{"Ahmed", "Mohammed", "Omar", "Mustafa"};
        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user= new User();
            user.setName(name);
            user.setGender("male");
            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user: users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(500);
                                emitter.onNext(user);
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
    @Override
    public void showNames(UsersAdapter adapter) {
        namesRecyclerView.setAdapter(adapter);
    }
}

