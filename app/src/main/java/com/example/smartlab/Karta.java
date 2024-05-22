package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Karta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karta);
        Spinner genderSpinner =findViewById(R.id.gender_spinner);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button create =(Button) findViewById(R.id.create);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView genderView = findViewById(R.id.genderView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 genderSpinner.setAdapter(adapter);
 genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String selectedGender = parent.getItemAtPosition(position).toString();
         if(position == 0){
             genderView.setText("");
         }
         else{
             genderView.setText(selectedGender);
         }
     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {

     }
 });

    }
}