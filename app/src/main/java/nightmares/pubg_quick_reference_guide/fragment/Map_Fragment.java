package nightmares.pubg_quick_reference_guide.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


import nightmares.pubg_quick_reference_guide.R;
import static nightmares.pubg_quick_reference_guide.R.id.map;

/**
 * Created by user on 10/4/2017.
 */

public class Map_Fragment extends Fragment implements OnMapReadyCallback{

    private static final String MOON_MAP_URL_FORMAT =
            "https://raw.githubusercontent.com/MikeJuarez/PUBGQuickReferenceGuide/master/map/%d_%d_%d.png";

    private TileOverlay mMoonTiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NONE);
        map.getUiSettings().setZoomControlsEnabled(true);

        map.setMinZoomPreference(0);
        map.setMaxZoomPreference(5);

        Marker newMarker = map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world")
                .draggable(true));

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Log.d("Marker Position: ", "" + marker.getPosition());
            }
        });
        TileProvider tileProvider = new UrlTileProvider(1350, 1350) {
            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                String s = String.format(Locale.US, MOON_MAP_URL_FORMAT, zoom, x, y);
                URL url = null;

                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
                return url;
            }
        };

        mMoonTiles = map.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
    }


    public void setFadeIn(View v) {
        if (mMoonTiles == null) {
            return;
        }
        mMoonTiles.setFadeIn(((CheckBox) v).isChecked());
    }
}
