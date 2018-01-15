package com.example.amarildo.masterchef;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FifthFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;



    public FifthFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FifthFragment newInstance(int param1) {
        FifthFragment fragment = new FifthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifth, container, false);
    }

}
