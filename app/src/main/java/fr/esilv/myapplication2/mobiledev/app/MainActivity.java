package fr.esilv.myapplication2.mobiledev.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public void setDataSet(List<Contract> dataSet) {
        this.dataSet = dataSet;
    }

    private List<Contract> dataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            String command = "stuffy";
            command+="apiKey=c9a75485e6196f494cdd205f146d838f9f16def8";
            new GetHTTPTask().execute(command);
        } else {
            // display error
        }
    }

    private class GetHTTPTask extends AsyncTask<String, Void, List<Contract>> {
        private static final String DEBUG_TAG = "GETHTTPTask Response";
        private List<Contract> contracts;

        @Override
        protected List<Contract> doInBackground(String... strings) {
            try {
                return downloadUrl(strings[0]);
            }
            catch (IOException e) {
                Log.e(DEBUG_TAG,"URL bug");
                return null;
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<Contract> result) {
            dataSet = contracts;
        }

        private List<Contract> downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a list of contracts
                List<Contract> contracts = readJsonStream(is);
                return contracts;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        public List readJsonStream(InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                return readContractsArray(reader);
            }
            finally {
                reader.close();
            }
        }

        public List readContractsArray(JsonReader reader) throws IOException {
            List contracts = new ArrayList();

            reader.beginArray();
            while (reader.hasNext()) {
                contracts.add(readContract(reader));
            }
            reader.endArray();
            return contracts;
        }

        public Contract readContract(JsonReader reader) throws IOException {
            String name="";
            List<String> cities= new ArrayList<String>();
            String commercialName = "";
            String countryCode = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String fieldName = reader.nextName();
                if (fieldName.equals("name")) {
                    name = reader.nextString();
                } else if (fieldName.equals("cities")) {
                    cities = readStringArray(reader);
                } else if (fieldName.equals("commercial_name")) {
                    commercialName = reader.nextString();
                } else if (fieldName.equals("country_code")) {
                    countryCode = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new Contract(name,cities,commercialName,countryCode);
        }

        public List readStringArray(JsonReader reader) throws IOException {
            List<String> strings = new ArrayList();

            reader.beginArray();
            while (reader.hasNext()) {
                strings.add(reader.nextString());
            }
            reader.endArray();
            return strings;
        }
    }
}
