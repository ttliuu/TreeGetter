package com.example.treegetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TreeResult extends AppCompatActivity {
    TextView textLatitude = (TextView)findViewById(R.id.textLatitude);
    TextView textLongitude = (TextView)findViewById(R.id.textLongitude);
    TextView textName = (TextView)findViewById(R.id.textName);
    TextView textLatin = (TextView)findViewById(R.id.textLatin);
    TextView textDescription = (TextView)findViewById(R.id.textDescription);


    @Override
    protected void onStart() {
        super.onStart();
        Intent myIntent = getIntent();
        int loc = myIntent.getIntExtra("location",0);
        Tree a = Tree.treeArray.get(loc);
        textLatitude.setText("" + a.latitude);
        textLongitude.setText("" + a.longitude);
        textName.setText(a.type);
        textLatin.setText(a.latin);
        textDescription.setText(a.description);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_result);
    }
}
