package com.example.chamalgunasinghe.emdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements DialogInterface.OnClickListener{

    EditText nameText, posText, retPrice, selPrice;
    Button saveBtn, retBtn;
    AlertDialog ad;

    ArrayList<String> players = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.etName);
        posText = (EditText)findViewById(R.id.etAge);
        retPrice = (EditText)findViewById(R.id.etRet);
        selPrice = (EditText)findViewById(R.id.etSell);


        saveBtn = (Button)findViewById(R.id.bSav);
        retBtn = (Button)findViewById(R.id.bRet);

        final DBAdapter adapter = new DBAdapter(this);

        //EVENTS
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open db
                adapter.openDB();

                //Insert
                long result = adapter.add(nameText.getText().toString(), posText.getText().toString(), retPrice.getText().toString(), selPrice.getText().toString());

                if (result > 0) {
                    nameText.setText("");
                    posText.setText("");
                    retPrice.setText("");
                    selPrice.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }

                //Close db
                adapter.close();
            }
        });


        retBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.clear();


                //Open
                adapter.openDB();

                //Retrive

                /*Cursor c = adapter.getAllPlayers();
                while (c.moveToNext()) {
                    String name = c.getString(1);
                    players.add(name);*/


                Intent i = new Intent(getApplicationContext(), ListClass.class);
                startActivity(i);
                adapter.close();


            }
        });
    }

    //Show LV
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Add data
        int playerNum = players.size();
        String[] names = new String[playerNum];

        for(int i=0;i<playerNum;i++){
            names[i]=players.get(i);
        }

        //Set items
        builder.setItems(names, this);
        ad=builder.create();
        ad.setTitle("Players from the db");
        ad.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int pos) {

        Toast.makeText(getApplicationContext(),players.get(pos),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Log.e(" ","Back pressed!! ");
        this.finish();
    }
}
