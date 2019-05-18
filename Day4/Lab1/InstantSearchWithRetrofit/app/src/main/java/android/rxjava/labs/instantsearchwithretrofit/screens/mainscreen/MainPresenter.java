package android.rxjava.labs.instantsearchwithretrofit.screens.mainscreen;

import android.content.Context;
import android.rxjava.labs.instantsearchwithretrofit.model.Contact;
import android.rxjava.labs.instantsearchwithretrofit.model.ContactsAdapter;

import java.util.List;

public class MainPresenter implements MainContract.IPresnter {

    MainContract.IView activity;
    ContactsAdapter contactsAdapter;


    public MainPresenter( MainContract.IView activity){
        this.activity = activity;
        contactsAdapter = new ContactsAdapter((Context) activity);
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        contactsAdapter.setContactList(contacts);
        activity.showContacts(contactsAdapter);

    }

}
