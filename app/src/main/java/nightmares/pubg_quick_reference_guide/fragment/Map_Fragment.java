package nightmares.pubg_quick_reference_guide.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import nightmares.pubg_quick_reference_guide.R;
import nightmares.pubg_quick_reference_guide.model.HighVehicle;
import nightmares.pubg_quick_reference_guide.model.Vehicles;
import nightmares.pubg_quick_reference_guide.presenter.PubgMarkerPresenter;

import static nightmares.pubg_quick_reference_guide.R.id.map;

/**
 * Created by user on 10/4/2017.
 */

public class Map_Fragment extends Fragment implements OnMapReadyCallback, PubgMarkerPresenter.UpdateMarkersInterface {

    @BindView(R.id.map_filter_vehicles_red_iv)
    ImageView mMapFilterVehiclesRedImageView;

    private static final String MOON_MAP_URL_FORMAT =
            "https://raw.githubusercontent.com/MikeJuarez/PUBGQuickReferenceGuide/master/map/%d_%d_%d.png";

    private TileOverlay mMoonTiles;
    private PubgMarkerPresenter mPubgMarkerPresenter;
    private GoogleMap mGoogleMap;
    private ArrayList<Marker> mVehicleRedMarkerList;
    private Unbinder unbinder;
    private int currentMapFilterVehicleImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        mPubgMarkerPresenter = PubgMarkerPresenter.getInstance(getActivity(), this);

        mMapFilterVehiclesRedImageView.setTag("green");
        mMapFilterVehiclesRedImageView.setImageResource(R.drawable.map_icon_vehicles_red_green);
        mVehicleRedMarkerList = new ArrayList();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mGoogleMap = map;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        mGoogleMap.setMinZoomPreference(0);
        mGoogleMap.setMaxZoomPreference(5);

        mGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
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

        mMoonTiles = mGoogleMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
        updateMarkers();
    }


    public void setFadeIn(View v) {
        if (mMoonTiles == null) {
            return;
        }
        mMoonTiles.setFadeIn(((CheckBox) v).isChecked());
    }

    @Override
    public void updateMarkers() {
        if (mPubgMarkerPresenter.getVehicleList().size() > 0) {
            Vehicles vehicles = mPubgMarkerPresenter.getVehicleList().get(0);

            for (HighVehicle highVehicle : vehicles.getHighVehicles()) {

                float xPos = highVehicle.getX();
                float yPos = highVehicle.getY();
                String pubgTitle = highVehicle.getTitle();

                Marker mVehicleRedMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(xPos, yPos))
                        .title(pubgTitle)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.google_marker_vehicle_red))
                        .draggable(true));

                mVehicleRedMarkerList.add(mVehicleRedMarker);
            }
        }
    }

    @OnClick(R.id.map_filter_vehicles_red_iv)
    public void clickMapFilterVehiclesRed() {
        if (mVehicleRedMarkerList != null) {
            String imageTagName = String.valueOf(mMapFilterVehiclesRedImageView.getTag());
            //currentMapFilterVehicleImage = (currentMapFilterVehicleImage == R.drawable.map_icon_vehicles_red_green) ? R.drawable.map_icon_vehicles_red_white : R.drawable.map_icon_vehicles_red_green;

            switch (imageTagName) {
                case ("green"):
                    mMapFilterVehiclesRedImageView.setTag("white");
                    mMapFilterVehiclesRedImageView.setImageResource(R.drawable.map_icon_vehicles_red_white);
                    for (Marker marker : mVehicleRedMarkerList) {
                        marker.setVisible(false);
                    }
                    break;
                case ("white"):
                    mMapFilterVehiclesRedImageView.setTag("green");
                    mMapFilterVehiclesRedImageView.setImageResource(R.drawable.map_icon_vehicles_red_green);
                    for (Marker marker : mVehicleRedMarkerList) {
                        marker.setVisible(true);
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
