package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.quakereport.Models.QuakeObject;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuakeAdapter extends RecyclerView.Adapter<QuakeAdapter.ViewHolder> {
    private final List<QuakeObject> quakeObjectList;
    private Intent browserIntent;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView magnitude;
        public TextView offset;
        public TextView location;
        public TextView date;
        public TextView time;
        public LinearLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            magnitude = itemView.findViewById(R.id.magnitude);
            offset = itemView.findViewById(R.id.offset);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            container = itemView.findViewById(R.id.container);
        }
    }

    public QuakeAdapter(List<QuakeObject> quakeObjectList) {
        this.quakeObjectList = quakeObjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.quake_list_item, parent, false);
        return new ViewHolder(wordView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuakeObject quakeObject = quakeObjectList.get(position);

        LinearLayout container = holder.container;
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(quakeObject.getURL()));
                container.getContext().startActivity(browserIntent);
            }
        });

        TextView mag = holder.magnitude;
        mag.setText(formatMagnitude(quakeObject.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        int magnitudeColor = getMagnitudeColor(quakeObject.getMagnitude(), mag.getContext());
        magnitudeCircle.setColor(magnitudeColor);

        TextView offset = holder.offset;
        offset.setText(getOffset(quakeObject.getLocation()));

        TextView location = holder.location;
        location.setText(formatLocation(quakeObject.getLocation()));

        TextView date = holder.date;
        Date dateObject = new Date(quakeObject.getTimeInMilliseconds());
        date.setText(formatDate(dateObject));

        TextView time = holder.time;
        time.setText(formatTime(dateObject));

    }

    @Override
    public int getItemCount() {
        return quakeObjectList != null ? quakeObjectList.size() : 0;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return timeFormat.format(dateObject);
    }

    private String getOffset(String location) {
        if(location.contains("of")) {
            return location.substring(0,location.indexOf("of") + 2);
        } else {
            return "Near by";
        }
    }

    private String formatLocation(String location) {
        if(location.contains("of")) {
            return location.substring(location.indexOf("of") + 2);
        } else {
            return location;
        }
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude, Context context) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            // For case 0 we use the same logic as case 1, so we leave the case statement blank with no
            // break statement so it can fall through to the logic in case 1
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
        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }

}
