package com.example.efnangul.firebasedatabase;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.UserModelHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";

    private ArrayList<UserModel> mDataset;
    private final myItemClickListener ıtemClickListener;

    public class UserModelHolder extends RecyclerView.ViewHolder {
        TextView tv_email;
        TextView tv_name;
        TextView tv_lastName;
        TextView tv_salary;
        TextView tv_department;
        TextView tv_age;
        myItemClickListener listener;
        int position = -1;

        public UserModelHolder(View itemView) {
            super(itemView);
            tv_email = (TextView) itemView.findViewById(R.id.tv_email);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_lastName = (TextView) itemView.findViewById(R.id.tv_lastName);
            tv_salary = (TextView) itemView.findViewById(R.id.tv_salary);
            tv_department = (TextView) itemView.findViewById(R.id.tv_department);
            tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(position);
                }
            });
        }

        public void setListener(myItemClickListener listener) {
            this.listener = listener;
        }
    }

    public MyRecyclerViewAdapter(ArrayList<UserModel> myDataset, myItemClickListener ıtemClickListener) {
        mDataset = myDataset;
        this.ıtemClickListener = ıtemClickListener;
    }

    @Override
    public UserModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        UserModelHolder dataObjectHolder = new UserModelHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(UserModelHolder holder, int position) {
        holder.tv_email.setText(mDataset.get(position).getEmail());
        holder.tv_name.setText(mDataset.get(position).getName());
        holder.tv_lastName.setText(mDataset.get(position).getLastName());
        holder.tv_salary.setText(String.valueOf(mDataset.get(position).getSalary()));
        holder.tv_department.setText(mDataset.get(position).getDepartment());
        holder.tv_age.setText(String.valueOf(mDataset.get(position).getAge()));
        holder.setListener(ıtemClickListener);
        holder.position = position;
    }

    public void addItem(UserModel dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface myItemClickListener {
        public void onItemClick(int position);
    }
}