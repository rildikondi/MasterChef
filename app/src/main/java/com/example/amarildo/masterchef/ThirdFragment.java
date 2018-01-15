package com.example.amarildo.masterchef;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ThirdFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private MoveToNextPageListener moveToNextPageListener;

    public interface MoveToNextPageListener{

        void moveNextFragment();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        moveToNextPageListener = null;
    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof ThirdFragment.MoveToNextPageListener) {
            moveToNextPageListener = (ThirdFragment.MoveToNextPageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewNoteListener");
        }

    }


    private int mParam1;
    View view;

    private EditText nome_EditText;
    private EditText cognome_EditText;
    private EditText email_EditText;
    private EditText cell_EditText;
    private EditText telefono_EditText;

    public ThirdFragment() {
        // Required empty public constructor
    }


    public static ThirdFragment newInstance(int param1) {

        ThirdFragment fragment = new ThirdFragment();
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
        view = inflater.inflate(R.layout.fragment_third, container, false);

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), view);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                view.getWindowVisibleDisplayFrame(r);

                int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 500) { // if more than 100 pixels, its probably a keyboard...


                    view.setPadding(0,0,0,360);

                }else{
                    view.setPadding(0,0,0,0);
                }
            }
        });


        nome_EditText  = view.findViewById(R.id.nomeEditText);
        cognome_EditText = view.findViewById(R.id.cognomeEditText);
        email_EditText = view.findViewById(R.id.emailEditText);
        cell_EditText = view.findViewById(R.id.cellEditText);
        telefono_EditText = view.findViewById(R.id.telefonoEditText);


        validate();


        return view;
    }

    public void validate(){
        final String nome = nome_EditText.getText().toString().trim();
        final String cognome = cognome_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String cell = cell_EditText.getText().toString().trim();
        final String tel = telefono_EditText.getText().toString().trim();
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,15}";

        if(nome.equals("") || email.equals("") || cognome.equals("") || cell.equals("") ){

            Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();


        }
        else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {

            Toast.makeText(getActivity(), "E-mail address is not valid!", Toast.LENGTH_SHORT).show();

        }
        else{

            moveToNextPageListener.moveNextFragment();
        }

    }


}
