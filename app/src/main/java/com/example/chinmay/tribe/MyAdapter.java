package com.example.chinmay.tribe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Chinmay on 21-03-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    StorageReference mstorage;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> mDataset = new ArrayList<>();

    ArrayList<descs> villdesc = new ArrayList<>();
    ArrayList<Double> mDataset2 = new ArrayList<>();
    ArrayList<Bitmap> images1 = new ArrayList<>();

    descs d;
    Context context;
    String email;

    public MyAdapter(ArrayList<String> myDataSet,ArrayList<Double> myDataSet2,ArrayList<Bitmap> images,Context c,String e,ArrayList<descs> des) {
        mDataset = myDataSet;
        mDataset2=myDataSet2;
        images1=images;
        email=e;
        villdesc=des;

    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        context=v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        mstorage= FirebaseStorage.getInstance().getReference().child(villdesc.get(position).getGalleryImage().toString());
        Glide.with(context).using(new FirebaseImageLoader()).load(mstorage).into(holder.img1);



        holder.txtHeader.setText(villdesc.get(position).getNameOfVillage());
        holder.txtFooter.setText(String.valueOf(villdesc.get(position).getRating()));
        holder.descrip.setText(villdesc.get(position).getDes());
    //    holder.speciality.setText(villdesc.get(position).getSpeciality());
        holder.img1.setImageBitmap(images1.get(position));

        if(villdesc.get(position).getRating()>=3.5)
        {
            holder.txtFooter.setBackgroundColor(Color.parseColor("#99d312"));
        }
        else if((villdesc.get(position).getRating())>2&&(villdesc.get(position).getRating())<3.5)
        {
            holder.txtFooter.setBackgroundColor(Color.parseColor("#3F51B5"));
        }
        else
        {
            holder.txtFooter.setBackgroundColor(Color.parseColor("#df3838"));

        }


        holder.txtHeader.setText( villdesc.get(position).getNameOfVillage());
    }

    @Override
    public int getItemCount() {
        return villdesc.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView img1;
        public  TextView speciality;
        public  TextView descrip;


        public MyViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.name);
            txtFooter = (TextView) v.findViewById(R.id.textView);
            img1=(ImageView)v.findViewById(R.id.image);
      //      speciality=(TextView)v.findViewById(R.id.speciality);
            descrip=(TextView)v.findViewById(R.id.name2);

        }


    }


}
