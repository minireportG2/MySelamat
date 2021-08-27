package com.example.myselamat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class SopAdapter extends FirebaseRecyclerAdapter<SopModel, SopAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SopAdapter(@NonNull FirebaseRecyclerOptions<SopModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SopModel model) {
        holder.name.setText(model.getName());
        holder.sop.setText(model.getSop());
        holder.allowed.setText(model.getAllowed());
        holder.disallowed.setText(model.getDisallowed());

        Picasso.get().load(model.getSurl()).into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sop_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name, sop, allowed, disallowed;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            sop = (TextView) itemView.findViewById(R.id.soptext);
            allowed = (TextView) itemView.findViewById(R.id.allowed);
            disallowed = (TextView) itemView.findViewById(R.id.disallowed);
        }
    }
}
