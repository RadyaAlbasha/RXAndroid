package android.rxjava.labs.observableemitevenindeces.model;


import android.content.Context;

import android.rxjava.labs.observableemitevenindeces.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    private Context context ;
    private ArrayList<Integer> numbersList;

    public NumbersAdapter(Context _context ) {
        numbersList = new ArrayList<>();
        context=_context;
    }

    public NumbersAdapter(Context _context , ArrayList<Integer> _usersList){
        context=_context;
        numbersList =_usersList;

    }

    public ArrayList<Integer> getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(ArrayList<Integer> numbersList) {
        this.numbersList = numbersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Integer num = numbersList.get(i);
        myViewHolder.txtNumber.setText(num.toString());
        //myViewHolder.txtGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
            return numbersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNumber;
        private TextView txtGender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.textViewNumber);
            txtGender = itemView.findViewById(R.id.textViewGender);
        }
    }

}

