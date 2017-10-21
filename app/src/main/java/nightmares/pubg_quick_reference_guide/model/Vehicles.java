package nightmares.pubg_quick_reference_guide.model;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by user on 10/21/2017.
 */

public class Vehicles {
        long mX;
        long mY;
        String mTitle;

        public Vehicles(int x, int y, String title) {
            mX = x;
            mY = y;
            mTitle = title;
        }

        public long getX() {
            return mX;
        }

        public long getY() {
            return mY;
        }

        public String getTitle() {
            return mTitle;
        }
}

/*    public VehiclesRed mVehiclesRed;

    public Vehicles(VehiclesRed vehiclesRed) {
        mVehiclesRed = vehiclesRed;
    }

    public VehiclesRed getVehiclesRed() {
        return mVehiclesRed;
    }

    public class VehiclesRed {
        public Vehicle mVehicle;

        public VehiclesRed(Vehicle vehicle) {
            mVehicle = vehicle;
        }

        public Vehicle getVehicle() {
            return mVehicle;
        }
    }*/
