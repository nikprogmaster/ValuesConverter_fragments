package com.example.valuesconverter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ViewHolder> {

    private OnValueClickListener onValueClickListener;
    private List<Values> values;

    ValuesAdapter(OnValueClickListener onValueClickListener){
        this.onValueClickListener = onValueClickListener;
    }

    public void setValues (List<Values> values){

        this.values = values;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view, onValueClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(values.get(position));
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView value;
        private OnValueClickListener onValueClickListener;
        private View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValueClickListener.OnValueClick((Values) v.getTag());
            }
        };

        public ViewHolder(@NonNull View itemView, OnValueClickListener listener) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            onValueClickListener = listener;
            itemView.setOnClickListener(itemClickListener);

        }

        public void bind (Values val){
            value.setText(val.getTitleStringResourceId());
            itemView.setTag(val);
        }
    }

    public interface OnValueClickListener {
        void OnValueClick(Values value);
    }
}
