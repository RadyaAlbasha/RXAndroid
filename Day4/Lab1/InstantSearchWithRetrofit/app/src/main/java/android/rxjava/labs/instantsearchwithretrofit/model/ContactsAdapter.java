package android.rxjava.labs.instantsearchwithretrofit.model;


import android.content.Context;
import android.rxjava.labs.instantsearchwithretrofit.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private Context context ;

    // private ArrayList<String> dataList;
   private List<Contact> contactList;

    public ContactsAdapter(Context _context ) {
        contactList = new ArrayList<>();
        context=_context;
    }

    public ContactsAdapter(Context _context , ArrayList<Contact> _contactList){
        context=_context;
        contactList =_contactList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       // final String s = dataList.get(i);
        final Contact contact = contactList.get(i);
        myViewHolder.txtName.setText(contact.getName());
        myViewHolder.txtPhone.setText(contact.getPhone());

        String url = contact.getImage();
        //load image from url
        Glide.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.imageView); //with(myViewHolder.imageViewFlag)
    }

    @Override
    public int getItemCount() {
            return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtPhone;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPhone = itemView.findViewById(R.id.textViewPhone);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}

