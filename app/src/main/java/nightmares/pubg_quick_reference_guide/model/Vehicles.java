package nightmares.pubg_quick_reference_guide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 10/21/2017.
 */


public class Vehicles {

    @SerializedName("Vehicles")
    @Expose
    private List<HighVehicle> mHighVehicles = null;

    public List<HighVehicle> getHighVehicles() {
        return mHighVehicles;
    }

    public void setHighVehicles(List<HighVehicle> highVehicles) {
        this.mHighVehicles = highVehicles;
    }
}

