package fr.esilv.myapplication2.mobiledev.app.Contract;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import fr.esilv.myapplication2.mobiledev.app.R;
import fr.esilv.myapplication2.mobiledev.app.Station.StationsActivity;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by louis on 29/01/2016.
 */
public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    private Contracts mDataSet;

    public ContractAdapter(Contracts dataSet) {
        mDataSet=dataSet;
    }

    public void setmDataSet(Contracts dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ContractAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_contract, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ContractAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mCommercial.setText(mDataSet.get(i).getCommercial_name());
        viewHolder.mName.setText(mDataSet.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mCommercial;

        public ViewHolder(View itemView) {
            super(itemView);
            mName=(TextView) itemView.findViewById(R.id.contract_name);
            mCommercial = (TextView) itemView.findViewById(R.id.contract_commercial);
        }
        public void onContractClick(View view) {

            Toast.makeText(view.getContext(), "Moo !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), StationsActivity.class);
            intent.putExtra("name",mName.getText());
            view.getContext().startActivity(intent);
        }
    }
}
