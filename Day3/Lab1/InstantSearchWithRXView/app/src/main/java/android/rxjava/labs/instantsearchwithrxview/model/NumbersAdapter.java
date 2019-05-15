package android.rxjava.labs.instantsearchwithrxview.model;


import android.content.Context;
import android.rxjava.labs.instantsearchwithrxview.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    private Context context ;
    private ArrayList<String> dataList;

    public NumbersAdapter(Context _context ) {
        dataList = new ArrayList<>();
        context=_context;
    }

    public NumbersAdapter(Context _context , ArrayList<String> _dataList){
        context=_context;
        dataList =_dataList;

    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setNumbersList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String s = dataList.get(i);
        myViewHolder.txtData.setText(s);
        //myViewHolder.txtGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
            return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtData;
        private TextView txtGender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtData = itemView.findViewById(R.id.textViewNumber);
            txtGender = itemView.findViewById(R.id.textViewGender);
        }
    }

}

