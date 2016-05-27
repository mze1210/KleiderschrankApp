package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;



class CustomAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;


    CustomAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.custom_row_klamotte, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView kleiderschrankTextView = (TextView) view.findViewById(R.id.kleiderschrankTextView);
        TextView kategorieTextView = (TextView) view.findViewById(R.id.kategorieTextView);
        TextView tagsTextView = (TextView) view.findViewById(R.id.tagsTextView);
        ImageView klamotteImageView = (ImageView) view.findViewById(R.id.klamotteImageView);

        String kleiderschrank = cursor.getString(cursor.getColumnIndex(DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG));
        kleiderschrankTextView.setText(kleiderschrank);

        kategorieTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseSchema.KlamotteEntry.COLUMN_NAME_KATEGORIE_FK)));

        tagsTextView.setText("blablabla");

        klamotteImageView.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(DatabaseSchema.KlamotteEntry.COLUMN_NAME_IMAGE_PATH))));


    }
}
