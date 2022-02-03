package com.nexis.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> selectedItems = new ArrayList<>();
    SearchView searchView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView =findViewById(R.id.search_bar);



        ListView chl = (ListView)findViewById(R.id.checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items = {"Prof. Oak(admin)", "Ash(user)", "Squirtle(pokemon)", "Prof. Kukui(admin)", "Misty(user)", "Wartortle(pokemon)", "Brook(user)", "Charmander(pokemon)", "Bulbasaur(pokemon)"};
        adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_lan, items);
        chl.setAdapter(adapter);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem);
                }
                else
                    selectedItems.add(selectedItem);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainActivity.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainActivity.this.adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void showSelectedItems(View view){
        String items="";
        for(String item:selectedItems){
            items+="-"+item+"\n";
        }
        Toast.makeText(this, "Selected items are: \n"+items, Toast.LENGTH_LONG).show();
    }
}