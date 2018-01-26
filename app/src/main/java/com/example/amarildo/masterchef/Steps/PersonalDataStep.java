package com.example.amarildo.masterchef.Steps;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amarildo.masterchef.BaseFragment;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;


public class PersonalDataStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";

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

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }


    private int mParam1;
    View view;

    private EditText nome_EditText;
    private EditText cognome_EditText;
    private EditText nomeUtente_EditText;
    private EditText password_EditText;
    private EditText passwordC_EditText;
    private EditText email_EditText;
    private EditText cell_EditText;
    private EditText telefono_EditText;

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

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), view);

        handleViewTreeObserver(view);

        nome_EditText  = view.findViewById(R.id.nomeEditText);
        cognome_EditText = view.findViewById(R.id.cognomeEditText);
        nomeUtente_EditText = view.findViewById(R.id.nomeUtenteEditText);
        email_EditText = view.findViewById(R.id.emailEditText);
        password_EditText = view.findViewById(R.id.passwordEditText);
        passwordC_EditText = view.findViewById(R.id.passwordCEditText);
        cell_EditText = view.findViewById(R.id.cellEditText);
        telefono_EditText = view.findViewById(R.id.telefonoEditText);


        //validate();


        return view;
    }

    public boolean validate(){
        /*final String nome = nome_EditText.getText().toString().trim();
        final String cognome = cognome_EditText.getText().toString().trim();
        final String nomeUtente = nomeUtente_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String password = password_EditText.getText().toString().trim();
        final String passwordC = passwordC_EditText.getText().toString().trim();
        final String cell = cell_EditText.getText().toString().trim();
        final String tel = telefono_EditText.getText().toString().trim();
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,15}";

        if(nome.equals("") || email.equals("") || cognome.equals("") || nomeUtente.equals("")
                || email.equals("") || password.equals("") || passwordC.equals("") ||cell.equals("") ){

            Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
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
        else{
            return true;
        }*/

        return true;
    }


}
