package com.example.chamalgunasinghe.emdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Chamal Gunasinghe on 2/2/2016.
 */
public class ListClass extends Activity{

    ListView list;

    ArrayList<String> details = new ArrayList<String>();
    AlertDialog ad;
    ArrayAdapter mForcastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_resource);

        final DBAdapter adapter = new DBAdapter(this);
        adapter.openDB();

        Cursor c = adapter.getAllPlayers();
        while(c.moveToNext()) {
            String ECDescription = c.getString(1);
            details.add(ECDescription);
            Arrays.asList(details);

            mForcastAdapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.list_item_forcast,
                    R.id.list_item_forecast_textview,
                    details);

            ListView listView = (ListView) findViewById(
                    R.id.listview_forecast);
            listView.setAdapter(mForcastAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), details.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

}
