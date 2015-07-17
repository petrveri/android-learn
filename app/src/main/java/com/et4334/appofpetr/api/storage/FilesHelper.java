package com.et4334.appofpetr.api.storage;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by et4334 on 7/12/15.
 */
public abstract class FilesHelper {

    protected abstract File getRootDirectory(Context ctx);

    protected abstract FileInputStream getFileInputStream(Context ctx, String fileName) throws FileNotFoundException;

    protected abstract FileOutputStream getFileOutputStream(Context ctx, String fileName) throws FileNotFoundException;

    public String getDirectoryPath(Context ctx) {
        File file = getRootDirectory(ctx);
        String result = "Not detected";
        if (file != null) {
            result = file.getAbsolutePath();
        }
        return result;
    }

    public String readFileAsString(Context ctx, String fileName) {
        FileInputStream fis;
        String result = null;
        try {
            fis = getFileInputStream(ctx, fileName);
        } catch (FileNotFoundException e) {
            Log.e("FilesHelper", "opening file as input stream", e);
            return result;
        }
        try {
            byte buffer[] = new byte[4096];
            int read;
            StringBuilder stringBuilder = new StringBuilder();
            while ((read = fis.read(buffer)) > 0){
                stringBuilder.append(new String(buffer, 0, read));
            }
            result = stringBuilder.toString();
        } catch (IOException e) {
            Log.e("FilesHelper", "error while reading from the stream", e);
            return result;
        }
        try {
            fis.close();
        } catch (IOException e) {
            Log.e("FilesHelper", "closing of the input stream", e);
            return result;
        }
        Log.d("FilesHelper", "reading has succeeded");
        return result;
    }

    public void writeFileAsString(Context ctx, String fileName, String content) {
        FileOutputStream fos;
        try {
            fos = getFileOutputStream(ctx, fileName);
        } catch (FileNotFoundException e) {
            Log.e("FilesHelper", "opening file as output stream", e);
            return;
        }
        try {
            fos.write(content.getBytes());
        } catch (IOException e) {
            Log.e("FilesHelper", "error while writing to the stream", e);
            return;
        }
        try {
            fos.close();
        } catch (IOException e) {
            Log.e("FilesHelper", "closing of the output stream", e);
            return;
        }
        Log.d("FilesHelper", "writing has succeeded");
    }

    public void deleteFile(Context ctx, String fileName) {
        File file = new File(
                getRootDirectory(ctx)
                        .getAbsolutePath(), fileName);
        if (file.delete())
            Log.d("FilesHelper", "deleting has succeeded");
        else
            Log.d("FilesHelper", "deleting has failed");
    }

    public String[] listFiles(Context ctx) {
        String[] fileNames = getRootDirectory(ctx).list();
        Log.d("FilesHelper", "listing of files has succeeded");
        return fileNames;
    }

}
