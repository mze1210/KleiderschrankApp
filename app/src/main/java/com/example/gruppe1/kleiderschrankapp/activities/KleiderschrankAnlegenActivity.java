package com.example.gruppe1.kleiderschrankapp.activities;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.dao.DBHelper;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KleiderschrankAnlegenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kleiderschrank_anlegen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kleiderschrank_anlegen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void saveKleiderschrank() {
        Kleiderschrank kleiderschrank = new Kleiderschrank();
        kleiderschrank.setBezeichnung(getStringValue(R.id.bezeichnungEditText));

        DBHelper dbHelper = DBHelper.getInstance(KleiderschrankAnlegenActivity.this);
        dbHelper.insertKleiderschrank(kleiderschrank);
        CharSequence text = dbHelper.databaseToString();

        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();

    }


    public void onClickHandler(View view) {
        Context context = getApplicationContext();
        Toast toast;
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;

        if (view.getId() == R.id.cancelButton) {
            Intent listIntent = new Intent(this, MainActivity.class);
            startActivity(listIntent);
            text = "Abgebrochen";
        }
        if (view.getId() == R.id.saveButton) {
            saveKleiderschrank();
            Intent listIntent = new Intent(this, MainActivity.class);
            startActivity(listIntent);
            text = "Abgespeichert";
        }
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public String getStringValue(int id) {
        View field = findViewById(id);
        if (field instanceof EditText) {
            EditText textField = (EditText) field;
            return textField.getText().toString();
        }
        return "";
    }

}
