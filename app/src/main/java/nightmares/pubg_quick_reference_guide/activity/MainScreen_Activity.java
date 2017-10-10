package nightmares.pubg_quick_reference_guide.activity;

import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nightmares.pubg_quick_reference_guide.R;
import nightmares.pubg_quick_reference_guide.fragment.MainScreen_Fragment;

import static android.R.attr.id;


public class MainScreen_Activity extends AppCompatActivity {

    @BindView(R.id.main_toolbar_app_name_tv) TextView mAppNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_);
        ButterKnife.bind(this);

        MainScreen_Fragment main_fragment = new MainScreen_Fragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, main_fragment).commit();

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/pubg_font.ttf");
        mAppNameTextView.setTypeface(custom_font);


    }
}
