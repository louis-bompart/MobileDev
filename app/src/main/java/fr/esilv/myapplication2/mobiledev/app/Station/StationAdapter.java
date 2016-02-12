package fr.esilv.myapplication2.mobiledev.app.Station;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.esilv.myapplication2.mobiledev.app.R;

/**
 * Created by louis on 29/01/2016.
 */
public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {
    private Stations mDataSet;

    public StationAdapter(Stations dataSet) {
        mDataSet=dataSet;
    }

    public void setmDataSet(Stations dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public StationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_station, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StationAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mAddress.setText(mDataSet.get(i).getAddress());
        viewHolder.mName.setText(mDataSet.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mAddress;
        public TextView mLat;
        public TextView mLng;

        public ViewHolder(View itemView) {
            super(itemView);
            mName=(TextView) itemView.findViewById(R.id.station_name);
            mAddress = (TextView) itemView.findViewById(R.id.station_address);
            mLat = (TextView) itemView.findViewById(R.id.station_lat);
            mLng = (TextView) itemView.findViewById(R.id.station_lng);
        }
    }
}
