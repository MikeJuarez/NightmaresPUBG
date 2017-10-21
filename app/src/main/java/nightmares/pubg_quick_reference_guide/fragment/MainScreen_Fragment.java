package nightmares.pubg_quick_reference_guide.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import nightmares.pubg_quick_reference_guide.R;

import static android.R.transition.fade;

/**
 * Created by user on 10/2/2017.
 */

public class MainScreen_Fragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.main_screen_items_tv)
    TextView mItemsTextView;
    @BindView(R.id.main_screen_items_iv)
    ImageView mItemsImageView;

    @BindView(R.id.main_screen_player_rank_tv)
    TextView mPlayerRankTextView;
    @BindView(R.id.main_screen_player_rank_iv)
    ImageView mPlayerRankImageView;

    @BindView(R.id.main_screen_map_tv)
    TextView mMapTextView;
    @BindView(R.id.main_screen_map_iv)
    ImageView mMapImageView;

    @BindView(R.id.adView)
    AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainscreen, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpUi();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpUi() {
        //Setting up ads
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Setting up font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/pubg_font.ttf");
        mItemsTextView.setTypeface(custom_font);
        mPlayerRankTextView.setTypeface(custom_font);
        mMapTextView.setTypeface(custom_font);

        //Setting up Images behind textviews on cards
        Glide.with(getActivity()).load("https://raw.githubusercontent.com/ZafraniTechLLC/Companion-For-PUBG-IMAGES/master/images/Icon_ammo_12Guage.png").into(mItemsImageView);
        Glide.with(getActivity()).load("https://i.imgur.com/WaQdqWS.png").into(mPlayerRankImageView);
        Glide.with(getActivity()).load("https://i.imgur.com/hPoHfWT.png").into(mMapImageView);
        //
    }

    @OnClick(R.id.main_screen_map)
    public void submit(View view) {
        Fragment Map_Fragment = new Map_Fragment();
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment_container, Map_Fragment)
                .addToBackStack("MainScreen_Fragment")
                .commit();
    }
}
