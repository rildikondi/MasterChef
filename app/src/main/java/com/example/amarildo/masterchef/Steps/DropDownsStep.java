package com.example.amarildo.masterchef.Steps;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.MainActivity;
import com.example.amarildo.masterchef.R;

public class DropDownsStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    private long idPositionType = -1;
    private int temp;
    private View view;
    private final int NUMBER_OF_PAGE = 0;
    LinearLayout firstItems_LinearLayout;
    LinearLayout secondItems_LinearLayout;
    LinearLayout thirdItems_LinearLayout;

    RelativeLayout header_RelativeLayout;
    RelativeLayout header2_RelativeLayout;
    RelativeLayout header3_RelativeLayout;

    ImageView arrow;
    ImageView arrow1;
    ImageView arrow2;

    View _lastColored = null;
    SharedPreferences sharedPreferences;



    @Override
    public int getPageNr() {
        return NUMBER_OF_PAGE;
    }

    @Override
    public void updateGui() {

    }

    public DropDownsStep() {
        // Required empty public constructor
    }

    public static DropDownsStep newInstance(int param1) {

        DropDownsStep fragment = new DropDownsStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("onCreate", "DropDownCreated");
        Bundle bundle = new Bundle();
        bundle.putString("y", "koko"); //any string to be sent
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.saveData(NUMBER_OF_PAGE, bundle);

        sharedPreferences = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);

        if (savedInstanceState != null)
        {
            sharedPreferences.edit().putInt("secondSelectedNr", savedInstanceState.getInt("secondSelectedNr")).apply();
            sharedPreferences.edit().putInt("thirdSelectedNr", savedInstanceState.getInt("thirdSelectedNr")).apply();
        }
        else
        {
            sharedPreferences.edit().remove("secondSelectedNr").commit();
            sharedPreferences.edit().remove("thirdSelectedNr").commit();
        }
    }

    @Override
    public void setArguments(Bundle args) {

        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_first, container, false);
        Log.i("selection", "Shared secondSelectedNr "+ sharedPreferences.getInt("secondSelectedNr", 0));
        Log.i("selection", "Shared thirdSelectedNr "+ sharedPreferences.getInt("thirdSelectedNr", 0));

        // arrow reference
        arrow = (ImageView) view.findViewById(R.id.imageGroupIndicator);
        arrow1 = (ImageView) view.findViewById(R.id.imageGroupIndicator1);
        arrow2 = (ImageView) view.findViewById(R.id.imageGroupIndicator2);

        header_RelativeLayout = view.findViewById(R.id.headerRelativeLayout);
        firstItems_LinearLayout = view.findViewById(R.id.firstItemsLinearLayout);
        handleHeaderOne();
        handleItems(firstItems_LinearLayout, "firstSelected");

        header2_RelativeLayout = view.findViewById(R.id.header2RelativeLayout);
        secondItems_LinearLayout = view.findViewById(R.id.secondItemsLinearLayout);
        handleHeaderTwo();
        handleItems(secondItems_LinearLayout, "secondSelectedNr");

        header3_RelativeLayout = view.findViewById(R.id.header3RelativeLayout);
        thirdItems_LinearLayout = view.findViewById(R.id.thirdItemsLinearLayout);
        handleHeaderThree();
        handleItems(thirdItems_LinearLayout, "thirdSelectedNr");

        if(savedInstanceState != null && savedInstanceState.getInt("firstSelected") != 0){

            Log.i("selection", "Bundle thirdSelectedNr "+ savedInstanceState.getInt("thirdSelectedNr"));
            TextView t  = firstItems_LinearLayout.findViewById(savedInstanceState.getInt("firstSelected"));
            t.setTextColor(Color.GREEN);
            t.setTag("green");
            expand(firstItems_LinearLayout);
            arrow.animate().rotationBy(180);
        }
        else {

            expand(firstItems_LinearLayout);
            arrow.animate().rotationBy(180);
            TextView exampleSelected = (TextView) firstItems_LinearLayout.getChildAt(0);
            exampleSelected.setTextColor(Color.GREEN);
            exampleSelected.setTag("green");
        }

        if(savedInstanceState != null && savedInstanceState.getInt("secondSelected") != 0){

            TextView t1  = secondItems_LinearLayout.findViewById(savedInstanceState.getInt("secondSelected"));
            t1.setTextColor(Color.GREEN);
            t1.setTag("green");
            int vauleOfItem = savedInstanceState.getInt("secondSelectedNr");
            sharedPreferences.edit().putInt("secondSelectedNr",vauleOfItem);
        }

        if(savedInstanceState != null && savedInstanceState.getInt("thirdSelected") != 0){

            TextView t2  = thirdItems_LinearLayout.findViewById(savedInstanceState.getInt("thirdSelected"));
            t2.setTextColor(Color.GREEN);
            t2.setTag("green");
            int vauleOfItem = savedInstanceState.getInt("thirdSelectedNr");
            sharedPreferences.edit().putInt("thirdSelectedNr",vauleOfItem);
        }

        return view;
    }

    public void handleHeaderOne(){

        header_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow.getRotation() == 0 ) {

                    arrow.animate().rotationBy(180).setDuration(300);
                    expand(firstItems_LinearLayout);

                    if(arrow1.getRotation() == 180 ){

                        collapse(secondItems_LinearLayout);
                        arrow1.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow2.getRotation() == 180 ){

                        collapse(thirdItems_LinearLayout);
                        arrow2.animate().rotationBy(-180).setDuration(300);
                    }

                }
                else if(arrow.getRotation() == 180){

                    arrow.animate().rotationBy(-180).setDuration(300);
                    collapse(firstItems_LinearLayout);
                }
            }
        });
    }

    public void handleHeaderTwo(){

        header2_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow1.getRotation() == 0 ) {

                    arrow1.animate().rotationBy(180).setDuration(300);
                    expand(secondItems_LinearLayout);

                    if(arrow.getRotation() == 180 ){

                        collapse(firstItems_LinearLayout);
                        arrow.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow2.getRotation() == 180 ){

                        collapse(thirdItems_LinearLayout);
                        arrow2.animate().rotationBy(-180).setDuration(300);
                    }

                }else if(arrow1.getRotation() == 180){

                    arrow1.animate().rotationBy(-180).setDuration(300);
                    collapse(secondItems_LinearLayout);
                }
            }
        });
    }

    public void handleHeaderThree(){

        header3_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow2.getRotation() == 0 ) {

                    arrow2.animate().rotationBy(180).setDuration(300);
                    expand(thirdItems_LinearLayout);

                    if(arrow.getRotation() == 180 ){

                        collapse(firstItems_LinearLayout);
                        arrow.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow1.getRotation() == 180 ){

                        collapse(secondItems_LinearLayout);
                        arrow1.animate().rotationBy(-180).setDuration(300);
                    }
                }
                else if(arrow2.getRotation() == 180){

                    arrow2.animate().rotationBy(-180).setDuration(300);
                    collapse(thirdItems_LinearLayout);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView t2 = secondItems_LinearLayout.findViewWithTag("green");
        TextView t3 = thirdItems_LinearLayout.findViewWithTag("green");

        if(firstItems_LinearLayout.findViewWithTag("green")!= null){

            outState.putInt("firstSelected",firstItems_LinearLayout.findViewWithTag("green").getId());
        }
        if(t2 != null){

            outState.putInt("secondSelected",t2.getId());
            int vauleOfItem = Integer.parseInt(Character.toString(t2.getText().charAt(0)));
            outState.putInt("secondSelectedNr",vauleOfItem);
        }
        if(t3 != null){

            outState.putInt("thirdSelected",t3.getId());
            int vauleOfItem = Integer.parseInt(Character.toString(t3.getText().charAt(0)));
            outState.putInt("thirdSelectedNr",vauleOfItem);
        }
    }

    public void handleItems(final LinearLayout firstItemsLn, final String nrSelection){

        View.OnClickListener secondItemsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView controlTextView = (TextView) v;
                String contenti = (String) controlTextView.getText();
                if (contenti.equals("RESET")) {

                    if (firstItemsLn.findViewWithTag("green") != null) {

                        TextView tochange = (TextView) firstItemsLn.findViewWithTag("green");
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                        sharedPreferences.edit().remove(nrSelection).apply();
                    }

                }else {

                    if (firstItemsLn.findViewWithTag("green") != null) {

                        TextView tochange = (TextView) firstItemsLn.findViewWithTag("green");
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                    }

                    TextView mytext = (TextView) v;
                    mytext.setTag("green");
                    mytext.setTextColor(Color.GREEN);

                    if(nrSelection !="firstSelected") {
                        Log.i("myChoise", mytext.getText().charAt(0)+"");

                        sharedPreferences.edit().remove(nrSelection).apply();
                        int vauleOfItem = Integer.parseInt(Character.toString(mytext.getText().charAt(0)));
                        sharedPreferences.edit().putInt(nrSelection, vauleOfItem).apply();
                    }
                }
            }
        };

        int nrItems = firstItemsLn.getChildCount();
        for (int i = 0; i < nrItems; i++){
            firstItemsLn.getChildAt(i).setOnClickListener(secondItemsListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //deleteSharedData();
    }

    public void deleteSharedData(){
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);

        sharedPreferences2.edit().remove("secondSelectedNr").commit();
        sharedPreferences2.edit().remove("thirdSelectedNr").commit();
    }

    @Override
    public boolean validateStep() {

        int secondSelectedValue = sharedPreferences.getInt("secondSelectedNr",0);
        Log.i("positionArriveInMain", secondSelectedValue+"");

        int thirdSelectedValue = sharedPreferences.getInt("thirdSelectedNr",0);
        Log.i("positionArriveInMain", secondSelectedValue+"");

        if(secondSelectedValue != 0 && thirdSelectedValue != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
