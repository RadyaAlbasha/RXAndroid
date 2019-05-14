package android.rxjava.labs.recyclerviewwithobservable.model;


import android.content.Context;
import android.rxjava.labs.recyclerviewwithobservable.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {
    private Context context ;
    private ArrayList<String> namesList;
    private ArrayList<String> capNamesList;

    public NamesAdapter(Context _context ) {
        capNamesList = new ArrayList<>();
        namesList = new ArrayList<>();
        context=_context;
    }

    public NamesAdapter(Context _context , ArrayList<String> _capNamesList ,  ArrayList<String> _namesList){
        context=_context;
        capNamesList=_capNamesList;
        namesList=_namesList;
    }

    public ArrayList<String> getNamesList() {
        return namesList;
    }

    public void setNamesList(ArrayList<String> namesList) {
        this.namesList = namesList;
    }

    public ArrayList<String> getCapNamesList() {
        return capNamesList;
    }

    public void setCapNamesList(ArrayList<String> capNamesList) {
        this.capNamesList = capNamesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String capName = capNamesList.get(i);
        final String name = namesList.get(i);
        myViewHolder.txtCapName.setText(capName);
        myViewHolder.txtName.setText(name);
    }

    @Override
    public int getItemCount() {
            return namesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCapName;
        private TextView txtName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCapName = itemView.findViewById(R.id.textViewCapName);
            txtName = itemView.findViewById(R.id.textViewName);
        }
    }

}

