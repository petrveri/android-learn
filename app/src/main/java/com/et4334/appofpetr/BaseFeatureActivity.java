package com.et4334.appofpetr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by et4334 on 7/13/15.
 */
public class BaseFeatureActivity extends Activity {

    protected void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
//      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}
