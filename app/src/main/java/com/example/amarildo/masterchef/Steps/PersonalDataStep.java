package com.example.amarildo.masterchef.Steps;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.amarildo.masterchef.BaseFragment;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;


public class PersonalDataStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    private View view;

    private EditText nome_EditText;
    private EditText cognome_EditText;
    private EditText nomeUtente_EditText;
    private EditText password_EditText;
    private EditText passwordC_EditText;
    private EditText email_EditText;
    private EditText cell_EditText;
    private EditText telefono_EditText;

    private LinearLayout passords_RelativeLayout;

    private SharedPreferences sharedPreferences;
    private String nome;
    private String cognome;
    private String nomeUtente;
    private String email;
    private String password;
    private String passwordC;
    private String cell;
    private String tel;


    @Override
    public int getPageNr() {
        return 2;
    }

    @Override
    public boolean validateStep() {

        return validate();
    }

    @Override
    public void updateGui() {

    }

    public PersonalDataStep() {
        // Required empty public constructor
    }

    public static PersonalDataStep newInstance(int param1) {
        PersonalDataStep fragment = new PersonalDataStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_third, container, false);
        handleViewTreeObserver(view);
        sharedPreferences = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), view);
        //handleViewTreeObserver(view);
        initialize(view);
        Boolean isSelected = sharedPreferences.getBoolean("savedOrNotAddress", false);
        String savedAddress = sharedPreferences.getString("savedAddress", "");

        if(isSelected && savedInstanceState == null ){
            loadSavedData();
            passords_RelativeLayout.setVisibility(View.GONE);
        }
        else if(!savedAddress.equals(""))
        {
            loadSavedData();
            passords_RelativeLayout.setVisibility(View.GONE);
        }
        else
        {
            deleteSavedData();
        }
        return view;
    }

    public void initialize(View view){
        passords_RelativeLayout = view.findViewById(R.id.passordsRelativeLayout);
        nome_EditText  = view.findViewById(R.id.nomeEditText);
        cognome_EditText = view.findViewById(R.id.cognomeEditText);
        nomeUtente_EditText = view.findViewById(R.id.nomeUtenteEditText);
        email_EditText = view.findViewById(R.id.emailEditText);
        password_EditText = view.findViewById(R.id.passwordEditText);
        passwordC_EditText = view.findViewById(R.id.passwordCEditText);
        cell_EditText = view.findViewById(R.id.cellEditText);
        telefono_EditText = view.findViewById(R.id.telefonoEditText);
    }

    public void loadSavedData(){
        nome_EditText.setText(sharedPreferences.getString("nome", ""));
        cognome_EditText.setText(sharedPreferences.getString("cognome", ""));
        nomeUtente_EditText.setText(sharedPreferences.getString("nomeUtente", ""));
        cell_EditText.setText(sharedPreferences.getString("cell", ""));
        telefono_EditText.setText(sharedPreferences.getString("tel", ""));
        password_EditText.setText(sharedPreferences.getString("password", ""));
        passwordC_EditText.setText(sharedPreferences.getString("password", ""));
        email_EditText.setText(sharedPreferences.getString("email", ""));
    }

    public void deleteSavedData(){
        sharedPreferences.edit().remove("nome");
        sharedPreferences.edit().remove("cognome");
        sharedPreferences.edit().remove("nomeUtente");
        sharedPreferences.edit().remove("password");
        sharedPreferences.edit().remove("cell");
        sharedPreferences.edit().remove("tel");
        sharedPreferences.edit().remove("email");
    }

    public void saveData(){
        sharedPreferences.edit().putString("nome", nome).apply();
        sharedPreferences.edit().putString("cognome", cognome).apply();
        sharedPreferences.edit().putString("nomeUtente", nomeUtente).apply();
        sharedPreferences.edit().putString("email", email).apply();
        sharedPreferences.edit().putString("password", password).apply();
        sharedPreferences.edit().putString("cell", cell).apply();
        sharedPreferences.edit().putString("tel", tel).apply();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void initializeFieldsStrings(){
        nome = nome_EditText.getText().toString().trim();
        cognome = cognome_EditText.getText().toString().trim();
        nomeUtente = nomeUtente_EditText.getText().toString().trim();
        email = email_EditText.getText().toString().trim();
        password = password_EditText.getText().toString().trim();
        passwordC = passwordC_EditText.getText().toString().trim();
        cell = cell_EditText.getText().toString().trim();
        tel = telefono_EditText.getText().toString().trim();
    }

    public boolean validate(){
        initializeFieldsStrings();
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,15}";

        if(nome.equals("") || email.equals("") || cognome.equals("") || nomeUtente.equals("")
                || email.equals("") || password.equals("") || passwordC.equals("") ||cell.equals("") ){
            Toast.makeText(getActivity(), "Please fill all the fields with *!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {
            Toast.makeText(getActivity(), "E-mail address is not valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.equals(passwordC)){

            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length() < 6){

            Toast.makeText(getActivity(), "Password min is 6 chars", Toast.LENGTH_SHORT).show();
            return false;
        }else if( cell.length() < 10 || cell.length()>15){
            Toast.makeText(getActivity(), "Nr cel between 10-15", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            saveData();
            return true;
        }
    }
}
