package com.gulci.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class JPrefsActivity extends AppCompatActivity {

    public static final String USERNAME = "username";

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jprefs);

        settings = getPreferences(MODE_PRIVATE);

        Button btnWriteValue = (Button) findViewById(R.id.btnWriteValue);
        btnWriteValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = settings.edit();
                String prefValue = UIHelper.getText(JPrefsActivity.this, R.id.etEnterValue);
                editor.putString(USERNAME, prefValue);
                editor.commit();
                UIHelper.displayText(JPrefsActivity.this, R.id.tvDisplayMsg, "Preference saved");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jprefs, menu);
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
