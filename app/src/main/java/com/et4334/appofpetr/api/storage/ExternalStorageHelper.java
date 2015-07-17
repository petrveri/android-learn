package com.et4334.appofpetr.api.storage;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by et4334 on 7/13/15.
 */
public class ExternalStorageHelper extends FilesHelper {

    private final static String EXTERNAL_DIR_NAME = "documents";

    @Override
    protected File getRootDirectory(Context ctx) {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + EXTERNAL_DIR_NAME);
    }

    @Override
    protected FileInputStream getFileInputStream(Context ctx, String fileName) throws FileNotFoundException {
        File file = new File(
                Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + EXTERNAL_DIR_NAME, fileName);
        return new FileInputStream(file);
    }

    @Override
    protected FileOutputStream getFileOutputStream(Context ctx, String fileName) throws FileNotFoundException {
        File file = new File(
                Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + EXTERNAL_DIR_NAME, fileName);
        return new FileOutputStream(file);
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
