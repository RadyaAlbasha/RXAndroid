package android.rxjava.labs.observablewithfilter.model;


import android.content.Context;
import android.rxjava.labs.observablewithfilter.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private Context context ;
    private ArrayList<User> usersList;

    public UsersAdapter(Context _context ) {
        usersList = new ArrayList<>();
        context=_context;
    }

    public UsersAdapter(Context _context , ArrayList<User> _usersList){
        context=_context;
        usersList=_usersList;

    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final User user = usersList.get(i);
        myViewHolder.txtCapName.setText(user.getName());
        myViewHolder.txtGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
            return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCapName;
        private TextView txtGender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCapName = itemView.findViewById(R.id.textViewCapName);
            txtGender = itemView.findViewById(R.id.textViewGender);
        }
    }

}

