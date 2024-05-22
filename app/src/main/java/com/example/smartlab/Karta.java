package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Karta extends AppCompatActivity {
    Button create;
    TextView genderView;
    TextView birthey;
    EditText name;
    EditText fam;
    EditText otch;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karta);
        Spinner genderSpinner =findViewById(R.id.gender_spinner);
          create =(Button) findViewById(R.id.create);
          genderView = findViewById(R.id.genderView);
          birthey = findViewById(R.id.birdthey);
          name = findViewById(R.id.name);
          fam = findViewById(R.id.fam);
          otch = findViewById(R.id.otche);
         //Выбор пола
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

 //Дата рождения
birthey.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Karta.this,
                android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
});
mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month +1;
        String date = dayOfMonth +"/"+month +"/"+year;
        birthey.setText(date);
    }
};

        //Кнопка перехода
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFormCompletion();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFormCompletion();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFormCompletion();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void checkFormCompletion(){
        boolean allFieldsFilled = !name.getText().toString().trim().isEmpty()
                && !fam.getText().toString().trim().isEmpty()
                && !otch.getText().toString().trim().isEmpty()
                && !birthey.getText().toString().trim().isEmpty()
                && !genderView.getText().toString().trim().isEmpty();
        create.setEnabled(allFieldsFilled);
        create.setBackgroundResource(R.drawable.buttonun);
    }

    public void create(View view) {
        Intent intent = new Intent(Karta.this, Katalog.class);
        startActivity(intent);
    }
}