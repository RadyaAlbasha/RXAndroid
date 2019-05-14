package android.rxjava.labs.recyclerviewwithobservable.screens.mainscreen;

import android.rxjava.labs.recyclerviewwithobservable.model.NamesAdapter;

import java.util.ArrayList;

public interface MainContract {
    public interface IPresnter{
        void setNames(ArrayList<String> names);
        void setCapNames(ArrayList<String> capNames);

    }
    public interface IView{
        void showNames(NamesAdapter adapter);
    }
}
