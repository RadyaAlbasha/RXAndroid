package android.rxjava.labs.observableemitevenindeces.screens.mainscreen;

import android.content.Context;
import android.rxjava.labs.observableemitevenindeces.model.NumbersAdapter;

import java.util.ArrayList;

public class MainPresenter implements MainContract.IPresnter {

    MainContract.IView activity;
    NumbersAdapter numbersAdapter;


    public MainPresenter( MainContract.IView activity){
        this.activity = activity;
        numbersAdapter = new NumbersAdapter((Context) activity);
    }

    @Override
    public void setNumbers(ArrayList<Integer> numbers) {
        numbersAdapter.setNumbersList(numbers);
            activity.showNames(numbersAdapter);

    }

}
