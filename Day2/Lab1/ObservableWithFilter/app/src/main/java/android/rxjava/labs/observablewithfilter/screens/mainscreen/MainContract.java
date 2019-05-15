package android.rxjava.labs.observablewithfilter.screens.mainscreen;



import android.rxjava.labs.observablewithfilter.model.UsersAdapter;
import android.rxjava.labs.observablewithfilter.model.User;

import java.util.ArrayList;

public interface MainContract {
    public interface IPresnter{
        public void setUsers(ArrayList<User> users);

    }
    public interface IView{
        void showNames(UsersAdapter adapter);
    }
}
