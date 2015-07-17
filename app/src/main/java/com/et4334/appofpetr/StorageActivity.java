package com.et4334.appofpetr;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.et4334.appofpetr.api.storage.ExternalFilesHelper;
import com.et4334.appofpetr.api.storage.ExternalStorageHelper;
import com.et4334.appofpetr.api.storage.FilesHelper;
import com.et4334.appofpetr.api.storage.InternalCacheHelper;
import com.et4334.appofpetr.api.storage.InternalFilesHelper;


public class StorageActivity extends BaseFeatureActivity {

    private final static String INTERNAL_FILE_NAME = "internalFile001.txt";
    private final static String EXTERNAL_FILE_NAME = "externalFile001.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        ((CheckBox) findViewById(R.id.cboReadableExternal)).setChecked(ExternalStorageHelper.isExternalStorageReadable());
        ((CheckBox) findViewById(R.id.cboWritableExternal)).setChecked(ExternalStorageHelper.isExternalStorageWritable());
        showFilesDir();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storage, menu);
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
        } else if (id == R.id.action_storage_close) {
            gotoMainActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFilesDir() {
        Log.d("Storage Get Files Dir", "start method");
        Context ctx = getApplicationContext();
        String internalFilesDir = new InternalFilesHelper().getDirectoryPath(ctx);
        Log.d("Storage Get Files Dir", "internal files dir: " + internalFilesDir);
        String internalCacheDir = new InternalCacheHelper().getDirectoryPath(ctx);
        Log.d("Storage Get Files Dir", "internal cache dir: " + internalCacheDir);
        String externalFilesDir = new ExternalFilesHelper().getDirectoryPath(ctx);
        Log.d("Storage Get Files Dir", "external files dir: " + externalFilesDir);
        String externalStorageDir = new ExternalStorageHelper().getDirectoryPath(null);
        Log.d("Storage Get Files Dir", "external storage dir: " + externalStorageDir);
        ((TextView) findViewById(R.id.textView)).setText(
                "internal files: " + internalFilesDir + "\n"
                        + "internal cache: " + internalCacheDir + "\n"
                        + "external files: " + externalFilesDir + "\n"
                        + "external storage: " + externalStorageDir);
    }

    public void onClickBtnOpenFile(View view) {
        Context ctx = null;
        FilesHelper filesHelper = null;
        String fileName = null;
        String customFileName = ((EditText) findViewById(R.id.edtFileName)).getText().toString();
        if (((RadioButton) findViewById(R.id.rbnInternal)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnInternalCache)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalCacheHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalFiles)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new ExternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalStorage)).isChecked()) {
            filesHelper = new ExternalStorageHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        }
        readFileToEdit(filesHelper, ctx, fileName);
    }

    public void onClickBtnSaveFile(View view) {
        Context ctx = null;
        FilesHelper filesHelper = null;
        String fileName = null;
        String customFileName = ((EditText) findViewById(R.id.edtFileName)).getText().toString();
        if (((RadioButton) findViewById(R.id.rbnInternal)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnInternalCache)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalCacheHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalFiles)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new ExternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalStorage)).isChecked()) {
            filesHelper = new ExternalStorageHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        }
        writeFileFromEdit(filesHelper, ctx, fileName);
    }

    private void readFileToEdit(FilesHelper filesHelper, Context ctx, String fileName) {
        String content = filesHelper.readFileAsString(ctx, fileName);
        ((EditText) findViewById(R.id.editText)).setText(content);
    }

    private void writeFileFromEdit(FilesHelper filesHelper, Context ctx, String fileName) {
        String content = ((EditText) findViewById(R.id.editText)).getText().toString();
        filesHelper.writeFileAsString(ctx, fileName, content);
    }

    public void onClickBtnDeleteFile(View view) {
        Context ctx = null;
        FilesHelper filesHelper = null;
        String fileName = null;
        String customFileName = ((EditText) findViewById(R.id.edtFileName)).getText().toString();
        if (((RadioButton) findViewById(R.id.rbnInternal)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnInternalCache)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalCacheHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = INTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalFiles)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new ExternalFilesHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        } else if (((RadioButton) findViewById(R.id.rbnExternalStorage)).isChecked()) {
            filesHelper = new ExternalStorageHelper();
            if (!customFileName.isEmpty())
                fileName = customFileName;
            else
                fileName = EXTERNAL_FILE_NAME;
        }
        filesHelper.deleteFile(ctx, fileName);
    }

    public void onClickBtnListFile(View view) {
        Context ctx = null;
        FilesHelper filesHelper = null;
        if (((RadioButton) findViewById(R.id.rbnInternal)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalFilesHelper();
        } else if (((RadioButton) findViewById(R.id.rbnInternalCache)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new InternalCacheHelper();
        } else if (((RadioButton) findViewById(R.id.rbnExternalFiles)).isChecked()) {
            ctx = getApplicationContext();
            filesHelper = new ExternalFilesHelper();
        } else if (((RadioButton) findViewById(R.id.rbnExternalStorage)).isChecked()) {
            filesHelper = new ExternalStorageHelper();
        }
        String[] fileNames = filesHelper.listFiles(ctx);
        StringBuilder fileList = new StringBuilder();
        String delim = "";
        for (String fileName : fileNames) {
            fileList.append(delim).append(fileName);
            delim = ",\n";
        }
        ((TextView) findViewById(R.id.textView)).setText(fileList);
    }
}
