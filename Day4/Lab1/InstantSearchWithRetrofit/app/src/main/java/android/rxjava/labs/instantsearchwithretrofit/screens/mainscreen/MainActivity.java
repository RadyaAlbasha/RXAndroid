package android.rxjava.labs.instantsearchwithretrofit.screens.mainscreen;

import android.rxjava.labs.instantsearchwithretrofit.Const;
import android.rxjava.labs.instantsearchwithretrofit.R;
import android.rxjava.labs.instantsearchwithretrofit.model.Contact;
import android.rxjava.labs.instantsearchwithretrofit.model.ContactsAdapter;
import android.rxjava.labs.instantsearchwithretrofit.service.ApiService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainContract.IView {

    private static final String TAG = "MainActivity" ;
    private MainContract.IPresnter presenter;
    private RecyclerView ContactsRecyclerView;
    private EditText inputSearch;

    private ArrayList<Contact> outList;
    private List<Contact> contactList;
    int count ;

    public MainActivity() {
        this.outList = new ArrayList<>();
        this.contactList = new ArrayList<>();
        count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputSearch =findViewById(R.id.editText);
        presenter = new MainPresenter(this);
        ContactsRecyclerView = findViewById(R.id.recyclerViewContacts);
        ContactsRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        loadJson();
        runRX();
    }


    private void loadJson(){
        int cachSize= 10*1024*1024;//10mp
        Cache cache= new Cache(getCacheDir(),cachSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new Const().BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService request = retrofit.create(ApiService.class);
        Call<List<Contact>> call = request.getContacts();

        call.enqueue(new Callback<List<Contact>>() {

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contactList = response.body();
                presenter.setContacts(contactList);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void runRX() {
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
                        for (int i = 0; i < contactList.size(); i++)
                        {
                            if(contactList.get(i).getName().toLowerCase().contains(textViewTextChangeEvent.text().toString().toLowerCase())){
                                outList.add(contactList.get(i));
                            }
                            else if(contactList.get(i).getPhone().contains(textViewTextChangeEvent.text().toString())){
                                outList.add(contactList.get(i));
                            }
                        }

                        presenter.setContacts(outList);
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void showContacts(ContactsAdapter adapter) {
        ContactsRecyclerView.setAdapter(adapter);
    }
}

