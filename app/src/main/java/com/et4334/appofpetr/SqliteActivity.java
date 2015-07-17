package com.et4334.appofpetr;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.et4334.appofpetr.api.storage.SqliteHelper;
import com.et4334.appofpetr.vo.River;

import java.util.List;


public class SqliteActivity extends BaseFeatureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
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
        } else if (id == R.id.action_sqlite_close) {
            gotoMainActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickBtnCreateDB(View view) {
        new SqliteHelper().createDB();
    }

    public void onClickBtnInsertRecord(View view) {
        String id = ((EditText) findViewById(R.id.edtId)).getText().toString();
        String name = ((EditText) findViewById(R.id.edtName)).getText().toString();
        Context ctx = getApplicationContext();
        new SqliteHelper().persistRecord(ctx, id, name);
        ((TextView) findViewById(R.id.txtResult)).setText("");
    }

    public void onClickBtnGetRecord(View view) {
        String id = ((EditText) findViewById(R.id.edtId)).getText().toString();
        Context ctx = getApplicationContext();
        River river = new SqliteHelper().getRecord(ctx, id);
        String json = String.format("{\n  id: '%s', name: '%s'\n}", river.getId(), river.getName());
        ((TextView) findViewById(R.id.txtResult)).setText(json);
    }

    public void onClickBtnListRecords(View view) {
        Context ctx = getApplicationContext();
        List<River> rivers = new SqliteHelper().listRecords(ctx);
        StringBuilder riverList = new StringBuilder();
        riverList.append("[");
        String delim = "\n";
        for (River river : rivers) {
            riverList.append(delim);
            riverList.append(String.format("{\n  id: '%s', name: '%s'\n}", river.getId(), river.getName()));
            delim = ",\n";
        }
        riverList.append("\n]");
        ((TextView) findViewById(R.id.txtResult)).setText(riverList.toString());
    }

    public void onClickBtnDeleteRecord(View view) {
        String id = ((EditText) findViewById(R.id.edtId)).getText().toString();
        Context ctx = getApplicationContext();
        new SqliteHelper().deleteRecord(ctx, id);
        ((TextView) findViewById(R.id.txtResult)).setText("");
    }
}
