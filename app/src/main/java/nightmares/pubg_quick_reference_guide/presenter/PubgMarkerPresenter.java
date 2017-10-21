package nightmares.pubg_quick_reference_guide.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;


import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nightmares.pubg_quick_reference_guide.model.Vehicles;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 10/10/2017.
 */

public class PubgMarkerPresenter {
    private static PubgMarkerPresenter sPubgMarkerPresenter;
    private List<Vehicles> mVehicleList;
    private UpdateMarkersInterface mUpdateMarkersInterface;

    public static PubgMarkerPresenter getInstance(Context context, UpdateMarkersInterface updateMarkersInterface) {
        if (sPubgMarkerPresenter == null)
            return new PubgMarkerPresenter(context, updateMarkersInterface);
        //else
        return sPubgMarkerPresenter;
    }

    private PubgMarkerPresenter(Context context, UpdateMarkersInterface updateMarkersInterface)  {
        mVehicleList = new ArrayList();
        mUpdateMarkersInterface = updateMarkersInterface;
        sPubgMarkerPresenter = this;
        generateVehicleMarkers(context);
    }

    private void generateVehicleMarkers(Context context) {
        new MarkerTask().execute();
    }

    public List<Vehicles> getVehicleList() {
        return mVehicleList;
    }

    public interface UpdateMarkersInterface {
        void updateMarkers();
    }

    private class MarkerTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            try {
                Gson gson = new Gson();

                //String url = new String("https://raw.githubusercontent.com/MikeJuarez/NightmaresPUBG/master/json/marker_locations.json");
                //String reader = IOUtils.toString(new URL(url).openStream());
                //URL url = new URL("https://raw.githubusercontent.com/MikeJuarez/NightmaresPUBG/master/json/marker_locations.json");
                //InputStreamReader reader = new InputStreamReader(url.openStream());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://raw.githubusercontent.com/MikeJuarez/NightmaresPUBG/master/json/marker_locations.json")
                        .build();
                Response response = client.newCall(request).execute();
                //String result = response.body().string();
                String result = "{\n" +
                        "  \"vehicles\": {\n" +
                        "    \"vehicle_red\" : {\n" +
                        "      \"1\" : {\n" +
                        "        \"x\" : -31.90,\n" +
                        "        \"y\"  : 115.86,\n" +
                        "        \"title\" : \"Vehicle (High Chance)\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}";

                Vehicles marker = gson.fromJson(result, Vehicles.class);
                mVehicleList.add(marker);
            } catch (Exception e) {
                Log.e("class", "error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mUpdateMarkersInterface.updateMarkers();
        }
    }
}


