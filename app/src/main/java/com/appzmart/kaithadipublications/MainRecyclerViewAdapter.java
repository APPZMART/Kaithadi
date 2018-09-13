package com.appzmart.kaithadipublications;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private List<ImageUpload> data;
    private Context context;
    private ItemClickListener mClickListener;

    public MainRecyclerViewAdapter(Context context, List<ImageUpload> data) {
        this.data= data;
        this.context = context;
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.mainactivity_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewAdapter.ViewHolder viewHolder, int i) {

        viewHolder.main_text.setText(data.get(i).getName());
        Glide.with(context).load(data.get(i).getUrl()).into(viewHolder.main_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView main_text;
        private ImageView main_img;
        public ViewHolder(View view) {
            super(view);
            main_text = (TextView)view.findViewById(R.id.main_text);
            main_img = (ImageView) view.findViewById(R.id.main_img);
            itemView.setOnClickListener( (View.OnClickListener) this );
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    ImageUpload getItem(int id) {
        return data.get( id );
    }
    // allows clicks events to be caught
    void setClickListener(ValueEventListener itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
        Log.i("TAG", "You clicked number ");
    }
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onUpdateNeeded(String updateUrl);
    }

}