package fr.esilv.myapplication2.mobiledev.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import fr.esilv.myapplication2.mobiledev.app.Contract.*;
import fr.esilv.myapplication2.mobiledev.app.Station.StationsActivity;

public class MainActivity extends AppCompatActivity {

    Contracts dataSet;
    private RecyclerView recyclerview;
    private ContractAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSet = new Contracts();
        setRecyclerView();
        getContracts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getContracts() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String command = "https://api.jcdecaux.com/vls/v1/contracts?";
            command+="apiKey=c9a75485e6196f494cdd205f146d838f9f16def8";
            StringRequest stringRequest = new StringRequest
                    (Request.Method.GET, command, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            dataSet = gson.fromJson(response, Contracts.class);
                            adapter.setmDataSet(dataSet);
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                        }
                    });
            queue.add(stringRequest);
        }
        else {
            // display error
        }
    }

    private void setRecyclerView() {
        recyclerview = (RecyclerView) findViewById(R.id.contracts_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new ContractAdapter(dataSet);
        recyclerview.setAdapter(adapter);
    }
    public void onContractClick(View view) {

        Toast.makeText(view.getContext(), "Moo !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), StationsActivity.class);
        intent.putExtra("name",((TextView)view.findViewById(R.id.contract_name)).getText());
        view.getContext().startActivity(intent);
    }
}
