package com.example.amarildo.masterchef.Steps;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amarildo.masterchef.BaseFragment;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;


public class FifthFragment extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    int nrTotale = 0;

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

      /*  if(getArguments().get("nrPage") != null)
            Log.i("nrPage","nrPage" + getArguments().get("nrPage"));
        if(getArguments().get("secondSelectedNr") != null){

            nrTotale = getArguments().getInt("secondSelectedNr");
            Log.i("nrPage","nrPage" + getArguments().get("nrPage"));
        }*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifth, container, false);
    }

    @Override
    public int getPageNr() {
        return 0;
    }

    @Override
    public boolean validateStep() {
        return false;
    }

    @Override
    public void updateGui() {

    }
}
