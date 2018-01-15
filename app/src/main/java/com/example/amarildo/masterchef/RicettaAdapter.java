package com.example.amarildo.masterchef;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by amarildo on 17-12-28.
 */

public class RicettaAdapter extends ArrayAdapter<Ricetta> {

    Context mContext;
    int mLayoutResourceId;
    Ricetta mData[] = null;

    // resource -> row

    public RicettaAdapter(Context context, int resource, Ricetta[] data){

        super(context, resource, data);

        this.mContext = context;
        this.mLayoutResourceId =  resource;
        this.mData = data;
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
            }else
            {
                row = inflater.inflate(R.layout.linear_row, parent, false);

            }
        }else{
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
            holder.name_Button.setTag("green");
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

                v.setTag("red");
                 v.setBackgroundColor(Color.RED);
            }
            else
            {
                v.setTag("green");
                v.setBackgroundColor(Color.parseColor("#32CD32"));
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
