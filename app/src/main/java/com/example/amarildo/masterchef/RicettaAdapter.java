package com.example.amarildo.masterchef;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amarildo on 17-12-28.
 */

public class RicettaAdapter extends ArrayAdapter<Ricetta> {

    Context mContext;
    int mLayoutResourceId;
    Ricetta mData[] = null;
    ArrayList<Integer> savedPositions;
    TextView nrDiRicetteTextView;
    int nrSelezionate;
    int numeroDiRicette;
    // resource -> row

    public RicettaAdapter(Context context, int resource, Ricetta[] data, int numeroDiRicette,
                          ArrayList<Integer> savedPositions, TextView nrDiRicetteTextView){

        super(context, resource, data);

        this.mContext = context;
        this.mLayoutResourceId =  resource;
        this.mData = data;
        this.savedPositions = savedPositions;
        this.nrDiRicetteTextView = nrDiRicetteTextView;
        this.numeroDiRicette = numeroDiRicette;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        return position <= 5 ? 0 : 1;
    }

    // overrride methods of ArrayAdapter
    @Override
    public Ricetta getItem(int position){
        return  super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        RicettaHolder holder = null;

        int type = getItemViewType(position);

        // if we currently dont have a row View to reuse ...
        if(row == null) {
            //Create a new View
            // inflate the layout for a single row
            LayoutInflater inflater = LayoutInflater.from(mContext);

            if(type == 0){

                row = inflater.inflate(mLayoutResourceId, parent, false);
                holder = new RicettaHolder();

                //get a reference to the different view elements we wish to update
                holder.nameTextView = (TextView) row.findViewById(R.id.nomeTextView);
                holder.nameImageView = (ImageView) row.findViewById(R.id.ricettaImageView);
                holder.name_Button = (TextView) row.findViewById(R.id.scegliButton);
                row.setTag(holder);
            }
            else
            {
                row = inflater.inflate(R.layout.linear_row, parent, false);
            }
        }
        else{
            // otherwise use an existing one  -> brings reuseability of code more eficensy on memory
            holder = (RicettaHolder) row.getTag();
        }

        if(type == 0) {

            // get the data from the data arraay
            Ricetta ricetta = mData[position];

            // setup and reuse the same listener for each row
            //holder.imageView.setOnClickListener(PopupListener);
            Integer rowPosition = position;
            holder.nameImageView.setTag(rowPosition);

            // setting the  view to reflect the data we need to display
            holder.nameTextView.setText(ricetta.nomeDiRicette);

            //int resId = mContext.getResources().getIdentifier(ricetta.mNameOfImage, "drawable", mContext.getPackageName());
            //holder.nameImageView.setImageResource(resId);

            if(savedPositions != null){
                nrSelezionate = savedPositions.size();
                nrDiRicetteTextView.setText(""+ nrSelezionate);
                boolean found = false;
                for(int i = 0; i < savedPositions.size(); i++){
                    if(position == savedPositions.get(i)){
                        holder.name_Button.setTag("red");
                        holder.name_Button.setBackgroundColor(Color.RED);
                        found = true;
                    }
                }

                if(found == false){
                    holder.name_Button.setTag("green");
                }

            }else {

                holder.name_Button.setTag("green");

            }

            holder.name_Button.setOnClickListener(chooseListener);


            String img_url = ricetta.mNameOfImage;

            if (!img_url.equals("")) {

                Picasso.with(this.mContext)
                        .load(img_url)
                        .resize(200, 160)
                        .into(holder.nameImageView);
            }
        }

        // returning the row view (because this is called getView after all)
        return row;
    }

    View.OnClickListener chooseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Integer viewPosition = (Integer) v.getTag();

            if(v.getTag() == "green"){

                if(nrSelezionate < numeroDiRicette)
                {
                    v.setTag("red");
                    //sharedPreferences.edit().putInt("clickedRicettaId", v.getId());
                    v.setBackgroundColor(Color.RED);
                    nrSelezionate++;
                    nrDiRicetteTextView.setText(""+ nrSelezionate);
                }else{
                    Toast.makeText(getContext(), "Il tuo box e completo", Toast.LENGTH_SHORT).show();
                }
            }
            else if(v.getTag() == "red")
            {
                v.setTag("green");
                v.setBackgroundColor(Color.parseColor("#32CD32"));
                nrSelezionate--;
                nrDiRicetteTextView.setText(""+ nrSelezionate);
            }

            //Ricetta p = mData[viewPosition];
            //Toast.makeText(getContext(), p.mPopup, Toast.LENGTH_SHORT).show();
        }
    };

    private static class RicettaHolder {

        ImageView nameImageView;
        TextView nameTextView;
        TextView name_Button;
    }

}
