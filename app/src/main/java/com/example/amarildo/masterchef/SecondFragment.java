package com.example.amarildo.masterchef;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    EditText coupon_EditText;

    ListView ricette_ListView;
    private RicettaAdapter ricettaAdapter;
    View v;

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



    // TODO: Rename and change types of parameters
    private int mParam1;


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    public SecondFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(int param1) {

        SecondFragment fragment = new SecondFragment();
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_second, container, false);

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), v);

        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                v.getWindowVisibleDisplayFrame(r);

                int heightDiff = v.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 500) { // if more than 100 pixels, its probably a keyboard...


                    v.setPadding(0,0,0,360);

                }else{
                    v.setPadding(0,0,0,0);
                }
            }
        });



        ricette_ListView = v.findViewById(R.id.ricetteListView);

        ricettaAdapter = new RicettaAdapter(getActivity().getApplicationContext(), R.layout.riccetta_row, riccette);

        if(ricette_ListView != null){
            // mListView.setAdapter(mArrayAdapter);
            ricette_ListView.setAdapter(ricettaAdapter);
        }



        ricette_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.v("PLACE", myStringArray[position]);
                // Log.v("PLACE", myPlacesArray[position].mNameOfPlace);
            }
        });

        int resId = this.getContext().getResources().getIdentifier("rounded_line", "drawable", this.getContext().getPackageName());
        //holder.nameImageView.setImageResource(resId);
        ricette_ListView.setBackgroundResource(resId);

        coupon_EditText = v.findViewById(R.id.couponEditText);



        return v;
    }

}
