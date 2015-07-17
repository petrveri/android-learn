package com.et4334.appofpetr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.et4334.appofpetr.api.storage.PreferencesHelper;


public class AboutActivity extends BaseFeatureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        updateActivityCounts();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        updateActivityCounts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
        } else if (id == R.id.action_about_close) {
            gotoMainActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnOKClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void updateActivityCounts() {
        int storageCount = new PreferencesHelper(this).getCount("activity_storage");
        ((TextView) findViewById(R.id.lblActivityStorageCountVal)).setText(Integer.toString(storageCount));
        int sqliteCount = new PreferencesHelper(this).getCount("activity_sqlite");
        ((TextView) findViewById(R.id.lblActivitySqliteCountVal)).setText(Integer.toString(sqliteCount));
    }
}
