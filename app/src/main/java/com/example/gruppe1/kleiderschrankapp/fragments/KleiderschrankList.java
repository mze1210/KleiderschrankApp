package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;

/**
 * Created by Furkan on 19.05.2016.
 */
public class KleiderschrankList extends ListFragment implements OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int SQLITE_LOADER = 0;

    String[] countries = new String[]{
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };
    private SimpleCursorAdapter adapter;
    private ListView kleiderschrankList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Creating an array adapter to store the list of countries **/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, countries);

        /** Setting the list adapter for the ListFragment */
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Used to initialize the CursorAdapter and layout of the ListView
     */
    private void initAdapter() {
        String[] fromColumns = {DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_ID, DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, null,fromColumns,toViews, 0);
        kleiderschrankList = (ListView) getActivity().findViewById(R.id.kleiderschrankListView);
//        kleiderschrankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
//                Intent detailIntent = new Intent(getActivity(), ContactDetailActivity.class);
//                detailIntent.putExtra(PARCEL_CONTACT, ContactMapper.map(cursor));
//                startActivity(detailIntent);
//            }
//        });
        kleiderschrankList.setAdapter(adapter);
    }


//    @Override
//    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
//        switch (loaderID) {
//            case SQLITE_LOADER:
//                final String sortOrder = DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_ID + " ASC";
//                return new CursorLoader(getContext(), null, null, null, null, sortOrder) {
//
//                };
//            default:
//                return null;
//        }
//    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
