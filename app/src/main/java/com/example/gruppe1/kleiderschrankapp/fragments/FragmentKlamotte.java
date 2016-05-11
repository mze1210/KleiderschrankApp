package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gruppe1.kleiderschrankapp.R;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link FragmentKlamotte.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentKlamotte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentKlamotte extends Fragment{

    public FragmentKlamotte() {
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
        return inflater.inflate(R.layout.fragment_klamotte, container, false);
    }

}
