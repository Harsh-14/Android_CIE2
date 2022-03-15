package com.fantappstic.fragment_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MyHolder> {
    ArrayList<String> course;
    FragmentManager fr;

    public RecyAdapter(ArrayList<String> course, ArrayList<String> desc,FragmentManager fr) {
        this.course = course;
        this.desc = desc;
        this.fr=fr;
    }

    ArrayList<String> desc;
    @NonNull
    @Override
    public RecyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyAdapter.MyHolder holder, int position) {
        holder.textView.setText(course.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fr.beginTransaction().replace(R.id.wrapper,new DescFragment(desc.get(position).toString())).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
