package android.rxjava.labs.instantsearchwithrxview.screens.mainscreen;


import android.rxjava.labs.instantsearchwithrxview.model.NumbersAdapter;

import java.util.ArrayList;

public interface MainContract {
    public interface IPresnter{
        public void setNumbers(ArrayList<String> dataList);

    }
    public interface IView{
        void showNames(NumbersAdapter adapter);
    }
}
