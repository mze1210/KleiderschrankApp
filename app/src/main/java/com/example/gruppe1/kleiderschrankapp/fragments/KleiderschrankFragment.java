package com.example.gruppe1.kleiderschrankapp.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.activities.KleiderschrankAnlegenActivity;
import com.example.gruppe1.kleiderschrankapp.dao.DBHelper;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;

/**
 * A simple {@link Fragment} subclass.
 */
public class KleiderschrankFragment extends Fragment {

    private SimpleCursorAdapter adapter;
    private ListView kleiderschrankList;
    private Cursor kleiderschrankCursor;

    public KleiderschrankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kleiderschrank, container, false);
        initView(view);
        return view;
    }

    public void initView(View view){
        kleiderschrankCursor = DBHelper.getInstance(getContext()).findAllKleiderschrank();
        String[] fromColumns = {DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG, DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_ID};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, kleiderschrankCursor, fromColumns, toViews, 0);
        kleiderschrankList = (ListView) view.findViewById(R.id.kleiderschrankList);
        kleiderschrankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * OnItemClickListener for item in ListView
             * @param parent where the click happened
             * @param view clicked View
             * @param position clicked position
             * @param id row id of the clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"text", Toast.LENGTH_SHORT).show();
            }
        });
        kleiderschrankList.setAdapter(adapter);
    }
}
