package io.dwak.multiitemrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FruitVegAdapter.FruitVegListener{
    private RecyclerView recyclerView;
    private boolean listMode = true;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final FruitVegAdapter adapter = new FruitVegAdapter(this, this);

        String[] fruits = getResources().getStringArray(R.array.fruits);
        String[] veggies = getResources().getStringArray(R.array.vegetables);

        for (String veg : veggies) {
            adapter.addVeg(veg);
        }

        for (String fruit : fruits) {
            adapter.addFruit(fruit);
        }

        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(adapter.getItemViewType(position)){
                    case FruitVegItem.FRUIT:
                        return 1;
                    case FruitVegItem.VEGETABLE:
                        return 3;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_toggle){
            listMode = !listMode;
            recyclerView.setLayoutManager(listMode ? linearLayoutManager : gridLayoutManager);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFruitClicked(String message) {
        showToast("Fruit:" + message);
    }

    @Override
    public void onVeggieClicked(String message) {
        showToast("Veggie:" + message);
    }

    void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
