package nightmares.pubg_quick_reference_guide.model;

/**
 * Created by user on 10/22/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Loot {
    @SerializedName("Loot")
    @Expose
    private List<HighLoot> mHighLoot = null;

    public List<HighLoot> getHighLoot() {
        return mHighLoot;
    }

    public void setHighLoot(List<HighLoot> highLoot) {
        mHighLoot = highLoot;
    }



}
