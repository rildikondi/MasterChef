package com.example.amarildo.masterchef.Steps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;
import com.example.amarildo.masterchef.Ricetta;
import com.example.amarildo.masterchef.RicettaAdapter;

import java.util.ArrayList;


public class SelezioneRicetteStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";
    EditText coupon_EditText;

    TextView nrSelezionate_TextView;
    TextView nrTotale_TextView;
    TextView prezzoTot_TextView;
    TextView nrRicette_TextView;
    int nrTotaleDiRicette;
    int nrRicetteSelezionate;
    SharedPreferences sharedPreferences;
    ListView ricette_ListView;
    private RicettaAdapter ricettaAdapter;
    View view;
    ArrayList<Integer> savedPositions;

    private static Parcelable mListInstanceState = null;

    //initial data
    Ricetta[] riccette = new Ricetta[]{
            new Ricetta("BOCCONCINI DI SPADA IN CREMA DI ZUCCA E FUNGHI","https://www.secondchef.it/images/thumb900_kxYjAF_A000S23.jpg"),
            new Ricetta("FARINATA DI CECI CON VERDURE","https://www.secondchef.it/images/thumb900_0x5XrG_V29_4.jpg"),
            new Ricetta("MEZZE PENNE ALLA MONTAMARA","https://www.secondchef.it/images/thumb900_327H2n_SPP06.jpg"),
            new Ricetta("POLLO CON CURCUMA E TIMO","https://www.secondchef.it/images/thumb900_XnsYZE_S66%201.jpg"),
            new Ricetta("RISOTTO ALLA CREMA DI CECI","https://www.secondchef.it/images/thumb900_xj9pkW_A000P62.jpg"),
            new Ricetta("TOMINO E TAGLIATELLE AL TONNO","https://www.secondchef.it/images/thumb900_pqu07D_A28P53.jpg"),
            new Ricetta("linearlayout","nolink")
    };

    private int mParam1;


    public SelezioneRicetteStep() {
        // Required empty public constructor
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static SelezioneRicetteStep newInstance(int param1) {

        SelezioneRicetteStep fragment = new SelezioneRicetteStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    private void initialize(View v){
        ricette_ListView = v.findViewById(R.id.ricetteListView);
        coupon_EditText = v.findViewById(R.id.couponEditText);
        nrRicette_TextView = v.findViewById(R.id.nrRicetteTextView);
        nrSelezionate_TextView = v.findViewById(R.id.nrSelezionateTextView);
        nrTotale_TextView = v.findViewById(R.id.nrTotaleTextView);
        prezzoTot_TextView = v.findViewById(R.id.prezzoTotTextView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);
        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), v);

        initialize(view);
        if(savedInstanceState != null){

            savedPositions = savedInstanceState.getIntegerArrayList("selectedRicettePos");

            nrTotaleDiRicette = savedInstanceState.getInt("secondSelectedNr");
            nrRicette_TextView.setText("" + nrTotaleDiRicette);
            nrTotale_TextView.setText("" + nrTotaleDiRicette);
            nrSelezionate_TextView.setText(savedPositions.size()+"");
        }

        handleViewTreeObserver(view);
        ricettaAdapter = new RicettaAdapter(getActivity().getApplicationContext(), R.layout.riccetta_row, riccette,
                nrTotaleDiRicette, savedPositions, nrSelezionate_TextView);
        ricette_ListView.setAdapter(ricettaAdapter);


        /*ricette_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView buttoRowTextView = view.findViewWithTag("green");
                //buttoRowTextView.setBackgroundColor(Color.BLUE);
            }
        });*/

        return view;
    }

    @Override
    public int getPageNr() {
        return 1;
    }

    @Override
    public boolean validateStep() {
        int selezionati = Integer.parseInt(Character.toString(nrSelezionate_TextView.getText().charAt(0)));

        int totali = sharedPreferences.getInt("secondSelectedNr",0);
        int diff = totali - selezionati;
        if(diff == 0){
            return true;
        }else{
            Toast.makeText(getContext(), "Seleziona ancora " + diff, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void updateGui() {

        nrTotaleDiRicette = sharedPreferences.getInt("secondSelectedNr", 0);
        savedPositions = null;
        ricettaAdapter = new RicettaAdapter(getActivity().getApplicationContext(), R.layout.riccetta_row, riccette,
                nrTotaleDiRicette, null, nrSelezionate_TextView);
        ricette_ListView.setAdapter(ricettaAdapter);
        ricettaAdapter.notifyDataSetChanged();



        nrSelezionate_TextView.setText(""+0);

        if (nrTotaleDiRicette != 0) {
            nrRicette_TextView.setText("" + nrTotaleDiRicette);
            nrTotale_TextView.setText("" + nrTotaleDiRicette);
        }else{
            nrRicette_TextView.setText("");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

            ArrayList<Integer> selectedRicettePos = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            TextView t = ricette_ListView.getChildAt(i).findViewWithTag("red");
            if(t != null){
                int pos = i;
                selectedRicettePos.add(pos);
            }
        }

        outState.putInt("secondSelectedNr", sharedPreferences.getInt("secondSelectedNr", 0));
        outState.putIntegerArrayList("selectedRicettePos", selectedRicettePos);
    }
}
