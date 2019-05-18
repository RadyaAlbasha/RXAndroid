package android.rxjava.labs.instantsearchwithretrofit.service;

import android.rxjava.labs.instantsearchwithretrofit.model.Contact;

import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("contacts.php")
    Call<List<Contact>> getContacts();
    @GET("contacts.php")
    Call<List<Contact>> getContactsRemotly(@Query("search")String name);
}
