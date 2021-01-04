package com.example.testapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.NetworkModel.TestResponse;
import com.example.testapp.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<TestResponse.Data> list;
    private Activity context;

    public ItemAdapter(List<TestResponse.Data> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        String name = list.get(position).name;
        String city = list.get(position).capital;
        String currency = list.get(position).currency;

        holder.setData(name, city, currency);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<TestResponse.Data> newList){
        list = newList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, city, currency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameID);
            city = itemView.findViewById(R.id.cityID);
            currency = itemView.findViewById(R.id.currencyID);
        }

        public void setData(String name, String city, String currency){
            this.name.setText(name);
            this.city.setText("Capital : "+city);
            this.currency.setText("Currency : "+currency);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
