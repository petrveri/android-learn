package com.et4334.appofpetr.api.storage;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by et4334 on 7/13/15.
 */
public class InternalFilesHelper extends FilesHelper {

    @Override
    protected File getRootDirectory(Context ctx) {
        return ctx.getFilesDir();
    }

    @Override
    protected FileInputStream getFileInputStream(Context ctx, String fileName) throws FileNotFoundException {
        return ctx.openFileInput(fileName);
    }

    @Override
    protected FileOutputStream getFileOutputStream(Context ctx, String fileName) throws FileNotFoundException {
        return ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
    }

}
