package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.activities.ActivityKleiderschrankAnlegen;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;


public class FragmentKleiderschrank extends Fragment {

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
        String[] fromColumns = {DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG};
        int[] toViews = {android.R.id.text1};
        adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null,fromColumns,toViews, 0);
        kleiderschrankList = (android.widget.ListView) getActivity().findViewById(R.id.KleiderschrankList);
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
        kleiderschrankList.setAdapter(adapter);
    }

}