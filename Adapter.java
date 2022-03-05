package alan.software.swipeitemrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alan.software.swipeitemrecyclerview.databinding.RecyclerviewRowBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.DataHolder> {

    private ArrayList<String> arrayList;

    public Adapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewRowBinding recyclerviewRowBinding=RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DataHolder(recyclerviewRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.DataHolder holder, int position) {
        holder.binding.dataTextview.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder{
        private RecyclerviewRowBinding binding;
        public DataHolder(RecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
