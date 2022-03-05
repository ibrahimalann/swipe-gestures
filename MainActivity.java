package alan.software.swipeitemrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import alan.software.swipeitemrecyclerview.databinding.ActivityMainBinding;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<String> arrayList;
    Adapter adapter;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        arrayList=new ArrayList<>();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(arrayList);
        binding.recyclerview.setAdapter(adapter);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recyclerview);
    }

    public void save(View view) {
        data=binding.editText.getText().toString();
        arrayList.add(data);
        adapter.notifyDataSetChanged();
        binding.editText.setText("");
    }
    String deleteData;
    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position=viewHolder.getBindingAdapterPosition();

            deleteData=arrayList.get(position);
            arrayList.remove(position);
            adapter.notifyDataSetChanged();

            Snackbar.make(binding.recyclerview,deleteData,Snackbar.LENGTH_LONG).setAction("Geri Al", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrayList.add(position,deleteData);
                    adapter.notifyDataSetChanged();
                }
            }).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull  RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.deleteColor))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(ContextCompat.getColor(MainActivity.this,R.color.white))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

}