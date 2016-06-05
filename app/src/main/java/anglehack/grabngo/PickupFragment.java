package anglehack.grabngo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickupFragment extends Fragment {

    ProgressBar progressBar;
    TextView result, estimatedArrival, shortestDistance;
    EditText origin, destination;
    Button calculate;
    String originText, destinationText;
    public PickupFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pickup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) getView().findViewById(R.id.loading_price);
        result = (TextView) getView().findViewById(R.id.result);
        estimatedArrival = (TextView) getView().findViewById(R.id.estimate_time);
        shortestDistance = (TextView) getView().findViewById(R.id.distance);
        origin = (EditText) getView().findViewById(R.id.origin);
        destination = (EditText) getView().findViewById(R.id.destination);
        calculate = (Button) getView().findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrieveShortestDistance task = new RetrieveShortestDistance();
                originText = origin.getText().toString();
                destinationText = destination.getText().toString();
                task.execute();
            }
        });
    }

    public class RetrieveShortestDistance extends AsyncTask<Void, Void, String> {
        private Exception exception;

        public RetrieveShortestDistance() {
            super();
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null){
                s = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            int distance = 0;
            try {
                JSONObject object = (JSONObject) new JSONTokener(s).nextValue();
                // rows = object.getJSONArray("rows").getJSONArray("elements").getJSONObject(0).getInteger("value");
                JSONObject obj = object.getJSONArray("rows").getJSONObject(0);
                distance = obj.getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value");
                //distance = object.getJSONArray("rows").getJSONObject(0).getJSONArray("distance").getInt("value");
                //getJSONArray(0).getJSONObject(0).getInt("value");
                Log.i("INFO", String.format("%d", distance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            shortestDistance.setText( String.format("%dKM", distance/1000));
            estimatedArrival.setText(Request.calculateEstimatedArrival());
            result.setText( String.format("RM%.2f", Request.calculatePrice(distance) ) );
            Log.i("INFO", String.format("%d", distance) );

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+originText+"&destinations="+destinationText+"&key=AIzaSyCCtV6CGtlIGIQ4fLu2gOrJI_pRmPyPEkA");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}


