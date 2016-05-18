package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.activities.ActivityKleiderschrankAnlegen;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema.KleiderschrankEntry;
import com.example.gruppe1.kleiderschrankapp.dao.KleiderschrankDBHelper;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;


public class FragmentKleiderschrank extends Fragment {

    private static final int SQLITE_LOADER = 0;

    private SimpleCursorAdapter adapter;
    private ListView kleiderschrankList;
    private SearchView searchField;
    private int backButtonCount = 0;

    public FragmentKleiderschrank() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kleiderschrank, container, false);
        FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.addKleiderschrankButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            /**
             * OnClickListener for the add button navigates to CreateContactAcitivy
             * @param v clicked View
             */
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(getActivity(), ActivityKleiderschrankAnlegen.class);
                startActivity(createIntent);
            }
        });


        return view;
    }

    /**
     * Used to initialize the CursorAdapter and layout of the ListView
     */
    private void initAdapter() {
        String[] fromColumns = {KleiderschrankEntry.COLUMN_NAME_ID, KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null, fromColumns, toViews, 0);
        kleiderschrankList = (ListView) getActivity().findViewById(R.id.KleiderschrankList);
        //TODO
//        kleiderschrankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            /**
//             * OnItemClickListener for item in ListView
//             * @param parent where the click happened
//             * @param view clicked View
//             * @param position clicked position
//             * @param id row id of the clicked item
//             */
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
//                Intent detailIntent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
//                detailIntent.putExtra(PARCEL_CONTACT, ContactMapper.map(cursor));
//                startActivity(detailIntent);
//            }
//        });
//        kleiderschrankList.setAdapter(adapter);
    }

    /**
     * Instantiate a new Loader used to retrieve data from SQLite automatically
     *
     * @param loaderID id of the loader
     * @param args     additional arguments
     * @return new instance of Loader
     */
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
        switch (loaderID) {
            case SQLITE_LOADER:
                final String sortOrder = KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG + " ASC";
                return new CursorLoader(getContext(), null, null, null, null, sortOrder) {
                    /**
                     * Loads contacts from SQLite database
                     * @return cursor with database rows
                     */
                    @Override
                    public Cursor loadInBackground() {
                        return KleiderschrankDBHelper.getInstance(getContext().getApplicationContext()).findAllKleiderschrank();
                    }
                };
            default:
                return null;
        }
    }

    /**
     * Sets retrieved Cursor to SimpleCursorAdapter when database query is finished
     * @param loader finished Loader
     * @param data Cursor with data
     */
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
    }

    /**
     * Gets called when Loader gets reset
     * @param loader Loader to reset
     */
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }

    /**
     * Gets called when user presses back button on device and is used to close the application
     */
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            backButtonCount = 0;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getContext(), "Press again to exit the application", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }


}