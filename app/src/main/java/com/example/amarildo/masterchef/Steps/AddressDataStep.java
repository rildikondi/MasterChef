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
import com.example.amarildo.masterchef.ObjectSerializer;
import com.example.amarildo.masterchef.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static android.view.View.FOCUS_DOWN;


public class AddressDataStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;
    View view;
    Switch switch_Button;
    ScrollView fourth_ScrollView;
    //fields of address dropdown
    RelativeLayout addressDropDown_RL;
    LinearLayout  address_LinearLayout;
    LinearLayout addressEntries_LinearLayout;
    ImageView arrow_Image;
    TextView nuovoIndirizzo_TextView;
    TextView savedIndirizzo_TextView;
    TextView dropDownHeader_TextView;
    //field of address
    EditText nomeCitofono_EditText;
    EditText indirizzo_EditText;
    EditText presso_EditText;
    EditText cap_EditText;
    EditText citta_EditText;
    EditText provincia_EditText;
    //fields of fattura address
    EditText nomeAzienda_EditText;
    EditText codeFiscale_EditText;
    EditText partitaIva_EditText;
    EditText indirizzoFattura_EditText;
    EditText cittaFattura_EditText;
    EditText capFattura_EditText;
    EditText provinciaFatturaEditText;
    // strings of person Adress
    String nomeCitofono;
    String indirizzo;
    String presso;
    String cap;
    String citta;
    String provincia;
    //Strings of fattura Adress
    String nomeAzienda;
    String codeFiscale;
    String partitaIva;
    String indirizzoFattura;
    String cittaFattura;
    String capFattura;
    String provinciaFattura;
    // views for fattura dropdown
    RelativeLayout fatturaAddressDropDown_RL;
    TextView dropDownHeader2_TV;
    ImageView arrow2_Image;
    LinearLayout fatturaAddress_LinearLayout;
    TextView nuovoIndirizzoFatt_TextView;
    TextView savedFattIndirizzo_TextView;
    LinearLayout fattComm_LinearLayout;

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
        handleFatturaAdressDropDown();
        updateGraphics();
        return view;
    }

    public void initializeViews(View view){
        fourth_ScrollView = view.findViewById(R.id.fourthScrollView);
        //views for address dropDown and its fields
        addressDropDown_RL = view.findViewById(R.id.addressDropDownRL);
        dropDownHeader_TextView = view.findViewById(R.id.dropDownHeaderTV);
        arrow_Image = view.findViewById(R.id.arrowImage);
        address_LinearLayout = view.findViewById(R.id.addressLinearLayout);
        nuovoIndirizzo_TextView = view.findViewById(R.id.nuovoIndirizzoTextView);
        savedIndirizzo_TextView = view.findViewById(R.id.savedIndirizzoTextView);
        //fileds Linear Layout
        addressEntries_LinearLayout = view.findViewById(R.id.addressEntriesLinearLayout);
        //fields of adress
        nomeCitofono_EditText = view.findViewById(R.id.nomeCitofonoEditText);
        indirizzo_EditText = view.findViewById(R.id.indirizzoEditText);
        presso_EditText = view.findViewById(R.id.pressoEditText);
        cap_EditText = view.findViewById(R.id.capEditText);
        citta_EditText = view.findViewById(R.id.cittaEditText);
        provincia_EditText = view.findViewById(R.id.provinciaEditText);

        switch_Button = view.findViewById(R.id.switchButton);

        final String savedAddress = sharedPreferences.getString("savedAddress", "");
        final String fatturaAddress = sharedPreferences.getString("fattura", "");

        switch_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(!savedAddress.equals(""))
                    {
                        fatturaAddressDropDown_RL.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        expand(fattComm_LinearLayout);
                        fourth_ScrollView.pageScroll(FOCUS_DOWN);
                    }
                }
                else {
                    fatturaAddressDropDown_RL.setVisibility(View.GONE);
                    collapse(fattComm_LinearLayout);
                }
            }
        });

        handleItems(address_LinearLayout);

        //views for fattura dropDown and its fields
        fatturaAddressDropDown_RL = view.findViewById(R.id.fatturaAddressDropDownRL);
        dropDownHeader2_TV = view.findViewById(R.id.dropDownHeader2TV);
        arrow2_Image = view.findViewById(R.id.arrow2Image);
        fatturaAddress_LinearLayout = view.findViewById(R.id.fatturaAddressLinearLayout);
        nuovoIndirizzoFatt_TextView = view.findViewById(R.id.nuovoIndirizzoFattTextView);
        savedFattIndirizzo_TextView = view.findViewById(R.id.savedFattIndirizzoTextView);
        fattComm_LinearLayout = view.findViewById(R.id.fattCommLinearLayout);
        //fields of fattura
        nomeAzienda_EditText = view.findViewById(R.id.nomeAziendaEditText);
        codeFiscale_EditText = view.findViewById(R.id.codeFiscaleEditText);
        partitaIva_EditText = view.findViewById(R.id.partitaIvaEditText);
        indirizzoFattura_EditText = view.findViewById(R.id.indirizzoFatturaEditText);
        cittaFattura_EditText = view.findViewById(R.id.cittaFatturaEditText);
        capFattura_EditText = view.findViewById(R.id.capFatturaEditText);
        provinciaFatturaEditText = view.findViewById(R.id.provinciaFatturaEditText);
        handleFatturaDropDownItems(fatturaAddress_LinearLayout);
    }

    public void handleHeaderOne(){
        addressDropDown_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow_Image.getRotation() == 0 ) {
                    Boolean isAddressSaved = sharedPreferences.getBoolean("savedOrNotAddress", false);
                    if(isAddressSaved){
                        savedIndirizzo_TextView.setVisibility(View.VISIBLE);
                        savedIndirizzo_TextView.setText(sharedPreferences.getString("savedAddress",""));
                    }
                    arrow_Image.animate().rotationBy(180).setDuration(300);
                    expand(address_LinearLayout);
                }
                else if(arrow_Image.getRotation() == 180){
                    arrow_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(address_LinearLayout);
                }
            }
        });
    }

    public void handleFatturaAdressDropDown(){
        fatturaAddressDropDown_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow2_Image.getRotation() == 0 ) {
                    arrow2_Image.animate().rotationBy(180).setDuration(300);
                    expand(fatturaAddress_LinearLayout);

                    Boolean isFatturaSaved = sharedPreferences.getBoolean("savedOrNotFatturaAddress", false);
                    if(isFatturaSaved){
                        savedFattIndirizzo_TextView.setVisibility(View.VISIBLE);
                        savedFattIndirizzo_TextView.setText(sharedPreferences.getString("indirizzoFattura",""));
                    }
                }
                else if(arrow2_Image.getRotation() == 180){
                    arrow2_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(fatturaAddress_LinearLayout);
                }
            }
        });
    }

    @Override
    public int getPageNr() {
        return 3;
    }

    public void initalizeAdressStrings(){
       nomeCitofono = nomeCitofono_EditText.getText().toString().trim();
       indirizzo = indirizzo_EditText.getText().toString().trim();
       presso = presso_EditText.getText().toString().trim();
       cap = cap_EditText.getText().toString().trim();
       citta = citta_EditText.getText().toString().trim();
       provincia = provincia_EditText.getText().toString().trim();
    }

    public void initalizeFatturaAdresStrings(){
       nomeAzienda = nomeAzienda_EditText.getText().toString().trim();
       codeFiscale = codeFiscale_EditText.getText().toString().trim();
       partitaIva = partitaIva_EditText.getText().toString().trim();
       indirizzoFattura = indirizzoFattura_EditText.getText().toString().trim();
       cittaFattura = indirizzoFattura_EditText.getText().toString().trim();
       capFattura = capFattura_EditText.getText().toString().trim();
       provinciaFattura = provinciaFatturaEditText.getText().toString().trim();
    }

    public void saveAdressData(){
        sharedPreferences.edit().putBoolean("savedOrNotAddress", true).apply();
        sharedPreferences.edit().putString("nomeCitofono", nomeCitofono).apply();
        sharedPreferences.edit().putString("savedAddress", indirizzo).apply();
        sharedPreferences.edit().putString("presso", presso).apply();
        sharedPreferences.edit().putString("cap", cap).apply();
        sharedPreferences.edit().putString("citta", citta).apply();
        sharedPreferences.edit().putString("provincia", provincia).apply();
    }

    public void loadIndirizzoData(){
        nomeCitofono_EditText.setText(sharedPreferences.getString("nomeCitofono", ""));
        indirizzo_EditText.setText(sharedPreferences.getString("savedAddress", ""));
        presso_EditText.setText(sharedPreferences.getString("presso", ""));
        cap_EditText.setText(sharedPreferences.getString("cap", ""));
        citta_EditText.setText(sharedPreferences.getString("citta", ""));
        provincia_EditText.setText(sharedPreferences.getString("provincia", ""));
    }

    public void saveFatturaAddressData(){
        sharedPreferences.edit().putString("nomeAzienda", nomeAzienda).apply();
        sharedPreferences.edit().putString("codeFiscale", codeFiscale).apply();
        sharedPreferences.edit().putString("partitaIva", partitaIva).apply();
        sharedPreferences.edit().putString("indirizzoFattura", indirizzoFattura).apply();
        sharedPreferences.edit().putString("cittaFattura", cittaFattura).apply();
        sharedPreferences.edit().putString("capFattura", capFattura).apply();
        sharedPreferences.edit().putString("provinciaFattura", provinciaFattura).apply();
    }

    public void loadFatturaAddressData(){
        nomeAzienda_EditText.setText(sharedPreferences.getString("nomeAzienda",""));
        codeFiscale_EditText.setText(sharedPreferences.getString("codeFiscale",""));
        partitaIva_EditText.setText(sharedPreferences.getString("partitaIva",""));
        indirizzoFattura_EditText.setText(sharedPreferences.getString("indirizzoFattura",""));
        cittaFattura_EditText.setText(sharedPreferences.getString("cittaFattura",""));
        capFattura_EditText.setText(sharedPreferences.getString("capFattura",""));
        provinciaFatturaEditText.setText(sharedPreferences.getString("provinciaFattura",""));
    }

    public boolean validateAdress(){

        if (indirizzo.equals("") || nomeCitofono_EditText.equals("") || presso_EditText.equals("")
                || cap_EditText.equals("") || citta_EditText.equals("") || provincia_EditText.equals(""))
        {
            Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }else
        {
            TextView selectedAddress = address_LinearLayout.findViewWithTag("green");

            if (selectedAddress != null)
            {
                String addressValue = selectedAddress.getText().toString();
                if(addressValue.equals("NUOVO INDIRIZZO")){
                    //saveNuovoAddressData();
                }
                else
                {
                    saveAdressData();
                }
            }
            else
            {
                saveAdressData();
            }
            return true;
        }

    }


    @Override
    public boolean validateStep() {
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,15}";
        initalizeAdressStrings();
        // fields of fattura
        initalizeFatturaAdresStrings();


        if(!switch_Button.isChecked()) {
            return validateAdress();
        }
        else{
            if (!validateAdress()) {
                return false;
            } else if(nomeAzienda.equals("") || codeFiscale.equals("") || partitaIva.equals("")
                    || indirizzoFattura.equals("") || cittaFattura.equals("") || capFattura.equals("")
                    || provinciaFattura.equals("")){
                Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                return false;
            }else{

                TextView selectedFatturaAddress = fatturaAddress_LinearLayout.findViewWithTag("green");

                if (selectedFatturaAddress != null)
                {
                    String addressFatturaValue = selectedFatturaAddress.getText().toString();
                    if(addressFatturaValue.equals("NUOVO INDIRIZZO") && savedFattIndirizzo_TextView.getVisibility() == View.VISIBLE){
                        //saveFatturaAddressData("nuovoFattura");
                    }
                    else
                    {
                        sharedPreferences.edit().putBoolean("savedOrNotFatturaAddress",true).apply();
                        saveFatturaAddressData();
                    }
                }
                else
                {
                    sharedPreferences.edit().putBoolean("savedOrNotFatturaAddress",true).apply();
                    saveFatturaAddressData();
                }
                return true;
            }
        }
    }

    @Override
    public void updateGui() {

        String savedAddress = sharedPreferences.getString("savedAddress", "");

        Boolean isAddressSelected = sharedPreferences.getBoolean("savedOrNotAddress", false);
        Boolean isFatturaSaved = sharedPreferences.getBoolean("savedOrNotFatturaAddress", false);

        if (isAddressSelected && addressDropDown_RL.getVisibility() == View.VISIBLE){
            String indirizzoField =  indirizzo_EditText.getText().toString();
            if(!indirizzoField.equals(""))
            {
                dropDownHeader_TextView.setText(indirizzo_EditText.getText().toString());
                if(address_LinearLayout.getVisibility() == View.VISIBLE){
                    if(savedIndirizzo_TextView.getText().toString().equals("SAVED ADDRESS"))
                        savedIndirizzo_TextView.setText(indirizzo_EditText.getText().toString());
                }
            }
        }
        else if(isAddressSelected && addressDropDown_RL.getVisibility() != View.VISIBLE){
            addressDropDown_RL.setVisibility(View.VISIBLE);
            dropDownHeader_TextView.setText(savedAddress);
        }

        if(isAddressSelected && fatturaAddress_LinearLayout.getVisibility() == View.VISIBLE ){
            view.invalidate();
            fatturaAddressDropDown_RL.setVisibility(View.VISIBLE);

        }

        if (isFatturaSaved && fatturaAddressDropDown_RL.getVisibility() == View.VISIBLE){
           String indirizzoField =  indirizzoFattura_EditText.getText().toString();
           if(!indirizzoField.equals(""))
           {
               dropDownHeader2_TV.setText(indirizzoFattura_EditText.getText().toString());
               if(fatturaAddress_LinearLayout.getVisibility() == View.VISIBLE){
                   if(savedFattIndirizzo_TextView.getText().toString().equals("SAVED ADDRESS"))
                   savedFattIndirizzo_TextView.setText(indirizzoFattura_EditText.getText().toString());
               }
           }
        }
    }

    //updating graphichs on rotate
    public void updateGraphics() {
        String savedAddress = sharedPreferences.getString("savedAddress", "");

        if (!savedAddress.equals("")) {
            addressDropDown_RL.setVisibility(View.VISIBLE);
            addressEntries_LinearLayout.setVisibility(View.GONE);
            savedIndirizzo_TextView.setText(savedAddress);
            address_LinearLayout.setVisibility(View.GONE);
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
                    }
                    arrow_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(address_LinearLayout);
                    expand(addressEntries_LinearLayout);
                    nomeCitofono_EditText.setText("");
                    indirizzo_EditText.setText("");
                    presso_EditText.setText("");
                    cap_EditText.setText("");
                    citta_EditText.setText("");
                    provincia_EditText.setText("");
                    dropDownHeader_TextView.setText(contenti);
                    controlTextView.setTextColor(Color.GREEN);
                    controlTextView.setTag("green");
                }
                else {

                    if(tochange != null){
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                        tochange.setTag("");
                    }
                    controlTextView.setTag("green");
                    controlTextView.setTextColor(Color.GREEN);
                    loadIndirizzoData();
                    arrow_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(address_LinearLayout);
                    expand(addressEntries_LinearLayout);
                    dropDownHeader_TextView.setText(contenti);
                }
            }
        };

        int nrItems = firstItemsLn.getChildCount();
        for (int i = 0; i < nrItems; i++){
            firstItemsLn.getChildAt(i).setOnClickListener(secondItemsListener);
        }
    }

    public void handleFatturaDropDownItems(final LinearLayout fatturaDropDownItemsLn){

        View.OnClickListener secondItemsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView controlTextView = (TextView) v;
                String contenti = (String) controlTextView.getText();
                TextView tochange = (TextView) fatturaDropDownItemsLn.findViewWithTag("green");

                if (contenti.equals("NUOVO INDIRIZZO")) {
                    if (tochange != null) {
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                    }

                    arrow2_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(fatturaAddress_LinearLayout);
                    expand(fattComm_LinearLayout);
                    nomeAzienda_EditText.setText("");
                    codeFiscale_EditText.setText("");
                    partitaIva_EditText.setText("");
                    indirizzoFattura_EditText.setText("");
                    cittaFattura_EditText.setText("");
                    capFattura_EditText.setText("");
                    provinciaFatturaEditText.setText("");
                    dropDownHeader2_TV.setText(contenti);
                    controlTextView.setTextColor(Color.GREEN);
                    controlTextView.setTag("green");

                }
                else {

                    if(tochange != null){
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                        tochange.setTag("");
                    }
                    controlTextView.setTag("green");
                    controlTextView.setTextColor(Color.GREEN);
                    loadFatturaAddressData();
                    arrow2_Image.animate().rotationBy(-180).setDuration(300);
                    collapse(fatturaAddress_LinearLayout);
                    expand(fattComm_LinearLayout);
                    dropDownHeader2_TV.setText(contenti);
                }
            }
        };

        int nrItems = fatturaDropDownItemsLn.getChildCount();
        for (int i = 0; i < nrItems; i++){
            fatturaDropDownItemsLn.getChildAt(i).setOnClickListener(secondItemsListener);
        }
    }
}