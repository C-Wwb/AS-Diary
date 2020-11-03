package com.example.fauziahrizky.diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fauziahrizky.diary.Database.SqliteDatabase;
import com.example.fauziahrizky.diary.R;

import java.util.Date;

public class AddDataActivity extends AppCompatActivity {
    EditText authorEt,subjectEt,descriptionEt;
    Button cancelBt,saveBt,shareBt;
    SqliteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mydb = new SqliteDatabase(this);

        authorEt = findViewById(R.id.authorEditTextId);
        subjectEt = findViewById(R.id.subjectEditTextId);
        descriptionEt = findViewById(R.id.descriptionEditTextId);

        cancelBt = findViewById(R.id.cacelButtonId);
        saveBt = findViewById(R.id.saveButtonId);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                backToMain();
            }
        });

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });

    }

    //for inserting new data
    public void insertData(){
        long l = -1;

        Date date = new Date();
        String d = (String) android.text.format.DateFormat.format("dd/MM/yyyy  hh:mm:ss",date);

        if(subjectEt.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"You didn't add any subject",Toast.LENGTH_SHORT).show();
        }
        else{
            l = mydb.insertData(authorEt.getText().toString(),subjectEt.getText().toString(),
                    descriptionEt.getText().toString(),
                    d);
        }

        if(l>=0){
            Toast.makeText(getApplicationContext(),"Data added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
        }
    }
    public void backToMain()
    {
        Intent intent = new Intent(AddDataActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}