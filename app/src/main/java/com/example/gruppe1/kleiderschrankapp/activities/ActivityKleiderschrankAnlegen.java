package com.example.gruppe1.kleiderschrankapp.activities;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ActivityKleiderschrankAnlegen extends AppCompatActivity {

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

    public void saveKleiderschrank(){

        Kleiderschrank kleiderschrank = new Kleiderschrank();
        kleiderschrank.setBezeichnung(getStringValue(R.id.bezeichnungEditText));
    }


    /**TODO funktioniert noch nicht
     * Gets called when item from menu gets selected
     *
     * @param item the selected menu item
     * @return true if event was handled successfully
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cancel:
                Intent listIntent = new Intent(this, MainActivity.class);
                startActivity(listIntent);
                return true;
            case R.id.action_save:
                saveKleiderschrank();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSaveClickHandler(View view){
        if(view.getId() == R.id.cancelButton){
            Intent listIntent = new Intent(this, MainActivity.class);
            startActivity(listIntent);
        }
        if(view.getId() == R.id.saveButton){
            saveKleiderschrank();
        }
    }

    public String getStringValue(int id){
        View field = findViewById(id);
        if (field instanceof EditText) {
            EditText textField = (EditText) field;
            return textField.getText().toString();
        }
        return "";
    }

}
