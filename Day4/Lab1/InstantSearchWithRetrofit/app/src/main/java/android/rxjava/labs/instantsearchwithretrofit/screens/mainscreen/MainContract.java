package android.rxjava.labs.instantsearchwithretrofit.screens.mainscreen;

import android.rxjava.labs.instantsearchwithretrofit.model.Contact;
import android.rxjava.labs.instantsearchwithretrofit.model.ContactsAdapter;

import java.util.List;

public interface MainContract {
    public interface IPresnter{
        public void setContacts(List<Contact> contacts);

    }
    public interface IView{
        void showContacts(ContactsAdapter adapter);
    }
}
