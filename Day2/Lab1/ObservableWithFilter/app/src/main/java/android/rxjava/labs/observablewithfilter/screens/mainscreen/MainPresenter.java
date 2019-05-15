package android.rxjava.labs.observablewithfilter.screens.mainscreen;

import android.content.Context;
import android.rxjava.labs.observablewithfilter.model.UsersAdapter;
import android.rxjava.labs.observablewithfilter.model.User;


import java.util.ArrayList;

public class MainPresenter implements MainContract.IPresnter {

    MainContract.IView activity;
    UsersAdapter usersAdapter;


    public MainPresenter( MainContract.IView activity){
        this.activity = activity;
        usersAdapter = new UsersAdapter((Context) activity);
    }

    @Override
    public void setUsers(ArrayList<User> users) {
        usersAdapter.setUsersList(users);
            activity.showNames(usersAdapter);

    }

}
