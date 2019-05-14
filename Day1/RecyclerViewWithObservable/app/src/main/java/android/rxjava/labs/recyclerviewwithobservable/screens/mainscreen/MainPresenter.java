package android.rxjava.labs.recyclerviewwithobservable.screens.mainscreen;

import android.content.Context;
import android.rxjava.labs.recyclerviewwithobservable.model.NamesAdapter;

import java.util.ArrayList;

public class MainPresenter implements MainContract.IPresnter {

    MainContract.IView activity;
    NamesAdapter namesAdapter;

    boolean namesFlag;
    boolean capNamesFlag;

    public MainPresenter( MainContract.IView activity){
        this.activity = activity;
        namesAdapter = new NamesAdapter((Context) activity);
        namesFlag = false;
        capNamesFlag = false;
    }

    @Override
    public void setNames(ArrayList<String> names) {
        namesAdapter.setNamesList(names);
        namesFlag = true;
        if(namesFlag && capNamesFlag)
            activity.showNames(namesAdapter);

    }

    @Override
    public void setCapNames(ArrayList<String> capNames) {
        namesAdapter.setCapNamesList(capNames);
        capNamesFlag = true;
        if(namesFlag && capNamesFlag)
            activity.showNames(namesAdapter);
    }

}
