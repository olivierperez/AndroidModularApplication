package fr.o80.sample.lib.core.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.o80.sample.lib.core.Feature;
import sample.o80.fr.lib.R;
import sample.o80.fr.lib.R2;

/**
 * @author Olivier Perez
 */
public abstract class BaseDrawerActivity extends AppCompatActivity implements LibNavigationView.Listener {

    @BindView(R2.id.navigation_view)
    protected LibNavigationView libNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        ButterKnife.bind(this);

        initDagger();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        libNavigationView.setListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.main_container) == null) {
            Fragment initFragment = getInitFragment();

            if (initFragment != null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, initFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onFeatureClicked(Feature feature) {
        feature.open(this);
    }

    protected abstract Fragment getInitFragment();

    protected void initDagger() {

    }
}
