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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        create = (Button) findViewById(R.id.create);
        genderView = findViewById(R.id.genderView);
        birthey = findViewById(R.id.birdthey);
        name = findViewById(R.id.name);
        fam = findViewById(R.id.fam);
        otch = findViewById(R.id.otche);
        //Выбор пола
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                if (position == 0) {
                    genderView.setText("");
                } else {
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
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                birthey.setText(date);
            }
        };

        //Кнопка перехода
create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name1 = name.getText().toString();
        String fam1 = fam.getText().toString();
        String otch1 = otch.getText().toString();
        String birthey1 = birthey.getText().toString();
        String formattedBirthDate=Utils.formateDateForServer(birthey1);
        String gender1 = genderView.getText().toString();
        PatientData patientData = new PatientData(name1,fam1,otch1,formattedBirthDate,gender1);
        sendPatientDataToServer(patientData);
        Intent intent = new Intent(Karta.this, Katalog.class);
        startActivity(intent);
    }
});
TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
checkFieldsForEmptyValues();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
};
name.addTextChangedListener(textWatcher);
fam.addTextChangedListener(textWatcher);
otch.addTextChangedListener(textWatcher);
birthey.addTextChangedListener(textWatcher);
genderView.addTextChangedListener(textWatcher);
    checkFieldsForEmptyValues();
    }

    private void checkFieldsForEmptyValues() {
    String strEditText1 = name.getText().toString();
    String strEditText2 = fam.getText().toString();
    String strEditText3 = otch.getText().toString();
    String strTextView1 = birthey.getText().toString();
    String strTextView2 = genderView.getText().toString();
    if(strEditText1.isEmpty() || strTextView2.isEmpty() || strTextView1.isEmpty() || strEditText2.isEmpty() || strEditText3.isEmpty()){
        create.setEnabled(false);
    }
    else {
        create.setEnabled(true);
        create.setBackgroundResource(R.drawable.buttonun);
    }
    }
private void sendPatientDataToServer(PatientData patientData){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.createPatient(patientData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Karta.this, "Success", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(Karta.this, "Failed1", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
Toast.makeText(Karta.this, "Failed2",Toast.LENGTH_SHORT);
            }
        });
}

}