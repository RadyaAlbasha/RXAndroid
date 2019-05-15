package android.rxjava.labs.observableemitevenindeces.screens.mainscreen;


import android.rxjava.labs.observableemitevenindeces.model.NumbersAdapter;

import java.util.ArrayList;

public interface MainContract {
    public interface IPresnter{
        public void setNumbers(ArrayList<Integer> numbers);

    }
    public interface IView{
        void showNames(NumbersAdapter adapter);
    }
}
