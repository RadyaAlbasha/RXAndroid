package android.rxjava.labs.instantsearchwithretrofit.screens.remotescreen;

import android.content.Context;
import android.rxjava.labs.instantsearchwithretrofit.model.Contact;
import android.rxjava.labs.instantsearchwithretrofit.model.ContactsAdapter;

import java.util.List;

public class RemotePresenter implements RemoteContract.IPresnter {

    RemoteContract.IView activity;
    ContactsAdapter contactsAdapter;


    public RemotePresenter(RemoteContract.IView activity){
        this.activity = activity;
        contactsAdapter = new ContactsAdapter((Context) activity);
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        contactsAdapter.setContactList(contacts);
        activity.showContacts(contactsAdapter);

    }

}