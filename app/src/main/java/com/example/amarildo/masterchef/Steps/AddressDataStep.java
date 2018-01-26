package com.example.amarildo.masterchef.Steps;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.FOCUS_DOWN;


public class AddressDataStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;

    View view;
    Switch switch_Button;
    LinearLayout fattComm_LinearLayout;
    ScrollView fourth_ScrollView;
    RelativeLayout addressDropDown_RL;
    LinearLayout  address_LinearLayout;
    LinearLayout addressEntries_LinearLayout;
    ImageView arrow_Image;
    TextView nuovoIndirizzo_TextView;
    TextView savedIndirizzo_TextView;
    TextView dropDownHeader_TextView;

    EditText nomeCitofono_EditText;
    EditText indirizzo_EditText;
    EditText presso_EditText;
    EditText cap_EditText;
    EditText citta_EditText;
    EditText provincia_EditText;
    //fields of fattura
    EditText nomeAzienda_EditText;
    EditText codeFiscale_EditText;
    EditText partitaIva_EditText;
    EditText indirizzoFattura_EditText;
    EditText cittaFattura_EditText;
    EditText capFattura_EditText;
    EditText provinciaFatturaEditText;

    SharedPreferences sharedPreferences;

    public AddressDataStep() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);
    }

    public static AddressDataStep newInstance(int param1) {
        AddressDataStep fragment = new AddressDataStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fourth, container, false);
        handleViewTreeObserver(view);
        initializeViews(view);
        handleHeaderOne();
        updateGui();
        return view;
    }

    public void initializeViews(View view){

        addressDropDown_RL = view.findViewById(R.id.addressDropDownRL);
        arrow_Image = view.findViewById(R.id.arrowImage);
        address_LinearLayout = view.findViewById(R.id.addressLinearLayout);
        addressEntries_LinearLayout = view.findViewById(R.id.addressEntriesLinearLayout);
        fattComm_LinearLayout = view.findViewById(R.id.fattCommLinearLayout);
        fourth_ScrollView = view.findViewById(R.id.fourthScrollView);

        nuovoIndirizzo_TextView = view.findViewById(R.id.nuovoIndirizzoTextView);
        savedIndirizzo_TextView = view.findViewById(R.id.savedIndirizzoTextView);
        dropDownHeader_TextView = view.findViewById(R.id.dropDownHeaderTV);

        nomeCitofono_EditText = view.findViewById(R.id.nomeCitofonoEditText);
        indirizzo_EditText = view.findViewById(R.id.indirizzoEditText);
        presso_EditText = view.findViewById(R.id.pressoEditText);
        cap_EditText = view.findViewById(R.id.capEditText);
        citta_EditText = view.findViewById(R.id.cittaEditText);
        provincia_EditText = view.findViewById(R.id.provinciaEditText);
        //fields of fattura
        nomeAzienda_EditText = view.findViewById(R.id.nomeAziendaEditText);
        codeFiscale_EditText = view.findViewById(R.id.codeFiscaleEditText);
        partitaIva_EditText = view.findViewById(R.id.partitaIvaEditText);
        indirizzoFattura_EditText = view.findViewById(R.id.indirizzoFatturaEditText);
        cittaFattura_EditText = view.findViewById(R.id.cittaFatturaEditText);
        capFattura_EditText = view.findViewById(R.id.capFatturaEditText);
        provinciaFatturaEditText = view.findViewById(R.id.provinciaFatturaEditText);

        switch_Button = view.findViewById(R.id.switchButton);
        switch_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    expand(fattComm_LinearLayout);
                    fourth_ScrollView.pageScroll(FOCUS_DOWN);
                } else {
                    collapse(fattComm_LinearLayout);
                }
            }
        });

        handleItems(address_LinearLayout);
    }

    public void handleHeaderOne(){
        addressDropDown_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nuovoAddress = sharedPreferences.getString("nuovoAddress", "");
                Boolean isSelected = sharedPreferences.getBoolean("savedOrNotAddress", false);

                if(arrow_Image.getRotation() == 0 ) {

                    if(!nuovoAddress.equals("") && isSelected == true)
                    {
                        indirizzo_EditText.setText(nuovoAddress);
                    }
                    arrow_Image.animate().rotationBy(180).setDuration(300);
                    expand(address_LinearLayout);
                    expand(addressEntries_LinearLayout);

                }
                else if(arrow_Image.getRotation() == 180){

                    if(!nuovoAddress.equals("") && isSelected == true)
                    {
                        collapse(address_LinearLayout);
                        indirizzo_EditText.setText(nuovoAddress);
                    }
                    arrow_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(address_LinearLayout);
                    collapse(addressEntries_LinearLayout);
                }
            }
        });
    }

    @Override
    public int getPageNr() {
        return 3;
    }

    @Override
    public boolean validateStep() {
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,15}";
        final String nomeCitofono = nomeCitofono_EditText.getText().toString().trim();
        final String indirizzo = indirizzo_EditText.getText().toString().trim();
        final String presso = presso_EditText.getText().toString().trim();
        final String cap = cap_EditText.getText().toString().trim();
        final String citta = citta_EditText.getText().toString().trim();
        final String provincia = provincia_EditText.getText().toString().trim();
        // fields of fattura
        final String nomeAzienda = nomeAzienda_EditText.getText().toString().trim();
        final String codeFiscale = codeFiscale_EditText.getText().toString().trim();
        final String partitaIva = partitaIva_EditText.getText().toString().trim();
        final String indirizzoFattura = indirizzoFattura_EditText.getText().toString().trim();
        final String cittaFattura = indirizzoFattura_EditText.getText().toString().trim();
        final String capFattura = capFattura_EditText.getText().toString().trim();
        final String provinciaFattura = provinciaFatturaEditText.getText().toString().trim();


        if(!switch_Button.isChecked()) {
            if (indirizzo.equals(""))
            {
                Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                return false;
            }else
            {
                TextView selectedAddress = address_LinearLayout.findViewWithTag("green");

                if (selectedAddress != null) {
                    String addressValue = selectedAddress.getText().toString();
                    if(addressValue.equals("NUOVO INDIRIZZO")){

                        sharedPreferences.edit().putString("nuovoAddress",indirizzo).apply();
                        sharedPreferences.edit().putBoolean("savedOrNotAddress", true).apply();
                    }
                }else {
                    sharedPreferences.edit().putString("savedAddress", indirizzo).apply();
                    sharedPreferences.edit().putBoolean("savedOrNotAddress", true).apply();
                }
                return true;
            }
        }
        else{
            if (indirizzo.equals("")) {
                Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                return false;
            } else if(nomeAzienda.equals("") || codeFiscale.equals("") || partitaIva.equals("")
                    || indirizzoFattura.equals("") || cittaFattura.equals("") || capFattura.equals("")
                    || provinciaFattura.equals("")){
                Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    public void updateGui() {

       String savedAddress = sharedPreferences.getString("savedAddress", "");
       String nuovoAddresValue = sharedPreferences.getString("nuovoAddress", "");
        Log.i("savedAddr", savedAddress);

        Boolean isSelected = sharedPreferences.getBoolean("savedOrNotAddress", false);

        if(!savedAddress.equals("") && isSelected == true && nuovoAddresValue.equals("")){
            Log.i("savedAddress", savedAddress);
            view.invalidate();
            addressDropDown_RL.setVisibility(View.VISIBLE);
            addressEntries_LinearLayout.setVisibility(View.GONE);
            savedIndirizzo_TextView.setText(savedAddress);
            savedIndirizzo_TextView.setTag("green");
            savedIndirizzo_TextView.setTextColor(Color.GREEN);
            dropDownHeader_TextView.setText(savedAddress);
            address_LinearLayout.setVisibility(View.GONE);
            indirizzo_EditText.setText(savedAddress);

            if(arrow_Image.getRotation() == 180){
                arrow_Image.animate().rotationBy(-180);
            }
        }
        else if(!savedAddress.equals("") && isSelected == true && !nuovoAddresValue.equals("")){
            view.invalidate();
            addressDropDown_RL.setVisibility(View.VISIBLE);
            if(arrow_Image.getRotation() == 180){
                arrow_Image.animate().rotationBy(-180);

            }
            address_LinearLayout.setVisibility(View.GONE);
            addressEntries_LinearLayout.setVisibility(View.GONE);
            indirizzo_EditText.setText(nuovoAddresValue);
            savedIndirizzo_TextView.setText(savedAddress);
            dropDownHeader_TextView.setText(nuovoAddresValue);
            nuovoIndirizzo_TextView.setTextColor(Color.GREEN);
            nuovoIndirizzo_TextView.setTag("green");

        }
        else if(!savedAddress.equals("")){
            addressDropDown_RL.setVisibility(View.VISIBLE);
            savedIndirizzo_TextView.setText(savedAddress);
            addressEntries_LinearLayout.setVisibility(View.GONE);
            dropDownHeader_TextView.setText("NUOVO INDIRIZZO");

            if(arrow_Image.getRotation() == 180){
                arrow_Image.animate().rotationBy(-180);
                address_LinearLayout.setVisibility(View.GONE);
                addressEntries_LinearLayout.setVisibility(View.GONE);
            }
        }
    }


    public void handleItems(final LinearLayout firstItemsLn){

        View.OnClickListener secondItemsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView controlTextView = (TextView) v;
                String contenti = (String) controlTextView.getText();
                TextView tochange = (TextView) firstItemsLn.findViewWithTag("green");

                if (contenti.equals("NUOVO INDIRIZZO")) {

                    if (tochange != null) {
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));

                        sharedPreferences.edit().remove("nuovoAddress").apply();
                        sharedPreferences.edit().putBoolean("savedOrNotAddress",false).apply();

                        expand(addressEntries_LinearLayout);
                        indirizzo_EditText.setText("");
                        dropDownHeader_TextView.setText(contenti);
                        controlTextView.setTextColor(Color.GREEN);
                        controlTextView.setTag("green");
                    }
                }
                else {

                    if(tochange != null){
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                        tochange.setTag("");
                        sharedPreferences.edit().remove("nuovoAddress").apply();
                    }

                    controlTextView.setTag("green");
                    controlTextView.setTextColor(Color.GREEN);
                    expand(addressEntries_LinearLayout);
                    indirizzo_EditText.setText(contenti);
                    dropDownHeader_TextView.setText(contenti);
                    sharedPreferences.edit().putBoolean("savedOrNotAddress",true).apply();
                }
            }
        };

        int nrItems = firstItemsLn.getChildCount();
        for (int i = 0; i < nrItems; i++){
            firstItemsLn.getChildAt(i).setOnClickListener(secondItemsListener);
        }
    }
}