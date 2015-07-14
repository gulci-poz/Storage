package com.gulci.storage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PrefsActivity extends AppCompatActivity {

    public static final String USERNAME = "pref_username";
    public static final String VIEWIMAGES = "pref_viewimages";

    private SharedPreferences settings;

    private OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        listener = new OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                PrefsActivity.this.refreshDisplay();
            }
        };

        settings.registerOnSharedPreferenceChangeListener(listener);

        // ręczne zapisywanie preferencji nie jest konieczne, ponieważ korzystamy z Shared Preferences

        Button btnSetPreferences = (Button) findViewById(R.id.btnSetPreferences);
        btnSetPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrefsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void refreshDisplay() {
        UIHelper.displayText(PrefsActivity.this,
                R.id.tvDisplayMessage,
                settings.getString(USERNAME, "User not found"));

        UIHelper.setCBChecked(PrefsActivity.this,
                R.id.chkImagesShow,
                settings.getBoolean(VIEWIMAGES, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
