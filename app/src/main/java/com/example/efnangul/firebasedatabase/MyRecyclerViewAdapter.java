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
    private static MyClickListener myClickListener;

    public static class UserModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_email;
        TextView tv_name;
        TextView tv_lastName;
        TextView tv_salary;
        TextView tv_department;
        TextView tv_age;

        public UserModelHolder(View itemView) {
            super(itemView);
            tv_email = (TextView) itemView.findViewById(R.id.tv_email);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_lastName = (TextView) itemView.findViewById(R.id.tv_lastName);
            tv_salary = (TextView) itemView.findViewById(R.id.tv_salary);
            tv_department = (TextView) itemView.findViewById(R.id.tv_department);
            tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<UserModel> myDataset) {
        mDataset = myDataset;
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

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}