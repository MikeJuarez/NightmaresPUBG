package nightmares.pubg_quick_reference_guide.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nightmares.pubg_quick_reference_guide.R;

/**
 * Created by user on 10/22/2017.
 */

public class HighLoot {
    private final int strokeWidth = R.dimen.map_filter_circle_stroke_width;
    private final int fillColor = R.color.pubRed;

    @SerializedName("x")
    @Expose
    private float x;

    @SerializedName("y")
    @Expose
    private float y;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("radiusMeters")
    @Expose
    private double radiusMeters;

    public double getRadiusMeters() {
        return radiusMeters;
    }

    public void setRadiusMeters(double radiusMeters) {
        this.radiusMeters = radiusMeters;    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getFillColor() {
        return fillColor;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }





}
