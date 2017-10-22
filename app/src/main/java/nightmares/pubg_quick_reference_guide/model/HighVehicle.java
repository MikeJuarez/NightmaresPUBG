package nightmares.pubg_quick_reference_guide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 10/21/2017.
 */

public class HighVehicle {

    @SerializedName("x")
    @Expose
    private float x;
    @SerializedName("y")
    @Expose
    private float y;
    @SerializedName("title")
    @Expose
    private String title;

    public float getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}