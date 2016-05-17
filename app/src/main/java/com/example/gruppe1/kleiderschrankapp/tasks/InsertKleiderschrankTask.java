package com.example.gruppe1.kleiderschrankapp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.gruppe1.kleiderschrankapp.activities.MainActivity;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;
import com.example.gruppe1.kleiderschrankapp.dao.KleiderschrankDBHelper;
/**
 * Created by Furkan on 17.05.2016.
 */
public class InsertKleiderschrankTask extends AsyncTask<Kleiderschrank, Void, Kleiderschrank> {

    private ProgressDialog dialog;
    private Context ctx;
    private Kleiderschrank kleiderschrank;

    /**
     * Constructor for InsertContactTask
     *
     * @param context the context
     */
    public InsertKleiderschrankTask(Context context) {
        this.ctx = context;
    }

    /**
     * Runs on UI thread before execution of AsyncTask and gets used to display a ProgressDialog
     */
    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(ctx, "", "Please wait...", true);
    }

    /**
     * Runs on background thread and inserts kleiderschrank in database
     *
     * @param params
     * @return row id of the inserted entry or -1 if an error occurred
     */
    @Override
    protected Kleiderschrank doInBackground(Kleiderschrank... params) {
        kleiderschrank = params[0];

        return KleiderschrankDBHelper.getInstance(ctx).insertContact(kleiderschrank);
    }

    /**
     * Runs on UI thread and is used to give user feedback and redirect to ContactsDetailActivity of newly created Contact
     *
     * @param insertedKleiderschrank inserted Contact or null if an error occurred
     */
    @Override
    protected void onPostExecute(Kleiderschrank insertedKleiderschrank) {
        dialog.dismiss();
        if (insertedKleiderschrank == null) {
            Toast.makeText(ctx, "Could not add Kleiderschrank to database.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "Added Kleiderschrank to database.", Toast.LENGTH_SHORT).show();
            Intent detailIntent = new Intent(ctx, MainActivity.class);
//            detailIntent.putExtra(PARCEL_CONTACT, insertedContact);
            ctx.startActivity(detailIntent);
        }
    }
}
