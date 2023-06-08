package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 public class MyAdapter extends ArrayAdapter<PostData> {

    public MyAdapter(Context context, ArrayList<PostData> postDataArrayList) {
        super(context, R.layout.item, postDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PostData postData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView hospitalName = convertView.findViewById(R.id.hospitalName);

        // Add more TextViews for other data if needed

        hospitalName.setText(postData.getHospitalName());

        // Set other data to corresponding TextViews

        return convertView;
    }
}

