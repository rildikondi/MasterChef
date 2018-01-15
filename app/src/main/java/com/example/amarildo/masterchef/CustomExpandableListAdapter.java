package com.example.amarildo.masterchef;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amarildo on 17-12-27.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private String drawable_name;
    public boolean[] buttons;
    public int lastChecked =  -1;



    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail,String drawable_icon) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.drawable_name = drawable_icon;
        this.buttons = new  boolean[getChildrenCount(0)];
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }


    private static class ChildHolder {
        LinearLayout frame;
        TextView details;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        View myRow = convertView;

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myRow = layoutInflater.inflate(R.layout.list_item, null);



        }else {

            myRow = convertView;
        }

        TextView mTextView = (TextView) myRow.findViewById(R.id.expandedListItem);
        mTextView.setText(expandedListText);


        return myRow;

    }


    public void notifyList() {

        this.notifyDataSetChanged();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        int resId = context.getResources().getIdentifier(drawable_name, "drawable", context.getPackageName());

        ImageView image = convertView.findViewById(R.id.iconIndicator);
        image.setImageResource(resId);

        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.tvHeader);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        if (isExpanded) {
            convertView.findViewById(R.id.imageGroupIndicator).setBackgroundResource(android.R.drawable.arrow_up_float);
        } else {
            convertView.findViewById(R.id.imageGroupIndicator).setBackgroundResource(android.R.drawable.arrow_down_float);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


}
