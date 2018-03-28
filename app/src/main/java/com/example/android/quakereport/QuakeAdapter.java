package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;
/**
 * Created by akhilamadari on 12/16/17.
 */

public class QuakeAdapter extends ArrayAdapter <Quake> {

    public QuakeAdapter(@NonNull Context context, ArrayList<Quake> quake) {
        super(context, 0,  quake );
    }

    private String DateFormat(Long timeInMs){
        Date dateObject = new Date(timeInMs);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(("LLL dd, yyyy"));
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }
    private String formatTime(Long timeInMs) {
        Date dateObject = new Date(timeInMs);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int splitLocation(String location){
        int a =0;
        if(location.contains(" of")){
             a = location.indexOf(" of");
            return a;
        }
        else return -1;

    }

    private String DecimalFormat(double mag){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(mag);

        return output;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quake pos = getItem( position);
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }


        TextView n = (TextView) listItemView.findViewById(R.id.magnitude);

        n.setText(DecimalFormat(pos.gettMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) n.getBackground();
        //int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(pos.gettMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String s = pos.getTparameter2();
        int k = splitLocation(s);
        String sec ="";
        String primary ="";
        if (k>-1) {
            sec = s.substring(0, k + 3);
            primary = s.substring(k + 4);
        }else{
            sec = "Near by";
            primary = s;
        }


        TextView n1 =(TextView) listItemView.findViewById(R.id.secondary);
        n1.setText(sec);

        TextView n2 =(TextView) listItemView.findViewById(R.id.primary);
        n2.setText(primary);

        Long time = pos.getTimeInMS();
        String dateDisp = DateFormat(time);
        String dispTime = formatTime(time);

        TextView n4 =(TextView) listItemView.findViewById(R.id.date);
        n4.setText(dateDisp);

        TextView n3 =(TextView) listItemView.findViewById(R.id.time);
        n3.setText(dispTime);

        //ListView quakeListView = (ListView) quakeListView.findViewById();



        return listItemView;
    }
}

